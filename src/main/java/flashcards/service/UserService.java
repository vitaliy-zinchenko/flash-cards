package flashcards.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;

import flashcards.domain.UserInfo;
import flashcards.dto.login.BaseLoginDto;
import flashcards.dto.login.BaseRegisterDto;
import flashcards.dto.login.GoogleLoginDto;
import flashcards.exception.AuthenticationException;
import flashcards.mapper.UserMapper;
import flashcards.repository.UserRepository;

//TODO it is possible to enter with email of google account without password
@Service
public class UserService {

    @Autowired
    private GoogleIdTokenVerifier googleIdTokenVerifier;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserInfo getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getPrincipal() instanceof String) {
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
        if(user == null) {
            throw new AuthenticationException();
        }
        if(!Objects.equals(user.getPassword(), loginDto.getPassword())) {
            throw new AuthenticationException();
        }
        login(user);
    }

    public void login(UserInfo user) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);
        login(authentication);
    }

    public UserInfo login(GoogleLoginDto loginDto) {
        UserInfo user = getUser(loginDto);
        login(user);
        return user;
    }

    private UserInfo getUser(GoogleLoginDto loginDto) {
        try {
            GoogleIdToken idToken = googleIdTokenVerifier.verify(loginDto.getIdToken());
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();
                String email = payload.getEmail();
                UserInfo user = userRepository.findByEmail(email);
                if (user == null) {
                    String name = (String) payload.get("name");
                    String familyName = (String) payload.get("family_name");
                    user = new UserInfo()
                            .setEmail(email)
                            .setFirstName(name)
                            .setLastName(familyName);
                    userRepository.save(user);
                }
                return user;
            }
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace(); //TODO handle
        }
        //TODO handle
        return null;
    }

    private void login(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public void logout() {
        SecurityContextHolder.clearContext();
    }

}
