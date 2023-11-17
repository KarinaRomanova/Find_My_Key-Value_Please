import java.util.*;

public class NestedMapSearch {

    public static List<Map<String, Object>> findKeyValuePair(Map<String, Object> data, String key, Object value) {
        List<Map<String, Object>> result = new ArrayList<>();
        searchKeyValuePair(data, key, value, result);
        return result;
    }

    private static void searchKeyValuePair(Map<String, Object> data, String key, Object value, List<Map<String, Object>> result) {
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            if (entry.getKey().equals(key) && entry.getValue().equals(value)) {
                result.add(data);
                break; 
            }

            if (entry.getValue() instanceof Map) {
                searchKeyValuePair((Map<String, Object>) entry.getValue(), key, value, result);
            } else if (entry.getValue() instanceof List) {
                for (Object item : (List<?>) entry.getValue()) {
                    if (item instanceof Map) {
                        searchKeyValuePair((Map<String, Object>) item, key, value, result);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        
        Map<String, Object> data = new HashMap<>();
        data.put("name", "John Doe");
        data.put("age", 30);

        Map<String, Object> address = new HashMap<>();
        address.put("city", "New York");
        address.put("zip", 10001);

        data.put("address", address);

        List<Map<String, Object>> projects = new ArrayList<>();
        Map<String, Object> project1 = new HashMap<>();
        project1.put("name", "Project A");
        project1.put("status", "completed");
        projects.add(project1);

        Map<String, Object> project2 = new HashMap<>();
        project2.put("name", "Project B");
        project2.put("status", "in progress");
        projects.add(project2);

        data.put("projects", projects);

        
        List<Map<String, Object>> results = findKeyValuePair(data, "status", "completed");
        System.out.println(results);
    }
}
