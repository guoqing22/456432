package xyz.welab.wmap.repositories;

import xyz.welab.wmap.domain.Node;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @package: com.welab.qing.graph.repositories
 * @author: qing
 * @date: 2018/5/10
 * @time: 10:27
 **/

@RepositoryRestResource(collectionResourceRel = "nodes", path = "nodes")
public interface NodeRepository extends PagingAndSortingRepository<Node, Long> {

    /**
     * findByID 根据ID查找
     * @param nid ID
     * @return
     */
    //@ResponseBody
    @Query("MATCH (n:Node) WHERE n.nid = {nid} RETURN n")
    Node findbyid(@Param("nid") String nid);

    /**
     * 根据nid模糊查找
     * @param nid 节点编号
     * @return
     */
    @Query("MATCH (n:Node) WHERE n.nid =~ ('(?i).*'+{nid}+'.*') RETURN n limit 5")
    Collection<Node> findbyidlike(@Param("nid") String nid);

    /**
     * 根据start_id和end_id查找最短路径
     * @param start_id
     * @param end_id
     * @return
     */
    @Query("match p=allshortestPaths((a:Node)-[*..1000]->(b:Node)) where a.nid = {start_id} and b.nid = {end_id} return p")
    Collection<Node> graph(@Param("start_id") String start_id, @Param("end_id") String end_id);
}