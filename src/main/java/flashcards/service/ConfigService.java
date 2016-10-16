package flashcards.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import flashcards.dto.config.AppConfigurationDto;

@Service
public class ConfigService {

    @Value("${fc.google.client.id}")
    private String googleClientId;

    public AppConfigurationDto getAppConfigDto() {
        return new AppConfigurationDto()
                .setGoogleClientId(googleClientId);
    }

}
