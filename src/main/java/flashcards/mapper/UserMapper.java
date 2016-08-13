package flashcards.mapper;

import org.mapstruct.Mapper;

import flashcards.domain.UserInfo;
import flashcards.dto.UserDto;
import flashcards.dto.login.BaseRegisterDto;

@Mapper
public interface UserMapper {

    UserDto toUserDto(UserInfo userInfo);

    UserInfo toUserInfo(BaseRegisterDto registerDto);

}
