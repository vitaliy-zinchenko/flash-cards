package flashcards.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardDto {

    private Long id;
    private String word;
    private String translation;

    public Long getId() {
        return id;
    }

    public CardDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getWord() {
        return word;
    }

    public CardDto setWord(String word) {
        this.word = word;
        return this;
    }

    public String getTranslation() {
        return translation;
    }

    public CardDto setTranslation(String translation) {
        this.translation = translation;
        return this;
    }
}
