package flashcards.mapper;

import org.mapstruct.Mapper;

import flashcards.domain.Card;
import flashcards.domain.CardSet;
import flashcards.dto.CardDto;
import flashcards.dto.CardSetDto;

@Mapper
public interface CardMapper {

    CardDto toDto(Card card);
    Card toEntity(CardDto cardDto);

}
