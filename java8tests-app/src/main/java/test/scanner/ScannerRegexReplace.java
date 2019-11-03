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
            "                    \"dependentId\": \"c7ea1608-eb4b-4859-9bd3-1dd8989e74e6\",\n" +
            "                    \"values\": [\"Yes\"],\n" +
            "                    \"fieldMirror\": \"fa14291d-3d77-41f6-9e09-e720404921af\"\n" +
            "                },\n" +
            "                \"hasOther\": false\n" +
            "            }";
    private static JsonNode jsonNode = null;
    private static final Pattern uuidRegexPattern = Pattern.compile("[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}");
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        uuidMap.put(UUID.fromString("c7ea1608-eb4b-4859-9bd3-1dd8989e74e6"), UUID.fromString("07d67128-fe2b-11e9-8f0b-362b9e155667"));
        uuidMap.put(UUID.fromString("fa14291d-3d77-41f6-9e09-e720404921af"), UUID.fromString("07d674ac-fe2b-11e9-8f0b-362b9e155667"));
        uuidMap.put(UUID.fromString("07d675d8-fe2b-11e9-8f0b-362b9e155667"), UUID.fromString("07d67704-fe2b-11e9-8f0b-362b9e155667"));
        uuidMap.put(UUID.fromString("07d679b6-fe2b-11e9-8f0b-362b9e155667"), UUID.fromString("07d67b00-fe2b-11e9-8f0b-362b9e155667"));

        try {
            jsonNode = objectMapper.readTree(jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private static JsonNode updateFieldReferences(JsonNode jsonNode, Map<UUID, UUID> uuidMap) throws JsonProcessingException {
        String jsonString = jsonNode.toPrettyString();
        StringBuilder stringBuilder = new StringBuilder(jsonString);
        final Matcher matcher = uuidRegexPattern.matcher(jsonString);
        while(matcher.find()){
            final String uuidMatch = jsonString.substring(matcher.start(), matcher.end());
            System.out.println(String.format("\n%s", uuidMatch));
            stringBuilder.replace(matcher.start(), matcher.end(), uuidMap.get(UUID.fromString(uuidMatch)).toString());
        }
        System.out.println("result is: \n" + stringBuilder.toString());
        return objectMapper.readTree(stringBuilder.toString());
    }

    public static void main(String[] args) throws JsonProcessingException {
        System.out.println("================= INIT JSON PARSER =================");
        System.out.println(jsonNode.toPrettyString());
        updateFieldReferences(jsonNode, uuidMap);
        System.out.println("=================  END JSON PARSER =================");
    }
}
