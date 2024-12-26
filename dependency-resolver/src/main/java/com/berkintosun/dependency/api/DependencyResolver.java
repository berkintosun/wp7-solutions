package com.berkintosun.dependency.api;


import java.util.ArrayList;
import java.util.List;

public class DependencyResolver {

    public String resolveAndPrint(String filePath){
        return "pretty string";
    }

    public List<GraphNode> resolve(String filePath){
        return new ArrayList();
    }
}
