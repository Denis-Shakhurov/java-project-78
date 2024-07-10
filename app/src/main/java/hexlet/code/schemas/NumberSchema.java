package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema positive() {
        Predicate<Integer> positivePredicate = number -> number > 0;
        predicateList.add(positivePredicate);
        return this;
    }

    public NumberSchema range(int from, int before) {
        Predicate<Integer> rangePredicate = number -> number >= from || number <= before;
        predicateList.remove(rangePredicate);
        predicateList.add(rangePredicate);
        return this;
    }
}
