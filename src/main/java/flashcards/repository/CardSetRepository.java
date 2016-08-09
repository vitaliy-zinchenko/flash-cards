package flashcards.repository;

import flashcards.domain.CardSet;
import org.springframework.data.repository.CrudRepository;

public interface CardSetRepository extends CrudRepository<CardSet, Long> {

}
