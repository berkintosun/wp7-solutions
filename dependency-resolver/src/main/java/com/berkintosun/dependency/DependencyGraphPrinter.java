package com.berkintosun.dependency;

import com.berkintosun.dependency.api.GraphNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code DependencyGraphPrinter} class is responsible for generating a readable,
 * tree-like representation of a dependency graph. It formats the output in a way that clearly
 * shows the hierarchy and relationships between packages and their dependencies.
 * <p>
 * This class works with a list of {@link GraphNode} objects, and it transforms
 * the dependency graph into a nicely indented, readable format and also prettier.
 * The result and the dependencies will be sorted by alphabetical order.
 * </p>
 * <p>
 * Example of the formatted output:
 * </p>
 * <pre>
 * pkg1
 * ├─ pkg2
 * │  └─ pkg3
 * └─ pkg3
 * pkg2
 * └─ pkg3
 * pkg3
 * </pre>
 */
public class DependencyGraphPrinter {
    StringBuilder result;
    Map<String, List<String>> dependencyList;
    List<String> nodes;

    public String print(List<GraphNode> graph) {
        result = new StringBuilder();
        dependencyList = new HashMap<>();

        for (GraphNode node : graph) {
            dependencyList.putIfAbsent(node.getName(), new ArrayList<>());
            node.getOutEdges().forEach((outNode) -> {
                dependencyList.putIfAbsent(outNode.getName(), new ArrayList<>());
                dependencyList.get(outNode.getName()).add(node.getName());
            });
        }

        nodes = dependencyList.keySet().stream().sorted().toList();
        for (String nodeName : nodes) {
            printPackageTree(nodeName, dependencyList, "", true, result, 0);
        }
        return result.toString();
    }

    private void printPackageTree(String nodeName, Map<String, List<String>> dependencyList,
                                  String prefix, boolean isLast, StringBuilder result, int level) {
        List<String> dependencies = dependencyList.get(nodeName).stream().sorted().toList();

        result.append(prefix);
        if (level > 0) {
            result.append(isLast ? "└─ " : "├─ ");
        }
        result.append(nodeName).append("\n");
        for (int i = 0; i < dependencies.size(); i++) {
            String newPrefix = prefix + (level > 0 ? (isLast ? "   " : "│  ") : "");
            printPackageTree(dependencies.get(i), dependencyList, newPrefix,
                    i == dependencies.size() - 1, result, level + 1);
        }
    }
}