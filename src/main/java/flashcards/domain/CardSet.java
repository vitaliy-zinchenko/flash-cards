package flashcards.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CardSet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne
    @JoinColumn(name = "translation_language_id")
    private Language translationLanguage;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public CardSet setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CardSet setTitle(String title) {
        this.title = title;
        return this;
    }

    public Language getLanguage() {
        return language;
    }

    public CardSet setLanguage(Language language) {
        this.language = language;
        return this;
    }

    public Language getTranslationLanguage() {
        return translationLanguage;
    }

    public CardSet setTranslationLanguage(Language translationLanguage) {
        this.translationLanguage = translationLanguage;
        return this;
    }

    public User getUser() {
        return user;
    }

    public CardSet setUser(User user) {
        this.user = user;
        return this;
    }
}
