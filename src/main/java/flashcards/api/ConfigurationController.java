package flashcards.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import flashcards.dto.config.AppConfigDto;
import flashcards.dto.login.BaseLoginDto;
import flashcards.service.ConfigService;

@RestController
@RequestMapping("/api/config")
public class ConfigurationController {

    @Autowired
    private ConfigService configService;

    @RequestMapping(value = "/app")
    public AppConfigDto login() {
        return configService.getAppConfigDto();
    }

}
