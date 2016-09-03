package flashcards.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flashcards.domain.Card;
import flashcards.domain.CardSet;
import flashcards.dto.CardDto;
import flashcards.dto.CardSetDto;
import flashcards.mapper.CardMapper;
import flashcards.mapper.CardSetMapper;
import flashcards.repository.CardRepository;
import flashcards.repository.CardSetRepository;

@Service
public class CardSetService {

    @Autowired
    private CardSetRepository cardSetRepository;
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardSetMapper cardSetMapper;
    @Autowired
    private CardMapper cardMapper;

    @Autowired
    private UserService userService;

    @Transactional
    public CardSetDto save(CardSetDto cardSetDto) {
        CardSet cardSet = cardSetMapper.toEntity(cardSetDto);
        cardSet.setUserInfo(userService.getCurrentUser());
        cardSetRepository.save(cardSet);
        return cardSetMapper.toDto(cardSet);
    }

    @Transactional
    public List<CardDto> add(Long cardSetId, List<CardDto> cardDtos) {
        CardSet cardSet = cardSetRepository.findOne(cardSetId);
        List<Card> cards = cardDtos.stream()
                .map(cardMapper::toEntity)
                .peek(card -> card.setCardSet(cardSet))
                .collect(Collectors.toList());
        Iterable<Card> saved = cardRepository.save(cards);
        return StreamSupport.stream(saved.spliterator(), false)
                .map(cardMapper::toDto)
                .collect(Collectors.toList());
    }

}
