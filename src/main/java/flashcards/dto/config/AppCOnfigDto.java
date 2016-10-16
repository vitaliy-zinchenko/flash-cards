package flashcards.dto.config;

public class AppConfigDto {

    private String googleClientId;

    public String getGoogleClientId() {
        return googleClientId;
    }

    public AppConfigDto setGoogleClientId(String googleClientId) {
        this.googleClientId = googleClientId;
        return this;
    }
}
