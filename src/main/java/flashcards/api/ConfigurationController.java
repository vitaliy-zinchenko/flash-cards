package flashcards.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import flashcards.dto.config.AppConfigDto;
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
