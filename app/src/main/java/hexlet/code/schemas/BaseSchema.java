package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema<T> {
    protected Map<String, Predicate<T>> predicates = new LinkedHashMap<>();
    protected boolean requiredStatus = false;

    public boolean isValid(T value) {
        int res = 0;
        if (value == null) {
            res += requiredStatus ? 1 : 2;
        } else {
            var keys = predicates.keySet();
            for (var key : keys) {
                if (!predicates.get(key).test(value)) {
                    res += 1;
                    break;
                } else {
                    res += 2;
                }
            }
        }
        return res % 2 == 0;
    }
}
