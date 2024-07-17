package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema<Map> {

    public MapSchema required() {
        requiredStatus = true;
        Predicate<Map> requiredPredicate = map -> map != null;
        predicates.put("required", requiredPredicate);
        return this;
    }

    public MapSchema sizeof(int size) {
        Predicate<Map> sizeofPredicate = map -> map.size() == size;
        predicates.put("sizeof", sizeofPredicate);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        predicates.put("shape", value -> ((Map) value).keySet().stream()
                        .allMatch(key -> schemas.get(key).isValid((String) value.get(key))));
        return this;
    }
}
