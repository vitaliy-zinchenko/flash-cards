package flashcards.dto.login;

public class GoogleLoginDto {

    private String idToken;

    public String getIdToken() {
        return idToken;
    }

    public GoogleLoginDto setIdToken(String idToken) {
        this.idToken = idToken;
        return this;
    }
}
