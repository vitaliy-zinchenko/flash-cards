package flashcards.repository;

import org.springframework.data.repository.CrudRepository;

import flashcards.domain.CardSet;

public interface CardSetRepository extends CrudRepository<CardSet, Long> {

}
