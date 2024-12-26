package com.berkintosun.dependency.api;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * The {@code GraphNode} class represents a node in a dependency graph.
 * Each node has a name, a set of outgoing edges and an in-degree
 * representing the number of incoming edges.
 * <p>
 * This class is primarily used to model and manipulate nodes in a directed graph structure,
 * where edges represent dependencies between nodes.
 * </p>
 */
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