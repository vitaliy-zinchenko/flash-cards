package flashcards.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardSetDto {

    private Long id;

    private String title;

    public String getTitle() {
        return title;
    }

    public CardSetDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public Long getId() {
        return id;
    }

    public CardSetDto setId(Long id) {
        this.id = id;
        return this;
    }
}
