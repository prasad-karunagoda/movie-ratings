package prasad.movieratings.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie")
@CrossOrigin
public class MovieController {

    @Autowired
    private MovieRepository repository;

    @GetMapping("/recent")
    public List<Movie> getRecentMovies(@RequestParam(required = false) Integer pageNumber,
                                    @RequestParam(required = false) Integer pageSize) {

        if (pageNumber == null) pageNumber = 0;
        if (pageSize == null) pageSize = 10;

        return repository.findAllByOrderByYearDesc(PageRequest.of(pageNumber, pageSize));
    }

    @GetMapping("/{part-of-the-title}")
    public List<Movie> getMovies(@PathVariable("part-of-the-title") String partOfTheTitle) {

        return repository.findByTitle(partOfTheTitle);
    }
}
