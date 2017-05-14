package ie.sugrue.service.genre;

import ie.sugrue.domain.Genre;
import ie.sugrue.domain.ResponseWrapper;

public interface DeleteGenreService {

	ResponseWrapper deleteGenre(ResponseWrapper resp, Genre genre);

}
