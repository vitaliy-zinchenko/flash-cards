package flashcards.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import flashcards.domain.Card;
import flashcards.domain.CardSet;
import flashcards.dto.CardSetDto;
import flashcards.repository.CardRepository;
import flashcards.repository.CardSetRepository;
import flashcards.service.CardSetService;
import flashcards.service.UserService;

@RestController
@RequestMapping("/card-set")
public class CardSetController {

    @Autowired
    private CardSetRepository cardSetRepository; //TODO remove

    @Autowired
    private CardRepository cardRepository; //TODO remove

    @Autowired
    private CardSetService cardSetService;

    @Autowired
    private UserService userService;

    //TODO protect with security
    @RequestMapping
    public Iterable<CardSet> getCardSets(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size) {
        Pageable pageable = new PageRequest(page, size);
        return cardSetRepository.findByUserInfo(userService.getCurrentUser(), pageable);
    }

    @RequestMapping("/{id}/cards")
    public Iterable<Card> getCardSets(@PathVariable("id") Long cardSetId) {
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

    @RequestMapping(value = "/doc", method = {RequestMethod.GET})
    public CardSetDto update() {
        return new CardSetDto()
                .setTitle("qwe");
    }

}
