package flashcards.dto.login;

public class LoginDto {

    String clientId;
    String redirectUri;
    String code;

    @Override
    public String toString() {
        return "LoginDto{" +
                "clientId='" + clientId + '\'' +
                ", redirectUri='" + redirectUri + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getClientId() {
        return clientId;
    }

    public LoginDto setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public LoginDto setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
        return this;
    }

    public String getCode() {
        return code;
    }

    public LoginDto setCode(String code) {
        this.code = code;
        return this;
    }
}
