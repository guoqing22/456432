package xyz.welab.wmap.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.welab.wmap.domain.Node;
import xyz.welab.wmap.repositories.NodeRepository;
import xyz.welab.wmap.services.NodeService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @package: xyz.welab.wmap.controller
 * @author: qing
 * @date: 2018/5/17
 * @time: 10:08
 **/
@Controller
public class NodeController {
    @Resource
    private NodeService nodeService;

    @RequestMapping("/getnode" )
    @ResponseBody
    public Node getNode(@RequestBody Node n, HttpServletRequest request){
        System.out.println(n.getNid());
        Node node =  nodeService.findById(n.getNid());
        if (node == null){
            System.out.println(n.getNid()+"不存在");
        }
        else{
            System.out.println(n.getNid()+"存在");
        }
        return node;
    }
}
