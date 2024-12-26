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

public class DependencyResolver {
    private final FileReader fileReader;
    private final DependencyGraphBuilder graphBuilder;
    private final DependencyGraphPrinter graphPrinter;

    public DependencyResolver() {
        this.fileReader = new FileReader();
        this.graphBuilder = new DependencyGraphBuilder();
        this.graphPrinter = new DependencyGraphPrinter();
    }

    public String resolveAndPrint(String filePath)
            throws IOException, JsonParseException, CircularDependencyException {
        List<GraphNode> resolvedGraph = resolve(filePath);
        return graphPrinter.print(resolvedGraph);
    }

    public List<GraphNode> resolve(String filePath)
            throws IOException, JsonParseException, CircularDependencyException {
        StringBuilder content = fileReader.readFile(filePath);
        JsonParser parser = new JsonParser(content);
        Map<String, List<String>> dependencyMap = parser.parse();
        return graphBuilder.buildGraph(dependencyMap);
    }
}
