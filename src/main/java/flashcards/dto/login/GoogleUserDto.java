package flashcards.dto.login;

import java.util.List;

public class GoogleUserDto {

    private List<Email> emails;

    private Image image;

    private Name name;

    private String id;

    public String getFirstEmail() {
        return emails.get(0).value;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public GoogleUserDto setEmails(List<Email> emails) {
        this.emails = emails;
        return this;
    }

    public Image getImage() {
        return image;
    }

    public GoogleUserDto setImage(Image image) {
        this.image = image;
        return this;
    }

    public Name getName() {
        return name;
    }

    public GoogleUserDto setName(Name name) {
        this.name = name;
        return this;
    }

    public String getId() {
        return id;
    }

    public GoogleUserDto setId(String id) {
        this.id = id;
        return this;
    }

    public static class Email {
        private String value;
        private String type;

        public String getValue() {
            return value;
        }

        public Email setValue(String value) {
            this.value = value;
            return this;
        }

        public String getType() {
            return type;
        }

        public Email setType(String type) {
            this.type = type;
            return this;
        }
    }

    public static class Image {
        private String url;

        public String getUrl() {
            return url;
        }

        public Image setUrl(String url) {
            this.url = url;
            return this;
        }
    }

    public static class Name {
        private String familyName;
        private String givenName;

        public String getFamilyName() {
            return familyName;
        }

        public Name setFamilyName(String familyName) {
            this.familyName = familyName;
            return this;
        }

        public String getGivenName() {
            return givenName;
        }

        public Name setGivenName(String givenName) {
            this.givenName = givenName;
            return this;
        }
    }

}


