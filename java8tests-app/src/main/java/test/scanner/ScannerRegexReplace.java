package test.scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScannerRegexReplace {

    private static final Map<UUID, UUID> uuidMap = new HashMap<>();
    private static final String jsonString = "{\n" +
            "                \"row\": 1,\n" +
            "                \"column\": 4,\n" +
            "                \"colSpan\": 1,\n" +
            "                \"maxLength\": 100,\n" +
            "                \"editable\": true,\n" +
            "                \"configurable\": false,\n" +
            "                \"moveable\": false,\n" +
            "                \"mirrorOn\": {\n" +
            "                \"showOn\": {\n" +
            "                    \"dependentId\": \"afe4c796-1f5b-43ae-a8e5-d92420d483ce\",\n" +
            "                    \"values\": [\"Yes\"]\n" +
            "                }," +
            "                \"dependentFields\": [\"143d3314-43b2-4711-899f-843e223d1128\", \"db63dccf-bcb3-4c3a-860f-925ef5a94180\"],\n" +
            "                    \"dependentId\": \"c7ea1608-eb4b-4859-9bd3-1dd8989e74e6\",\n" +
            "                    \"values\": [\"Yes\"],\n" +
            "                    \"fieldMirror\": \"fa14291d-3d77-41f6-9e09-e720404921af\"\n" +
            "                },\n" +
            "                \"hasOther\": false\n" +
            "            }";

    private static final String jsonWithNoRef = "{\n" +
            "                \"row\": 1,\n" +
            "                \"column\": 4,\n" +
            "                \"colSpan\": 1,\n" +
            "                \"maxLength\": 100,\n" +
            "                \"editable\": true,\n" +
            "                \"configurable\": false,\n" +
            "                \"moveable\": false,\n" +
            "                \"hasOther\": false\n" +
            "            }";
    private static JsonNode jsonNode = null;
    private static JsonNode jsonNodeNoRef = null;
    private static final Pattern uuidRegexPattern = Pattern.compile("[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}");
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        uuidMap.put(UUID.fromString("c7ea1608-eb4b-4859-9bd3-1dd8989e74e6"), UUID.fromString("07d67128-fe2b-11e9-8f0b-362b9e155667"));
        uuidMap.put(UUID.fromString("fa14291d-3d77-41f6-9e09-e720404921af"), UUID.fromString("07d674ac-fe2b-11e9-8f0b-362b9e155667"));
        uuidMap.put(UUID.fromString("143d3314-43b2-4711-899f-843e223d1128"), UUID.fromString("07d67704-fe2b-11e9-8f0b-362b9e155667"));
        uuidMap.put(UUID.fromString("db63dccf-bcb3-4c3a-860f-925ef5a94180"), UUID.fromString("07d67b00-fe2b-11e9-8f0b-362b9e155667"));
        uuidMap.put(UUID.fromString("afe4c796-1f5b-43ae-a8e5-d92420d483ce"), UUID.fromString("1b011b35-20a3-490d-8d97-2aac9c162267"));


        try {
            jsonNode = objectMapper.readTree(jsonString);
            jsonNodeNoRef = objectMapper.readTree(jsonWithNoRef);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private static JsonNode updateFieldReferences(final JsonNode jsonNode, final Map<UUID, UUID> uuidMap) throws JsonProcessingException {
        final String jsonString = jsonNode.toString();
        final StringBuilder stringBuilder = new StringBuilder(jsonString);
        final Matcher matcher = uuidRegexPattern.matcher(jsonString);
        while(matcher.find()){
            final String uuidMatch = jsonString.substring(matcher.start(), matcher.end());
            stringBuilder.replace(matcher.start(), matcher.end(), uuidMap.get(UUID.fromString(uuidMatch)).toString());
        }
        return objectMapper.readTree(stringBuilder.toString());
    }

    private static JsonNode updateFieldReferencesFound(final JsonNode jsonNode, final Map<UUID, UUID> uuidMap) throws JsonProcessingException {
        final String jsonString = jsonNode.toString();
        final Matcher matcher = uuidRegexPattern.matcher(jsonString);
        final StringBuilder result = new StringBuilder();
        while(matcher.find()){
            matcher.appendReplacement(result, uuidMap.get(UUID.fromString(matcher.group())).toString());
        }
        matcher.appendTail(result);
        return objectMapper.readTree(result.toString());
    }

    public static void main(String[] args) throws JsonProcessingException {
        System.out.println("================= INIT JSON WITH REF PARSER =================");
        System.out.println(jsonNode.toPrettyString());
        final JsonNode updatedJsonNode = updateFieldReferencesFound(ScannerRegexReplace.jsonNode, uuidMap);
        System.out.println(updatedJsonNode.toPrettyString());
        System.out.println("=================  END JSON WITH REF PARSER =================");
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("================= INIT JSON WITH NO REF PARSER =================");
        System.out.println(jsonNodeNoRef.toPrettyString());
        final JsonNode updatedJsonNodeNoRef = updateFieldReferencesFound(ScannerRegexReplace.jsonNodeNoRef, uuidMap);
        System.out.println(updatedJsonNodeNoRef.toPrettyString());
        System.out.println("================= END JSON WITH NO REF PARSER =================");
    }
}
