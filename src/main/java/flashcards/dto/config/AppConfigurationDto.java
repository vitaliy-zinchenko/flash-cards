package flashcards.dto.config;

public class AppConfigurationDto {

    private String googleClientId;

    public String getGoogleClientId() {
        return googleClientId;
    }

    public AppConfigurationDto setGoogleClientId(String googleClientId) {
        this.googleClientId = googleClientId;
        return this;
    }

}
