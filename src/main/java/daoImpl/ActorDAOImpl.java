package main.java.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import main.java.dao.ActorDAO;
import main.java.model.Actor;
import main.java.model.Movie;

public class ActorDAOImpl implements ActorDAO{

	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public int save(Actor actor) {
		// TODO Auto-generated method stub
		String query = "insert into Actors values(\"" + actor.getActorName()+"\",\"" + actor.getMovieName() + "\")";
		return jdbcTemplate.update(query);
	}

	@Override
	public int delete(Actor actor) {
		// TODO Auto-generated method stub
		String query = "delete from Actors where actor_name =\"" + actor.getActorName() + "\" AND movie_name=\"" + actor.getMovieName() +"\"";
		return jdbcTemplate.update(query);
	}

	@Override
	public int update(Actor actor) {
		// TODO Auto-generated method stub
		String query = "update Actors set actor_name=\"" +actor.getActorName() + "\",movie_name=\""+actor.getMovieName()
		+"\" where actor_name=\"" + actor.getActorName() + "\"";
		return jdbcTemplate.update(query);
	}

	@Override
	public List<Actor> list() {
		// TODO Auto-generated method stub
		String query = "select * from Actors";
		List<Actor> actors = new ArrayList<>();
		jdbcTemplate.query(query,new ResultSetExtractor<List<Actor>>() {
			@Override
			public List<Actor> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				while(rs.next()) {
					Actor actor = new Actor();
					actor.setActorName(rs.getString(1));
					actor.setMovieName(rs.getString(2));
					actors.add(actor);
				}
				return null;
			}
		});
		return actors;
	}

	@Override
	public List<Actor> list(Movie movie) {
		// TODO Auto-generated method stub
		String query = "select * from Actors where movie_name=\"" + movie.getMovieName() + "\"";
		List<Actor> actors = new ArrayList<>();
		jdbcTemplate.query(query,new ResultSetExtractor<List<Actor>>() {
			@Override
			public List<Actor> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				while(rs.next()) {
					Actor actor = new Actor();
					actor.setActorName(rs.getString(1));
					actor.setMovieName(rs.getString(2));
					actors.add(actor);
				}
				return null;
			}
		});
		return actors;
	}

	@Override
	public List<Actor> list(Actor actor) {
		// TODO Auto-generated method stub
		String query = "select * from Actors where actor_name=\"" + actor.getActorName() +"\"";
		List<Actor> actors = new ArrayList<>();
		jdbcTemplate.query(query,new ResultSetExtractor<List<Actor>>() {
			@Override
			public List<Actor> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				while(rs.next()) {
					Actor actor = new Actor();
					actor.setActorName(rs.getString(1));
					actor.setMovieName(rs.getString(2));
					actors.add(actor);			
				}
				return null;
			}
		});
		return actors;
	}
	
}
