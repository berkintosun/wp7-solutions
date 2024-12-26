package com.berkintosun.dependency.api;

import com.berkintosun.dependency.DependencyGraphBuilder;
import com.berkintosun.dependency.DependencyGraphPrinter;
import com.berkintosun.dependency.api.exception.CircularDependencyException;
import com.berkintosun.dependency.api.exception.JsonParseException;
import com.berkintosun.dependency.parser.json.JsonParser;
import com.berkintosun.dependency.reader.FileReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * The {@code DependencyResolver} class provides functionality to parse a JSON file
 * containing a dependency graph representation, resolve the graph
 * and print the resolved output in a readable shape.
 * <p>
 * The JSON file should define an object with attributes where attributes represent package names
 * and values are arrays of dependencies for those packages. This class supports resolving dependencies
 * while handling circular dependencies and invalid JSON formats.
 * </p>
 */
public class DependencyResolver {
    private final FileReader fileReader;
    private final DependencyGraphBuilder graphBuilder;
    private final DependencyGraphPrinter graphPrinter;

    public DependencyResolver() {
        this.fileReader = new FileReader();
        this.graphBuilder = new DependencyGraphBuilder();
        this.graphPrinter = new DependencyGraphPrinter();
    }

    /**
     * Resolves the dependency graph from a given JSON file and returns a string
     * representation of the fully resolved graph.
     *
     * @param filePath The path to the JSON file containing the dependency graph.
     * @return A pretty-printed string representation of the resolved dependency graph.
     * @throws IOException                    If the file cannot be read.
     * @throws JsonParseException             If the JSON format is invalid.
     * @throws CircularDependencyException    If circular dependencies are detected.
     */
    public String resolveAndPrint(String filePath)
            throws IOException, JsonParseException, CircularDependencyException {
        List<GraphNode> resolvedGraph = resolve(filePath);
        return graphPrinter.print(resolvedGraph);
    }

    /**
     * Resolves the dependency graph from a given JSON file and returns the resolved graph
     * as a list of {@link GraphNode} objects.
     *
     * @param filePath The path to the JSON file containing the dependency graph.
     * @return A list of {@link GraphNode} objects representing the resolved dependency graph.
     * @throws IOException                    If the file cannot be read.
     * @throws JsonParseException             If the JSON format is invalid.
     * @throws CircularDependencyException    If circular dependencies are detected.
     */
    public List<GraphNode> resolve(String filePath)
            throws IOException, JsonParseException, CircularDependencyException {
        StringBuilder content = fileReader.readFile(filePath);
        JsonParser parser = new JsonParser(content);
        Map<String, List<String>> dependencyMap = parser.parse();
        return graphBuilder.buildGraph(dependencyMap);
    }
}
