package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema<T> {
    protected Map<String, Predicate<T>> predicates = new LinkedHashMap<>();

    public BaseSchema<T> required() {
        Predicate<T> predicateRequired = val -> val != null;
        predicates.put("required", predicateRequired);
        return this;
    }

    public boolean isValid(T value) {
        int res = 0;
        var keys = predicates.keySet();
        for (var key : keys) {
            if (!predicates.get(key).test(value)) {
                res += 1;
                break;
            } else {
                res += 2;
            }
        }
        return res % 2 == 0;
    }
}
