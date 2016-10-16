package flashcards.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import flashcards.dto.config.AppConfigDto;

@Service
public class ConfigService {

    @Value("${fc.google.client.id}")
    private String googleClientId;

    public AppConfigDto getAppConfigDto() {
        return new AppConfigDto()
                .setGoogleClientId(googleClientId);
    }

}
