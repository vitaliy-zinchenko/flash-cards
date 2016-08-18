package flashcards.api;

import static flashcards.mapper.MapperUtil.map;

import java.util.Collection;
import java.util.List;

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
import flashcards.domain.UserInfo;
import flashcards.dto.CardDto;
import flashcards.dto.CardSetDto;
import flashcards.mapper.CardMapper;
import flashcards.mapper.CardSetMapper;
import flashcards.mapper.MapperUtil;
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
    @Autowired
    private CardSetMapper cardSetMapper;
    @Autowired
    private CardMapper cardMapper;


    //TODO protect with security
    @RequestMapping
    public Iterable<CardSetDto> getCardSets(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size) {
        Pageable pageable = new PageRequest(page, size);
        UserInfo currentUser = userService.getCurrentUser();
        List<CardSet> cardSets = cardSetRepository.findByUserInfo(currentUser, pageable);
        return map(cardSets, cardSetMapper::toDto);
    }

    //TODO protect with security. Protect fetching cards from other users.
    @RequestMapping("/{id}/cards")
    public Collection<CardDto> getCardSetCards(
            @PathVariable("id") Long cardSetId,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size) {
        Pageable pageable = new PageRequest(page, size);
        List<Card> cards = cardRepository.findByCardSetId(cardSetId, pageable);
        return map(cards, cardMapper::toDto);
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
