package hexlet.code.schemas;

import java.util.Map;

public class BaseSchema<T> {

    public boolean isValid(T value) {
        return true;
    }

    protected void reset(Map<String, T> map) {
        map.clear();
    }
}
