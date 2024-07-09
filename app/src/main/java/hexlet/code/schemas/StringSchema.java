package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;

public class StringSchema extends BaseSchema<String> {
    private Map<String, String> methods = new LinkedHashMap<>();

    public StringSchema required() {
        methods.remove("required");
        methods.put("required", null);
        return this;
    }

    public StringSchema minLength(int min) {
        methods.remove("minLength");
        methods.put("minLength", String.valueOf(min));
        return this;
    }

    public StringSchema contains(String str) {
        methods.remove("contains");
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
        reset(methods);
        return res % 2 == 0;
    }
}
