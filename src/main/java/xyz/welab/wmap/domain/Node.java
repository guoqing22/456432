package xyz.welab.wmap.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @package: com.welab.qing.graph.domain
 * @author: qing
 * @date: 2018/5/10
 * @time: 10:22
 **/
public class Node {
    @GraphId
    private Long id;

    private String nid;

    private String lon;

    private List<String> adj;

    private String lat;
    @Relationship(type = "Line",direction = Relationship.INCOMING)
    private List<Rols> relations = new ArrayList<>();

    public Node() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Node(String nid) {

        this.nid = nid;
    }

    public Collection<Rols> getRelations() {
        return relations;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid){
        this.nid = nid;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public List<String> getAdj() {
        return adj;
    }

    public void addRole(Rols relations) {
        this.relations.add(relations);
    }

}
