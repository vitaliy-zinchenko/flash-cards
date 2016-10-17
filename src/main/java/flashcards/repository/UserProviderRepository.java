package flashcards.repository;

import org.springframework.data.repository.CrudRepository;

import flashcards.domain.UserInfo;
import flashcards.domain.UserProvider;

public interface UserProviderRepository extends CrudRepository<UserProvider, Long> {

    UserProvider findByCode(UserProvider.Code code);

}
