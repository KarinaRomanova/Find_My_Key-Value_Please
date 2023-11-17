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
        // Пример использования:
        Map<String, Object> nestedData = new HashMap<>();
        nestedData.put("a", 1);

        Map<String, Object> b = new HashMap<>();
        b.put("c", 2);

        Map<String, Object> d = new HashMap<>();
        d.put("e", 3);

        b.put("d", d);
        nestedData.put("b", b);

        List<Map<String, Object>> f = new ArrayList<>();
        f.add(Collections.singletonMap("g", 4));
        Map<String, Object> h = new HashMap<>();
        h.put("h", 5);
        h.put("i", 6);
        f.add(h);
        f.add(Collections.singletonMap("j", 7));
        nestedData.put("f", f);

        // Ищем все вхождения пары ключ-значение 'j': 7
        List<Map<String, Object>> results = findKeyValuePair(nestedData, "j", 7);
        System.out.println(results);
    }
}
