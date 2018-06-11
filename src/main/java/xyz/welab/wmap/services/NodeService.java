package xyz.welab.wmap.services;

import org.springframework.stereotype.Service;
import xyz.welab.wmap.domain.Node;
import xyz.welab.wmap.repositories.NodeRepository;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @package: com.welab.qing.graph.services
 * @author: qing
 * @date: 2018/5/10
 * @time: 10:43
 **/
@Service
public class NodeService {
    @Resource
    NodeRepository nodeRepository;

    /**
     * 根据nid精确查找
     * @param nid 节点编号
     * @return
     */
    public Node findById(String nid){
        Node node = nodeRepository.findbyid(nid);
        return node;
    }

    /**
     * 根据nid模糊查找
     * @param nid 节点编号
     * @return
     */
    public Collection<Node> findByIdLike(String nid){
        Collection<Node> node = nodeRepository.findbyidlike(nid);
        return node;
    }

    public Map<String,Map<String,Object>> findPath(String start,String end){
        Collection<Node> nodes = nodeRepository.graph(start,end);

        Map<String,Map<String,Object>> path = new HashMap<>();
        for(Node node : nodes){

            Map<String,Object> pathInfo = new HashMap<>();
            String nid = node.getNid();
            String lat = node.getLat();
            String lon = node.getLon();
            System.out.println("+++"+nid);
            List<String> adj = node.getAdj();
            pathInfo.put("nid",nid);
            pathInfo.put("lat",lat);
            pathInfo.put("lon",lon);
            pathInfo.put("adj",adj);
            path.put(nid,pathInfo);
        }
        return path;
    }
}

