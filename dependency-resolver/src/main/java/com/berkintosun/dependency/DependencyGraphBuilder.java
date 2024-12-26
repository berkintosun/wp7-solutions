package com.berkintosun.dependency;

import com.berkintosun.dependency.api.GraphNode;
import com.berkintosun.dependency.sorter.TopologicalSorter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code DependencyGraphBuilder} class is responsible for constructing a graph
 * from a parsed JSON representation of package dependencies. The input is a map where keys represent
 * package names, and values are lists of dependencies for each package.
 * <p>
 * This class supports the following operations:
 * </p>
 * <ul>
 *     <li>Creates graph nodes for all packages and their dependencies.</li>
 *     <li>Adds directed edges between nodes to represent the dependency relationships.</li>
 *     <li>Performs topological sorting to ensure that the dependencies are resolved in the correct order.</li>
 * </ul>
 */
public class DependencyGraphBuilder {
    private final Map<String, GraphNode> nodeMap;

    public DependencyGraphBuilder() {
        this.nodeMap = new HashMap<>();
    }

    public List<GraphNode> buildGraph(Map<String, List<String>> dependencyMap) {
        createNodes(dependencyMap);
        addDependencies(dependencyMap);
        return new TopologicalSorter(new ArrayList<>(nodeMap.values())).sort();
    }

    private void createNodes(Map<String, List<String>> dependencyMap) {
        nodeMap.clear();
        dependencyMap.forEach((name, deps) -> {
            nodeMap.putIfAbsent(name, new GraphNode(name));
            deps.forEach(dep -> nodeMap.putIfAbsent(dep, new GraphNode(dep)));
        });
    }

    private void addDependencies(Map<String, List<String>> dependencyMap) {
        dependencyMap.forEach((name, deps) -> {
            GraphNode graphNode = nodeMap.get(name);
            deps.forEach(dep -> {
                GraphNode dependent = nodeMap.get(dep);
                dependent.addOutEdge(graphNode);
            });
        });
    }
}