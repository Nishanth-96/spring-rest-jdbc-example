package main.java.dao;

import java.util.List;

import main.java.model.Movie;

public interface MovieDAO {

	/* POST Request Method */
	int save(Movie movie);
	
	/* DELETE Request Method */
	int delete(Movie movie);
	
	int update(Movie movie);
	
	/* GET Request methods */
	List<Movie> list();
	Movie list(Movie movie);
}
