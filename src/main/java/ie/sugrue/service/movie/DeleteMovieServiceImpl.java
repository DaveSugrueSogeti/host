package ie.sugrue.service.movie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ie.sugrue.domain.Movie;
import ie.sugrue.domain.ResponseWrapper;
import ie.sugrue.repository.MovieRepository;

@Service("deleteMovieService")
@Scope("prototype")
public class DeleteMovieServiceImpl implements DeleteMovieService {

	private final Logger	log	= LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MovieRepository	movieRepo;

	@Override
	public ResponseWrapper deleteMovie(ResponseWrapper resp, Movie movie) {

		try {
			if (movie.getId() > 0) {
				movieRepo.deleteMovie(movie.getId());
			} else {
				log.error("Cannot identify Movie to be deleted when trying to delete {} from Database", movie);
				resp.getStatus().updateStatus(1, "We encountered a problem deleting Movie details from our database. Please try again.");
			}
		} catch (EmptyResultDataAccessException erdae) {
			log.info("Problem occured deleting movie with id of {} from DB - ", movie.getId(), erdae);
			resp.getStatus().updateStatus(1, "Movie cannot be deleted as it does not exist.");
		} catch (DataAccessException dae) {
			log.error("Data Access exception encountered trying to delete {} from Database", movie, dae);
			resp.getStatus().updateStatus(2, "We encountered a problem deleting Movie details from our database. Please try again.");
		} catch (Exception e) {
			log.error("Unknown exception encountered trying to delete {} to Database", movie, e);
			resp.getStatus().updateStatus(2, "We encountered a problem deleting Movie details from our database. Please try again.");
		}

		return resp;
	}
}
