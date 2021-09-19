package prasad.movieratings.movies;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, String> {

    List<Movie> findAllByOrderByYearDesc(Pageable page);

    List<Movie> findByTitleLike(String partOfTheTitle);

    default List<Movie> findByTitle(String partOfTheTitle) {
        return findByTitleLike("%" + partOfTheTitle + "%");
    }
}
