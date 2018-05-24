package xyz.welab.wmap.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @package: com.welab.qing.graph.domain
 * @author: qing
 * @date: 2018/5/10
 * @time: 10:47
 **/
@RelationshipEntity(type = "Line")
public class Rols {

    @GraphId
    private Long id;

    private String relations;

    @StartNode
    private Node start;

    @EndNode
    private Node end;

    public String getRelations() {
        return relations;
    }

    public Rols() {
    }

    public Rols(Node start, Node end) {
        this.start = start;
        this.end = end;
    }

    public Long getId() {
        return id;
    }

//    public Collection<String> getRelation() {
//        return relations;
//    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }

    public void addRoleName(String name) {
        this.relations = name;
    }
}
