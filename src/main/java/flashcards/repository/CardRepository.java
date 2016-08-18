package flashcards.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import flashcards.domain.Card;
import flashcards.domain.UserInfo;

public interface CardRepository extends CrudRepository<Card, Long> {

    @Query("select c from Card c where c.cardSet.id = :cardSetId")
    public List<Card> findByCardSetId(@Param("cardSetId") Long cardSetId, Pageable pageable);

}
