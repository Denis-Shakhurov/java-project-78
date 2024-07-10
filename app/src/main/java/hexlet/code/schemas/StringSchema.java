package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        Predicate<String> requiredPredicate = s -> s != null && s != "";
        predicateList.add(requiredPredicate);
        return this;
    }

    public StringSchema minLength(int min) {
        Predicate<String> minLengthPredicate = s -> s.length() >= min;
        predicateList.remove(minLengthPredicate);
        predicateList.add(minLengthPredicate);
        return this;
    }

    public StringSchema contains(String str) {
        Predicate<String> containsPredicate = s -> s.contains(str);
        predicateList.remove(containsPredicate);
        predicateList.add(containsPredicate);
        return this;
    }
}
