package xyz.welab.wmap.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.welab.wmap.domain.Node;
import xyz.welab.wmap.domain.NodeParam;
import xyz.welab.wmap.repositories.NodeRepository;
import xyz.welab.wmap.services.NodeService;
import xyz.welab.wmap.services.ToolServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @package: xyz.welab.wmap.controller
 * @author: qing
 * @date: 2018/5/17
 * @time: 10:08
 **/
@Controller
public class NodeController {

    private final Logger LOGGER = LogManager.getLogger(NodeController.class);

    @Resource
    private NodeService nodeService;
    @Resource
    private ToolServiceImpl toolService;

    @RequestMapping("/getnode")
    @ResponseBody
    public Collection<Node> getNode(@RequestBody Node n, HttpServletRequest request) {
        LOGGER.info("节点id：" + n.getNid());
        LOGGER.info("查找节点");
        Collection<Node> nodes = nodeService.findByIdLike(n.getNid());
        LOGGER.info("查找完成");
        for (Node node1 : nodes) {
            System.out.println(node1.getNid());
        }
        return nodes;
    }

    @RequestMapping("/getPathTest")
    @ResponseBody
    public void test(@RequestBody NodeParam node, HttpServletRequest request) {
        System.out.println(node.getStart() + "\t" + node.getTujing() + "\t" + node.getEnd());
    }

    @RequestMapping("/getpath")
    @ResponseBody
    public List<double[]> getPath(@RequestBody NodeParam node, HttpServletRequest request) {
        System.out.println("开始" + node.getStart());
        System.out.println("开始" + node.getEnd());
        System.out.println("开始" + node.getTujing());
        if (node.getStart() != null && node.getEnd() != null && node.getTujing() == null) {
            Map<String, Map<String, Object>> path = nodeService.findPath(node.getStart(), node.getEnd());
            for (Map.Entry<String, Map<String, Object>> entry : path.entrySet()) {
                String key = entry.getKey().toString();
            }
            toolService.setPath(path);

            List<double[]> pathList = toolService.AStart(path.get(node.getStart()).get("nid").toString(), path.get(node.getEnd()).get("nid").toString());
            System.out.println("开始" + path.get(node.getStart()));
            System.out.println("开始" + path.get(node.getEnd()));
            System.out.println("开始" + pathList.size());

            return pathList;
        } else{
            System.out.println("开始----------------------------");
            Map<String, Map<String, Object>> path = nodeService.findPath(node.getStart(), node.getTujing());
            for (Map.Entry<String, Map<String, Object>> entry : path.entrySet()) {
                String key = entry.getKey().toString();
            }
            toolService.setPath(path);

            List<double[]> pathList = toolService.AStart(path.get(node.getStart()).get("nid").toString(), path.get(node.getTujing()).get("nid").toString());

            Map<String, Map<String, Object>> path1 = nodeService.findPath(node.getTujing(), node.getEnd());
            for (Map.Entry<String, Map<String, Object>> entry : path1.entrySet()) {
                String key = entry.getKey().toString();
            }
            toolService.setPath(path1);

            List<double[]> pathList1 = toolService.AStart(path1.get(node.getTujing()).get("nid").toString(), path1.get(node.getEnd()).get("nid").toString());

            pathList.addAll(pathList1);
            return pathList;
        }
    }
}