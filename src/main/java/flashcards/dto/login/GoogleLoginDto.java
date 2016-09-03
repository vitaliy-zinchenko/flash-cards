package flashcards.dto.login;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
