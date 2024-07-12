package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    protected final Map<String, Predicate<T>> predicates = new LinkedHashMap<>();
    protected boolean requiredStatus = false;


    public final boolean isValid(T value) {
        if (value == null) {
            return !requiredStatus;
        } else {
            return predicates.values()
                    .stream()
                    .allMatch(predicate -> predicate.test(value));
        }
    }
}
