package ie.sugrue.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ie.sugrue.domain.Movie;
import ie.sugrue.domain.ResponseWrapper;
import ie.sugrue.service.movie.CreateMovieService;
import ie.sugrue.service.movie.DeleteMovieService;
import ie.sugrue.service.movie.GetMovieService;
import ie.sugrue.service.movie.UpdateMovieService;

@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
@RequestMapping("/movie")
@Scope("request")
public class MovieController extends PrimaryController {

	private final Logger	log	= LoggerFactory.getLogger(this.getClass());

	@Autowired
	ResponseWrapper			resp;
	@Autowired
	GetMovieService			getMovieService;
	@Autowired
	CreateMovieService		createMovieService;
	@Autowired
	UpdateMovieService		updateMovieService;
	@Autowired
	DeleteMovieService		deleteMovieService;

	@RequestMapping("")
	public ResponseWrapper getMovie(@RequestParam(value = "id", defaultValue = "") int id) {
		ResponseWrapper resp2 = getMovieService.getMovie(resp, id);
		return resp2;
	}

	@RequestMapping("/all")
	public ResponseWrapper getMovie(@RequestParam(value = "genreId", defaultValue = "") String genreId) {
		// If no Category id specified, bring them all back (default)
		if (genreId.equals("")) {
			resp = getMovieService.getMovies(resp);
		} else {
			resp = getMovieService.getMovies(resp, genreId);
		}
		log.info("Called:: /movie/all with genreId=" + genreId);
		log.info("Returning:: " + resp);
		return resp;
	}

	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public ResponseWrapper createMovie(@RequestBody Movie movie) {
		log.info("CREATING -> {}", movie);

		return createMovieService.createMovie(resp, movie);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseWrapper updateMovie(@RequestBody Movie movie) {
		log.info("UPDATING -> {}", movie);

		return updateMovieService.updateMovie(resp, movie);
	}

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseWrapper deleteMovie(@RequestBody Movie movie) {
		log.info("DELETING -> {}", movie);
		return deleteMovieService.deleteMovie(resp, movie);
	}
}