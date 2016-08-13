package flashcards.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "card_set_id")
    private CardSet cardSet;

    private String word;

    private String translation;

    public Long getId() {
        return id;
    }

    public Card setId(Long id) {
        this.id = id;
        return this;
    }

    public CardSet getCardSet() {
        return cardSet;
    }

    public Card setCardSet(CardSet cardSet) {
        this.cardSet = cardSet;
        return this;
    }

    public String getWord() {
        return word;
    }

    public Card setWord(String word) {
        this.word = word;
        return this;
    }

    public String getTranslation() {
        return translation;
    }

    public Card setTranslation(String translation) {
        this.translation = translation;
        return this;
    }
}
