package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema<String> {

    public StringSchema() {
        predicates.clear();
    }

    public StringSchema required() {
        requiredStatus = true;
        Predicate<String> requiredPredicate = s -> s != null && !s.equals("");
        predicates.put("required", requiredPredicate);
        return this;
    }

    public StringSchema minLength(int min) {
        Predicate<String> minLengthPredicate = s -> s.length() >= min;
        predicates.put("minLength", minLengthPredicate);
        return this;
    }

    public StringSchema contains(String str) {
        Predicate<String> containsPredicate = s -> s.contains(str);
        predicates.put("contains", containsPredicate);
        return this;
    }
}
