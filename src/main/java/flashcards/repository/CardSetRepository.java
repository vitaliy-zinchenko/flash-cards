package flashcards.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import flashcards.domain.CardSet;
import flashcards.domain.UserInfo;

public interface CardSetRepository extends CrudRepository<CardSet, Long> {

    List<CardSet> findByUserInfo(UserInfo userInfo, Pageable pageable);

}
