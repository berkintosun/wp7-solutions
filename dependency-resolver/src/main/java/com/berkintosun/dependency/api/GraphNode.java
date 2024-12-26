package com.berkintosun.dependency.api;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class GraphNode {
    private final String name;
    private final Set<GraphNode> outEdges;
    private int inDegree;

    public GraphNode(String name) {
        this.name = name;
        this.outEdges = new HashSet<>();
        this.inDegree = 0;
    }

    public void addOutEdge(GraphNode graphNode) {
        if (outEdges.add(graphNode)) {
            graphNode.incrementInDegree();
        }
    }

    public void incrementInDegree() {
        inDegree++;
    }

    public void decrementInDegree() {
        inDegree--;
    }

    public String getName() {
        return name;
    }

    public Set<GraphNode> getOutEdges() {
        return Collections.unmodifiableSet(outEdges);
    }

    public int getInDegree() {
        return inDegree;
    }
}