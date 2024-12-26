package com.berkintosun.dependency;

import com.berkintosun.dependency.api.GraphNode;
import com.berkintosun.dependency.sorter.TopologicalSorter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DependencyGraphBuilder {
    private final Map<String, GraphNode> adjacencyList;

    public DependencyGraphBuilder() {
        this.adjacencyList = new HashMap<>();
    }

    public List<GraphNode> buildGraph(Map<String, List<String>> dependencyMap) {
        createNodes(dependencyMap);
        addDependencies(dependencyMap);
        return new TopologicalSorter(new ArrayList<>(adjacencyList.values())).sort();
    }

    private void createNodes(Map<String, List<String>> dependencyMap) {
        adjacencyList.clear();
        dependencyMap.forEach((name, deps) -> {
            adjacencyList.putIfAbsent(name, new GraphNode(name));
            deps.forEach(dep -> adjacencyList.putIfAbsent(dep, new GraphNode(dep)));
        });
    }

    private void addDependencies(Map<String, List<String>> dependencyMap) {
        dependencyMap.forEach((name, deps) -> {
            GraphNode graphNode = adjacencyList.get(name);
            deps.forEach(dep -> {
                GraphNode dependent = adjacencyList.get(dep);
                dependent.addOutEdge(graphNode);
            });
        });
    }
}