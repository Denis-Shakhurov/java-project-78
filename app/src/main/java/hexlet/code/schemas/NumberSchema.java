package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema() {
        predicates.clear();
    }

    public NumberSchema positive() {
        Predicate<Integer> positivePredicate = number -> number > 0;
        predicates.put("positive", positivePredicate);
        return this;
    }

    public NumberSchema range(int from, int before) {
        Predicate<Integer> rangePredicate = number -> number >= from || number <= before;
        predicates.put("range", rangePredicate);
        return this;
    }
}
