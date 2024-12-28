## [Task 3: Java Library for Dependency Resolution](dependency-resolver/)

### Problem Statement
A Java library was developed to read JSON content describing package dependencies, resolve the full dependency graph, and provide a pretty string representation.

### Implementation Details
- Language Used: **Java (21)**
- Dependencies: None (only Java's standard libraries).
- Approach:
    1. **Load Dependencies:** Reads JSON content from a file.
    2. **Build The Graph:** From the read content, we build a graph network that the dependencies points to the node.
    3. **Resolve Graph:** Applying Kahn's algorithm to traverse nodes without incoming edges to resolve the full graph and achieve topological order and detects cyclic dependency if there are any.
    4. **Pretty Representation:** Provides a string representation of the dependency graph.
- Complexity:
  - JSON Parsing would take time complexity of O(N) where n is the amount of the characters the json file contains and O(k) as space complexity where k is the amount of dependencies the json would have
  - Building the graph would take O(V+E) time complexity and O(V) memory complexity to preserve graph nodes in a hashmap
  - Topological sort takes O(V+E) time complexity and O(V) memory complexity for containing result and the usage of queue.

### JSON Parsing Limitation

For the `dependency-resolver` project, the `JsonParser` implementation only supports JSON objects (`{}`) as the root structure. It does not support JSON arrays (`[]`) as the root. For example:

- **Supported:** `{ "pkg1": ["pkg2", "pkg3"], "pkg2": ["pkg3"], "pkg3": [] }`
- **Not Supported:** `[ { "pkg1": ["pkg2"] }, { "pkg2": [] } ]`

### Exposure Management
The Dependency Resolver project is designed using Java modules to ensure efficient exposure management.

#### API Package
When the module is used as a library or submodule, only the **API package** will be exposed. The API package contains the public interfaces and classes that define the functionality of the module. These are the components that external consumers can interact with.

#### Usage as Library / Submodule
When used as a library or submodule, consumers will have access solely to the exposed API. The internal modules and implementations remain hidden, preventing accidental misuse and providing a higher level of abstraction.

### Thoughts & Assumptions
- Input JSON file only uses JSON object annotation `{}` as the root because the provided example and the text matched with this behaviour.
- Circular dependencies are handled as well.
- Thought about adding cli support and allow to manual test through cli, but I feel like it would break the concept of developing the library.

### Testing
- Created unit tests for various input scenarios & tests for json parsing as well.

---