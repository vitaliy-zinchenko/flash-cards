package flashcards.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import flashcards.dto.config.AppConfigDto;
import flashcards.service.config.GoogleAuthConfigs;

@Service
public class ConfigService {

    @Autowired
    private GoogleAuthConfigs googleAuthConfigs;

    public AppConfigDto getAppConfigDto() {
        return new AppConfigDto()
                .setGoogleClientId(googleAuthConfigs.getClientId());
    }

}
