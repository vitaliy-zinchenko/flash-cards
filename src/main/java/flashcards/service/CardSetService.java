package flashcards.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flashcards.domain.CardSet;
import flashcards.dto.CardSetDto;
import flashcards.mapper.CardSetMapper;
import flashcards.repository.CardSetRepository;

@Service
public class CardSetService {

    @Autowired
    private CardSetRepository cardSetRepository;

    @Autowired
    private CardSetMapper cardSetMapper;

    @Autowired
    private UserService userService;

    public CardSetDto save(CardSetDto cardSetDto) {
        CardSet cardSet = cardSetMapper.toEntity(cardSetDto);
        cardSet.setUserInfo(userService.getCurrentUser());
        cardSetRepository.save(cardSet);
        return cardSetMapper.toDto(cardSet);
    }

}
