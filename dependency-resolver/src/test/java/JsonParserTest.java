import com.berkintosun.dependency.api.exception.JsonParseException;
import com.berkintosun.dependency.parser.json.JsonParser;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class JsonParserTest {

    @Test
    public void testValidJsonParsing() {
        String json = "{" +
                "\"pkg1\": [\"pkg2\", \"pkg3\"]," +
                "\"pkg2\": [\"pkg3\", \"pkg4\"]," +
                "\"pkg3\": [\"pkg4\"]," +
                "\"pkg4\": []" +
                "}";

        JsonParser parser = new JsonParser(new StringBuilder(json));
        Map<String, List<String>> result = parser.parse();

        assertNotNull(result);
        assertEquals(4, result.size());
        assertEquals(Arrays.asList("pkg2", "pkg3"), result.get("pkg1"));
        assertEquals(Arrays.asList("pkg3", "pkg4"), result.get("pkg2"));
        assertEquals(Collections.singletonList("pkg4"), result.get("pkg3"));
        assertEquals(Collections.emptyList(), result.get("pkg4"));
    }

    @Test
    public void testEmptyJson() {
        String json = "{}";

        JsonParser parser = new JsonParser(new StringBuilder(json));
        Map<String, List<String>> result = parser.parse();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testInvalidJsonMissingBraces() {
        String json = "\"pkg1\": [\"pkg2\", \"pkg3\"]";

        JsonParser parser = new JsonParser(new StringBuilder(json));

        assertThrows(JsonParseException.class, parser::parse);
    }

    @Test
    public void testInvalidJsonExtraComma() {
        String json = "{" +
                "\"pkg1\": [\"pkg2\", \"pkg3\"]," +
                "}";

        JsonParser parser = new JsonParser(new StringBuilder(json));

        assertThrows(JsonParseException.class, parser::parse);
    }

    @Test
    public void testUnsupportedJsonArrayRoot() {
        String json = "[\"pkg1\", \"pkg2\"]";

        JsonParser parser = new JsonParser(new StringBuilder(json));

        assertThrows(JsonParseException.class, parser::parse);
    }

    @Test
    public void testJsonWithEmptyArrays() {
        String json = "{" +
                "\"pkg1\": []," +
                "\"pkg2\": []" +
                "}";

        JsonParser parser = new JsonParser(new StringBuilder(json));
        Map<String, List<String>> result = parser.parse();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(Collections.emptyList(), result.get("pkg1"));
        assertEquals(Collections.emptyList(), result.get("pkg2"));
    }

    @Test
    public void testJsonWithWhitespace() {
        String json = " { \n" +
                "  \"pkg1\": [ \"pkg2\", \"pkg3\"  ] , \n" +
                "  \"pkg2\": [ \"pkg3\", \"pkg4\" ] \n" +
                " } ";

        JsonParser parser = new JsonParser(new StringBuilder(json));
        Map<String, List<String>> result = parser.parse();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(Arrays.asList("pkg2", "pkg3"), result.get("pkg1"));
        assertEquals(Arrays.asList("pkg3", "pkg4"), result.get("pkg2"));
    }
}
