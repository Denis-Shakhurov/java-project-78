package hexlet.code.schemas;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema<T> {
    protected List<Predicate<T>> predicateList = new LinkedList<>();

    public BaseSchema<T> required() {
        Predicate<T> predicateRequired = val -> val != null;
        predicateList.remove(predicateRequired);
        predicateList.add(predicateRequired);
        return this;
    }

    public boolean isValid(T value) {
        int res = 0;
        for (Predicate<T> predicate : predicateList) {
            if (!predicate.test(value)) {
                res += 1;
                break;
            } else {
                res += 2;
            }
        }
        return res % 2 == 0;
    }
}
