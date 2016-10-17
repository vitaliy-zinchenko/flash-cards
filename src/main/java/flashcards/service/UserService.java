package flashcards.service;

import java.util.Collections;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;

import flashcards.domain.UserInfo;
import flashcards.domain.UserProvider;
import flashcards.dto.login.BaseLoginDto;
import flashcards.dto.login.BaseRegisterDto;
import flashcards.dto.login.GoogleTokenResponse;
import flashcards.dto.login.GoogleUserDto;
import flashcards.dto.login.LoginDto;
import flashcards.exception.AuthenticationException;
import flashcards.mapper.UserMapper;
import flashcards.repository.UserProviderRepository;
import flashcards.repository.UserRepository;
import flashcards.service.config.GoogleAuthConfigs;

//TODO it is possible to enter with email of google account without password
@Service
public class UserService {

    @Autowired
    private GoogleIdTokenVerifier googleIdTokenVerifier;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private GoogleAuthConfigs googleAuthConfigs;
    @Autowired
    private UserProviderRepository userProviderRepository;

    public UserInfo getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof String) {
            return null; //TODO handle
        }
        return (UserInfo) authentication.getPrincipal();
    }

    @Transactional
    public UserInfo register(BaseRegisterDto registerDto) {
        UserInfo userInfo = userMapper.toUserInfo(registerDto);
        userRepository.save(userInfo);
        login(userInfo);
        return userInfo;
    }

    public void login(BaseLoginDto loginDto) {
        UserInfo user = userRepository.findByEmail(loginDto.getEmail());
        if (user == null) {
            throw new AuthenticationException();
        }
        if (!Objects.equals(user.getPassword(), loginDto.getPassword())) {
            throw new AuthenticationException();
        }
        login(user);
    }

    public void login(UserInfo user) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);
        login(authentication);
    }

    //TODO implement according to this https://developers.google.com/api-client-library/java/
    public UserInfo login(LoginDto loginDto) {
        String accessToken = getAccessToken(loginDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.put("Authorization", Collections.singletonList("Bearer " + accessToken));

        HttpEntity<String> request = new HttpEntity<>("parameters", headers);
        ResponseEntity<GoogleUserDto> response = restTemplate.exchange("https://www.googleapis.com/plus/v1/people/me",
                HttpMethod.GET, request, GoogleUserDto.class);

        return getUser(response.getBody());
    }

    private String getAccessToken(LoginDto loginDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("grant_type", "authorization_code");
        map.add("code", loginDto.getCode());
        map.add("client_id", googleAuthConfigs.getClientId());
        map.add("client_secret", googleAuthConfigs.getClientSecret());
        map.add("redirect_uri", googleAuthConfigs.getRedirectUri());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<GoogleTokenResponse> response = restTemplate.postForEntity("https://www.googleapis" +
                ".com/oauth2/v4/token", request, GoogleTokenResponse.class);
        return response.getBody().getAccessToken();
    }

    private UserInfo getUser(GoogleUserDto googleUserDto) {
        UserInfo user = userRepository.findByExternalId(googleUserDto.getId());
        if (user == null) {
            user = new UserInfo()
                    .setEmail(googleUserDto.getFirstEmail())
                    .setFirstName(googleUserDto.getName().getGivenName())
                    .setLastName(googleUserDto.getName().getFamilyName())
                    .setUserProvider(userProviderRepository.findByCode(UserProvider.Code.GOOGLE))
                    .setExternalId(googleUserDto.getId());
            userRepository.save(user);
        }
        return user;
    }

    private void login(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public void logout() {
        SecurityContextHolder.clearContext();
    }

}
