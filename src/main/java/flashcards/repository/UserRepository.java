package flashcards.repository;

import org.springframework.data.repository.CrudRepository;

import flashcards.domain.UserInfo;

public interface UserRepository extends CrudRepository<UserInfo, Long> {

    UserInfo findByEmail(String email);

}
