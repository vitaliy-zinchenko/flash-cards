//package model.domain;
//
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//
//@Entity
//public class UserProvider {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private Long id;
//
//    @Enumerated(EnumType.STRING)
//    private Code code;
//
//    public enum Code {
//        GOOGLE
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public UserProvider setId(Long id) {
//        this.id = id;
//        return this;
//    }
//
//    public Code getCode() {
//        return code;
//    }
//
//    public UserProvider setCode(Code code) {
//        this.code = code;
//        return this;
//    }
//}
