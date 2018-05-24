package xyz.welab.wmap.services;

import org.springframework.stereotype.Service;
import xyz.welab.wmap.domain.Node;
import xyz.welab.wmap.repositories.NodeRepository;

import javax.annotation.Resource;

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

    public Node findById(String nid){
        Node node = nodeRepository.findbyid(nid);
        return node;
    }
}

