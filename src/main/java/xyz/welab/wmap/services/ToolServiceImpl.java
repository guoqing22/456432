package xyz.welab.wmap.services;

import org.springframework.stereotype.Service;
import xyz.welab.wmap.domain.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Math.sqrt;

/**
 * @Author: qing
 * @Date: 2018/6/6
 * @Time: 16:34
 * @Description:
 **/
@Service("ToolService")
public class ToolServiceImpl  implements IToolService {

    private List<String> openList = new ArrayList<String>();
    private List<String> closeList = new ArrayList<String>();

    private List<double[]> pathList = new ArrayList<double[]>();
    private double[] latlon;
    private double G = 0;
    private double F = 0;

    private String min_id;

    private Map<String, Map<String, Object>> path;

    /**
     * 获得path
     *
     * @param path
     */
    public void setPath(Map<String, Map<String, Object>> path) {
        this.path = path;
    }

    /**
     * 两点A*算法
     *
     * @param start_id
     * @param end_id
     * @return
     */
    @Override
    public List<double[]> AStart(String start_id, String end_id) {
        System.out.println(start_id);
        System.out.println(end_id);
        openList = new ArrayList<String>();
        closeList = new ArrayList<String>();
        pathList = new ArrayList<double[]>();

        latlon = new double[]{getLat(start_id), getLon(start_id)};
        pathList.add(latlon);
        openList.add(start_id);
        closeList.add(start_id);

        min_id = start_id;
        while (!closeList.contains(end_id)){
            String parent_id = closeList.get(closeList.size()-1);
            System.out.println(closeList.size());
            System.out.println(parent_id);
            List<String> neighborNodes = findNeighborNodes(parent_id);

            for (String node_id : neighborNodes) {
                if (closeList.contains(node_id)) {
                    continue;
                } else {
                    addOpenList(node_id);
                }
            }
            min_id = getMinFNode(end_id);
            closeList.add(min_id);
            latlon = new double[]{getLat(min_id), getLon(min_id)};
            pathList.add(latlon);
            if (closeList.contains(end_id)) {
                return pathList;
            }
            openList.clear();
        }
        return pathList;
    }

    /**
     * 两点双向A*算法
     *
     * @param start_id
     * @param end_id
     * @return
     */
    @Override
    public List<String> NiAStart(String start_id, String end_id) {
        return null;
    }

    /**
     * 三点A*算法
     *
     * @param start_id
     * @param tujing_id
     * @param end_id
     * @return
     */
    @Override
    public List<double[]> AStartT(String start_id, String tujing_id, String end_id) {
        return null;
    }


    /**
     * 查找最小消耗F的节点
     *
     * @return
     */
    @Override
    public String getMinFNode(String end_id) {
        String parent_id = closeList.get(closeList.size() - 1);
        String min_id = openList.get(0);
        System.out.println("=-=-=" + min_id);
        double temp_g = calculateG(parent_id, min_id);
        double temp_h = calculateH(min_id, end_id);
        double temp_f = calculateF(temp_g, temp_h);
        for (String current_id : openList) {
            double g = calculateG(parent_id, current_id);
            double h = calculateH(current_id, end_id);
            double f = calculateF(g, h);
            if (f < temp_f) {
                min_id = current_id;
                temp_f = f;
            }

        }
        return min_id;
    }

    /**
     * 计算总的消耗F
     * F=G+H
     *
     * @param G 从起点移动到指定位置的移动耗费
     * @param H 从指定的位置移动到终点的预计耗费
     * @return
     */
    @Override
    public double calculateF(double G, double H) {
        double F;
        F = G + H;
        return F;
    }

    /**
     * 计算从起点移动当前节点的的移动耗费G
     *
     * @param start_id   起点
     * @param current_id 当前节点
     * @return
     */
    @Override
    public double calculateG(String start_id, String current_id) {

        double start_lat = getLat(start_id);
        double start_lon = getLon(start_id);

        double current_lat = getLat(current_id);
        double current_lon = getLon(current_id);

        double x = Math.pow((start_lat - current_lat), 2);
        double y = Math.pow((start_lon - current_lon), 2);

        double G = sqrt(x + y);

        return G;
    }

    /**
     * 计算从当前的位置移动到终点的预计耗费H
     *
     * @param current_id 指定节点
     * @param end_id     终点
     * @return
     */
    @Override
    public double calculateH(String current_id, String end_id) {
        double current_lat = getLat(current_id);
        double current_lon = getLon(current_id);

        double end_lat = getLat(end_id);
        double end_lon = getLon(end_id);

        double x = Math.pow((current_lat - end_lat), 2);
        double y = Math.pow((current_lon - end_lon), 2);

        double H = sqrt(x + y);

        return H;
    }


    /**
     * 根据node id获得lat
     *
     * @param node_id
     * @return
     */
    public double getLat(String node_id) {
        System.out.println(node_id);
        System.out.println(path.get(node_id));
        System.out.println(Double.parseDouble(path.get(node_id).get("lat").toString()));
        double lat = Double.parseDouble(path.get(node_id).get("lat").toString());
        return lat;
    }

    /**
     * 根据node id获得lon
     *
     * @param node_id
     * @return
     */
    public double getLon(String node_id) {
        System.out.println(node_id);
        System.out.println(path.get(node_id));
        System.out.println(Double.parseDouble(path.get(node_id).get("lon").toString()));
        double lon = Double.parseDouble(path.get(node_id).get("lon").toString());
        return lon;
    }

    /**
     * 查找相邻的节点坐标
     *
     * @param currentNode_id 当前节点id
     * @return
     */
    @Override
    public List<String> findNeighborNodes(String currentNode_id) {

        List<String> neighborNodesList = (List<String>) path.get(currentNode_id).get("adj");

        return neighborNodesList;
    }


    /**
     * 添加到OpenList
     *
     * @param node_id
     */
    @Override
    public void addOpenList(String node_id) {
        System.out.println(node_id+"\t"+path.get(node_id)!=null);
        if (path.get(node_id)!=null){
            openList.add(node_id);
        }

    }

    /**
     * 从OpenList中移除
     *
     * @param node_id
     */
    @Override
    public void removeOpenList(String node_id) {
        openList.remove(node_id);
    }

    /**
     * 添加到CloseList
     *
     * @param nid
     */
    @Override
    public void addCloseList(String nid) {
        closeList.add(nid);
    }
}