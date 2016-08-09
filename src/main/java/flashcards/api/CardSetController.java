package flashcards.api;

import flashcards.domain.Card;
import flashcards.domain.CardSet;
import flashcards.repository.CardRepository;
import flashcards.repository.CardSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card-set")
public class CardSetController {

    @Autowired
    private CardSetRepository cardSetRepository;

    @Autowired
    private CardRepository cardRepository;

    @RequestMapping
    public Iterable<CardSet> getAll() {
        return cardSetRepository.findAll();
    }

    @RequestMapping("/{id}/cards")
    public Iterable<Card> getAll(@PathVariable("id") Long cardSetId) {
        return cardRepository.findByCardSetId(cardSetId);
    }

}
