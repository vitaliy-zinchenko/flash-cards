package flashcards.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AnswerType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Code code;

    private String title;

    public enum Code {
        TRANSLATION, LISTENING, MATCHING
    }

    public Long getId() {
        return id;
    }

    public AnswerType setId(Long id) {
        this.id = id;
        return this;
    }

    public Code getCode() {
        return code;
    }

    public AnswerType setCode(Code code) {
        this.code = code;
        return this;
    }


    public String getTitle() {
        return title;
    }

    public AnswerType setTitle(String title) {
        this.title = title;
        return this;
    }


}
