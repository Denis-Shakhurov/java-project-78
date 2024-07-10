package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map> {
    protected Map<?, ?> shapeSchema = new LinkedHashMap<>();

    public MapSchema sizeof(int size) {
        Predicate<Map> sizeofPredicate = map -> map.size() == size;
        predicateList.remove(sizeofPredicate);
        predicateList.add(sizeofPredicate);
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
                res += ((BaseSchema) shapeSchema.get(key)).isValid(value.get(key)) ? 2 : 1;
            }
            return res % 2 == 0;
        } else {
            return super.isValid(value);
        }
    }
}
