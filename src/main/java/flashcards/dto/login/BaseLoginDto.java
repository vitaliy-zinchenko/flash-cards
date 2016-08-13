package flashcards.dto.login;

public class BaseLoginDto {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public BaseLoginDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public BaseLoginDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
