package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema<Map> {
    private Map<?, ?> shapeSchema = new LinkedHashMap<>();

    public MapSchema() {
        predicates.clear();
    }

    public MapSchema required() {
        requiredStatus = true;
        Predicate<Map> requiredPredicate = map -> map != null;
        return this;
    }

    public MapSchema sizeof(int size) {
        Predicate<Map> sizeofPredicate = map -> map.size() == size;
        predicates.put("sizeof", sizeofPredicate);
        return this;
    }

    public MapSchema shape(Map<?, ?> schemas) {
        this.shapeSchema = schemas;
        return this;
    }

    @Override
    public boolean isValid(Map value) {
        int res = 0;
        if (shapeSchema.size() > 0) {
            var keys = value.keySet();
            for (var key : keys) {
                if (!((BaseSchema) shapeSchema.get(key)).isValid(value.get(key))) {
                    res += 1;
                    break;
                } else {
                    res += 2;
                }
            }
            return res % 2 == 0;
        } else {
            return super.isValid(value);
        }
    }
}
