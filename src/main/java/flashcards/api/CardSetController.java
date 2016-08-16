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
import flashcards.dto.CardSetDto;
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
