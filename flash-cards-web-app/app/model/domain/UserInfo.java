//package model.domain;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//
//@Entity
//public class UserInfo {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private Long id;
//
//    private String externalId;
//
//    @ManyToOne
//    @JoinColumn(name = "user_provider_id")
//    private UserProvider userProvider;
//
//    private String email;
//
//    private String firstName;
//
//    private String lastName;
//
//    private String password;
//
//    public Long getId() {
//        return id;
//    }
//
//    public UserInfo setId(Long id) {
//        this.id = id;
//        return this;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public UserInfo setEmail(String email) {
//        this.email = email;
//        return this;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public UserInfo setFirstName(String firstName) {
//        this.firstName = firstName;
//        return this;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public UserInfo setLastName(String lastName) {
//        this.lastName = lastName;
//        return this;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public UserInfo setPassword(String password) {
//        this.password = password;
//        return this;
//    }
//
//    public String getExternalId() {
//        return externalId;
//    }
//
//    public UserInfo setExternalId(String externalId) {
//        this.externalId = externalId;
//        return this;
//    }
//
//    public UserProvider getUserProvider() {
//        return userProvider;
//    }
//
//    public UserInfo setUserProvider(UserProvider userProvider) {
//        this.userProvider = userProvider;
//        return this;
//    }
//}
