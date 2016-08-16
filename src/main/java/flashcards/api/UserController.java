package flashcards.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import flashcards.dto.UserDto;
import flashcards.dto.login.BaseLoginDto;
import flashcards.dto.login.BaseRegisterDto;
import flashcards.dto.login.GoogleLoginDto;
import flashcards.mapper.UserMapper;
import flashcards.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public UserDto register(@RequestBody BaseRegisterDto registerDto) {
        return userMapper.toUserDto(userService.register(registerDto));
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/login/base", method = RequestMethod.POST)
    public void login(@RequestBody BaseLoginDto loginDto) {
        userService.login(loginDto);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/login/google", method = RequestMethod.POST)
    public void login(@RequestBody GoogleLoginDto loginDto) {
        userService.login(loginDto);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void logout() {
        userService.logout();
    }

    @RequestMapping
    public UserDto getUser() {
        return userMapper.toUserDto(userService.getCurrentUser());
    }

}
