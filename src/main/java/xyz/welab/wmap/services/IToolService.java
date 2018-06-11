package xyz.welab.wmap.services;

import java.util.List;
import java.util.Map;

/**
 * @Author: qing
 * @Date: 2018/6/6
 * @Time: 17:01
 * @Description:
 **/
public interface IToolService {
    /**
     * 两点A*算法
     *
     * @param start
     * @param end
     * @return
     */
    public List<double[]> AStart(String start, String end);

    /**
     * 两点双向A*算法
     *
     * @param start
     * @param end
     * @return
     */
    public List<String> NiAStart(String start, String end);

    /**
     * 三点A*算法
     *
     * @param start
     * @param tujing
     * @param end
     * @return
     */
    public List<double[]> AStartT(String start, String tujing, String end);

    /**
     * 查找最小消耗F的节点
     *
     * @return
     */
    public String getMinFNode(String end);

    /**
     * 计算总的消耗F
     * F=G+H
     *
     * @param G 从起点移动到指定位置的移动耗费
     * @param H 从指定的位置移动到终点的预计耗费
     * @return
     */
    public double calculateF(double G, double H);

    /**
     * 计算从起点移动当前节点的的移动耗费G
     *
     * @param start   起点
     * @param current 当前节点
     * @return
     */
    public double calculateG(String start, String current);

    /**
     * 计算从当前的位置移动到终点的预计耗费H
     *
     * @param current 指定节点
     * @param end     终点
     * @return
     */
    public double calculateH(String current, String end);

    /**
     * 查找相邻的节点坐标
     *
     * @return
     * @oaram currentNode 当前节点
     */
    public List<String> findNeighborNodes(String parent);

    /**
     * 添加到OpenList
     *
     * @param node
     */
    public void addOpenList(String node);

    /**
     * 从OpenList中移除
     *
     * @param node
     */
    public void removeOpenList(String node);

    /**
     * 添加到CloseList
     *
     * @param nid
     */
    public void addCloseList(String nid);
}
