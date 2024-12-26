import com.berkintosun.dependency.api.DependencyResolver;
import com.berkintosun.dependency.api.GraphNode;
import com.berkintosun.dependency.api.exception.CircularDependencyException;
import com.berkintosun.dependency.api.exception.JsonParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DependencyResolverTest {
    private Path tempDir;

    @BeforeEach
    void createTempDir() throws IOException {
        tempDir = Files.createTempDirectory("test");
    }

    @AfterEach
    void deleteTempFiles() throws IOException {
        Files.walk(tempDir)
                .sorted(Comparator.reverseOrder())
                .forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    @Test
    void testSimpleDependencyGraph() throws IOException {
        String json = """
                {
                    "pkg1": ["pkg2", "pkg3"],
                    "pkg2": ["pkg3"],
                    "pkg3": []
                }
                """;

        Path jsonFile = tempDir.resolve("simple.json");
        Files.writeString(jsonFile, json);

        DependencyResolver resolver = new DependencyResolver();
        List<GraphNode> resolved = resolver.resolve(jsonFile.toString());

        assertEquals(3, resolved.size());
        assertEquals("pkg3", resolved.get(0).getName());
        assertEquals("pkg2", resolved.get(1).getName());
        assertEquals("pkg1", resolved.get(2).getName());
    }

    @Test
    void testCircularDependency() throws IOException {
        String json = """
                {
                    "pkg1": ["pkg2"],
                    "pkg2": ["pkg1"]
                }
                """;

        Path jsonFile = tempDir.resolve("circular.json");
        Files.writeString(jsonFile, json);

        DependencyResolver resolver = new DependencyResolver();
        assertThrows(CircularDependencyException.class,
                () -> resolver.resolve(jsonFile.toString()));
    }

    @Test
    void testEmptyJson() throws IOException {
        String json = "{}";
        Path jsonFile = tempDir.resolve("empty.json");
        Files.writeString(jsonFile, json);

        DependencyResolver resolver = new DependencyResolver();
        List<GraphNode> resolved = resolver.resolve(jsonFile.toString());
        assertTrue(resolved.isEmpty());
    }

    @Test
    void testComplexDependencyGraph() throws IOException {
        String json = """
                {
                    "pkg1": ["pkg2", "pkg3"],
                    "pkg2": ["pkg3", "pkg4"],
                    "pkg3": ["pkg4"],
                    "pkg4": []
                }
                """;

        Path jsonFile = tempDir.resolve("complex.json");
        Files.writeString(jsonFile, json);

        DependencyResolver resolver = new DependencyResolver();
        List<GraphNode> resolved = resolver.resolve(jsonFile.toString());

        assertEquals(4, resolved.size());
        assertEquals("pkg4", resolved.get(0).getName());
        assertEquals("pkg3", resolved.get(1).getName());
        assertEquals("pkg2", resolved.get(2).getName());
        assertEquals("pkg1", resolved.get(3).getName());
    }

    @Test
    void testPrettyPrint() throws IOException {
        String json = """
                {
                    "pkg1": ["pkg2", "pkg3"],
                    "pkg2": ["pkg3"],
                    "pkg3": []
                }
                """;

        Path jsonFile = tempDir.resolve("pretty.json");
        Files.writeString(jsonFile, json);

        DependencyResolver resolver = new DependencyResolver();
        String output = resolver.resolveAndPrint(jsonFile.toString());

        String expected =
                "pkg1\n" +
                "├─ pkg2\n" +
                "│  └─ pkg3\n" +
                "└─ pkg3\n" +
                "pkg2\n" +
                "└─ pkg3\n" +
                "pkg3\n";

        assertEquals(expected, output);
    }

    @Test
    void testInvalidJson() throws IOException {
        String json = "{ invalid json }";
        Path jsonFile = tempDir.resolve("invalid.json");
        Files.writeString(jsonFile, json);
        DependencyResolver resolver = new DependencyResolver();

        assertThrows(JsonParseException.class,
                () -> resolver.resolve(jsonFile.toString()));
    }

    @Test
    void testFileNotFound() {
        DependencyResolver resolver = new DependencyResolver();
        assertThrows(IOException.class,
                () -> resolver.resolve("nonexistent.json"));
    }
}
