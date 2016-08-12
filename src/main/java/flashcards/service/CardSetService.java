package flashcards.service;

import org.springframework.stereotype.Service;

import flashcards.dto.CardSetDto;
import flashcards.repository.CardSetRepository;

@Service
public class CardSetService {

    private CardSetRepository cardSetRepository;

    public CardSetDto save(CardSetDto cardSet) {
        return null;
    }

}
