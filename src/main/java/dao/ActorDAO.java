package main.java.dao;

import java.util.List;

import main.java.model.Actor;
import main.java.model.Movie;

public interface ActorDAO {

	int save(Actor actor);
	int delete(Actor actor);
	int update(Actor actor);
	List<Actor> list(Movie movie);

	/* GET Request Methods */
	List<Actor> list();
	List<Actor> list(Actor actor);
}
