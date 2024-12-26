package com.berkintosun.dependency.sorter;

import com.berkintosun.dependency.api.GraphNode;
import com.berkintosun.dependency.api.exception.CircularDependencyException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologicalSorter {
    private final List<GraphNode> GraphNodes;

    public TopologicalSorter(List<GraphNode> GraphNodes) {
        this.GraphNodes = GraphNodes;
    }

    public List<GraphNode> sort() {
        List<GraphNode> result = new ArrayList<>();
        Queue<GraphNode> queue = new LinkedList<>();

        GraphNodes.stream()
                .filter(p -> p.getInDegree() == 0)
                .forEach(queue::offer);

        while (!queue.isEmpty()) {
            GraphNode current = queue.poll();
            result.add(current);

            current.getOutEdges().forEach(dep -> {
                dep.decrementInDegree();
                if (dep.getInDegree() == 0) {
                    queue.offer(dep);
                }
            });
        }

        if (result.size() != GraphNodes.size()) {
            throw new CircularDependencyException("Circular dependency detected");
        }

        return result;
    }
}