package flashcards.mapper;

import org.mapstruct.Mapper;

import flashcards.domain.CardSet;
import flashcards.domain.UserInfo;
import flashcards.dto.CardSetDto;
import flashcards.dto.UserDto;
import flashcards.dto.login.BaseRegisterDto;

@Mapper
public interface CardSetMapper {

    CardSetDto toDto(CardSet cardSet);

}
