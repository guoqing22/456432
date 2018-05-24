package xyz.welab.wmap.repositories;

import java.util.Collection;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.welab.wmap.domain.Movie;

/**
 *@Author:guoqing22
 *@Description:
 *@Date:13:10 2018/1/31
 */

@RepositoryRestResource(collectionResourceRel = "movies", path = "movies")
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {

	/**
	 * findByTitle 精确查询
	 * @param title 标题
	 * @return
	 */
	@ResponseBody
	//@Query("MATCH (m:Movie) WHERE m.title = {title} RETURN m")
	@Query("MATCH (m:Movie)<-[r:ACTED_IN]-(a:Person) WHERE  m.title = {title} RETURN m,r,a")
	Movie findByTitle(@Param("title") String title);

	/**
	 * findByTitleLike 模糊查询
	 * @param title 标题
	 * @return
	 */
	@Query("MATCH (m:Movie) WHERE m.title =~ ('(?i).*'+{title}+'.*') RETURN m")
	Collection<Movie> findByTitleLike(@Param("title") String title);

	/**
	 * graph
	 * @param limit 限制
	 * @return
	 */
	@Query("MATCH (m:Movie)<-[r:ACTED_IN]-(a:Person) RETURN m,r,a LIMIT {limit}")
	Collection<Movie> graph(@Param("limit") int limit);
}

