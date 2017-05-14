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

import ie.sugrue.domain.Genre;
import ie.sugrue.domain.ResponseWrapper;
import ie.sugrue.service.genre.CreateGenreService;
import ie.sugrue.service.genre.DeleteGenreService;
import ie.sugrue.service.genre.GetGenreService;
import ie.sugrue.service.genre.UpdateGenreService;

@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
@RequestMapping("/genre")
@Scope("request")
public class GenreController extends PrimaryController {

	private final Logger	log	= LoggerFactory.getLogger(this.getClass());

	@Autowired
	ResponseWrapper			resp;
	@Autowired
	GetGenreService			getGenreService;
	@Autowired
	CreateGenreService		createGenreService;
	@Autowired
	UpdateGenreService		updateGenreService;
	@Autowired
	DeleteGenreService		deleteGenreService;

	@RequestMapping("")
	public ResponseWrapper getGenre(@RequestParam(value = "id", defaultValue = "") String id) {
		// If no id specified, bring them all back (default)
		if (id.equals("")) {
			return getGenreService.getGenres(resp);
		} else {
			return getGenreService.getGenre(resp, id);
		}
	}

	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public ResponseWrapper createGenre(@RequestBody Genre genre) {
		log.info("CREATING -> {}", genre);

		return createGenreService.createGenre(resp, genre);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseWrapper updateGenre(@RequestBody Genre genre) {
		log.info("UPDATING -> {}", genre);

		return updateGenreService.updateGenre(resp, genre);
	}

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseWrapper deleteGenre(@RequestBody Genre genre) {
		log.info("DELETING -> {}", genre);
		return deleteGenreService.deleteGenre(resp, genre);
	}
}