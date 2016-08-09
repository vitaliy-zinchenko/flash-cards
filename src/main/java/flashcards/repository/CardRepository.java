package flashcards.repository;

import flashcards.domain.Card;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardRepository extends CrudRepository<Card, Long> {

    @Query("select c from Card c where c.cardSet.id = :cardSetId")
    public List<Card> findByCardSetId(@Param("cardSetId") Long cardSetId);

}
