package flashcards.mapper;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MapperUtil {

    public static <T, D> Collection<D> map(Iterable<T> iterable, Function<T, D> mapper) {
           return StreamSupport.stream(iterable.spliterator(), false)
                   .map(mapper)
                   .collect(Collectors.toList());
    }

}
