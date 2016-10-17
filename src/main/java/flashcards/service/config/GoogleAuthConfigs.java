package flashcards.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GoogleAuthConfigs {

    @Value("${fc.google.client.id}")
    private String clientId;
    @Value("${fc.google.redirect.uri}")
    private String redirectUri;
    @Value("${fc.google.client.secret}")
    private String clientSecret;

    public String getClientId() {
        return clientId;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}
