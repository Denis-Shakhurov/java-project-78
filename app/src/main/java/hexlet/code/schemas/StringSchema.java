package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;

public class StringSchema {
    private Map<String, String> methods = new LinkedHashMap<>();

    public Map<String, String> getMethods() {
        return methods;
    }

    public StringSchema required() {
        methods.remove("required");
        methods.put("required", null);
        return this;
    }

    public StringSchema minLength(int min) {
        methods.put("minLength", "0");
        methods.put("minLength", String.valueOf(min));
        return this;
    }

    public StringSchema contains(String str) {
        methods.put("contains", "0");
        methods.put("contains", str);
        return this;
    }
    public boolean isValid(String text) {
        int res = 0;
        var keys = methods.keySet();
        for (var key : keys) {
            String nameMethod = key;
            String value = methods.get(key);
            if (nameMethod.equals("required")) {
                res += text == null || text == "" ? 1 : 2;
            } else if (nameMethod.equals("minLength")) {
                res += text.length() >= Integer.valueOf(value) ? 2 : 1;
            } else if (nameMethod.equals("contains")) {
                res += text.contains(value) ? 2 : 1;
            }
        }
        reset();
        return res % 2 == 0;
    }
    private void reset() {
        methods.remove("required");
        methods.remove("minLength");
        methods.remove("contains");
    }
}
