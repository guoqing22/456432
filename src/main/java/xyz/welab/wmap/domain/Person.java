package xyz.welab.wmap.domain;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 *@Author:guoqing22
 *@Description:
 *@Date:13:09 2018/1/31
 */

//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity
public class Person {

	@GraphId
	private Long id;

	private String name;

	private int born;

	@Relationship(type = "ACTED_IN")
	private List<Movie> movies = new ArrayList<>();

	public Person() {
	}

	public Person(String name) {

		this.name = name;
	}


	public String getName() {
		return name;
	}

	public int getBorn() {
		return born;
	}

	public List<Movie> getMovies() {
		return movies;
	}
}
