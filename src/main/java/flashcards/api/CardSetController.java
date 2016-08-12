package flashcards.api;

import flashcards.domain.Card;
import flashcards.domain.CardSet;
import flashcards.dto.CardSetDto;
import flashcards.repository.CardRepository;
import flashcards.repository.CardSetRepository;
import flashcards.service.CardSetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card-set")
public class CardSetController {

    @Autowired
    private CardSetRepository cardSetRepository; //TODO remove

    @Autowired
    private CardRepository cardRepository; //TODO remove

    @Autowired
    private CardSetService cardSetService;

    @RequestMapping
    public Iterable<CardSet> getAll() {
        return cardSetRepository.findAll();
    }

    @RequestMapping("/{id}/cards")
    public Iterable<Card> getAll(@PathVariable("id") Long cardSetId) {
        return cardRepository.findByCardSetId(cardSetId);
    }

    @RequestMapping(method = {RequestMethod.POST})
    public CardSetDto create(@RequestBody CardSetDto cardSetDto) {
        return cardSetService.save(cardSetDto);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT})
    public CardSetDto update(@PathVariable("id") Long id, @RequestBody CardSetDto cardSetDto) {
        return cardSetService.save(cardSetDto);
    }

}
