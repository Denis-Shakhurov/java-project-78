package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class NumberSchema extends BaseSchema<Integer> {
    private Map<String, Integer> methods = new LinkedHashMap<>();
    private Integer start;
    private Integer end;

    public NumberSchema required() {
        methods.remove("required");
        methods.put("required", null);
        return this;
    }

    public NumberSchema positive() {
        methods.remove("positive");
        methods.put("positive", 0);
        return this;
    }

    public NumberSchema range(int start, int end) {
        this.start = start;
        this.end = end;
        methods.remove("range");
        methods.put("range", 0);
        return this;
    }

    @Override
    public boolean isValid(Integer number) {
        int res = 0;
        var keys = methods.keySet();
        for (var key : keys) {
            String nameMethod = key;
            //Integer value = methods.get(key);
            if (nameMethod.equals("required")) {
                res += number == null ? 1 : 2;
            } else if (nameMethod.equals("positive")) {
                res += number > 0 ? 2 : 1;
            } else if (nameMethod.equals("range")) {
                res += number <= end && number >= start ? 2 : 1;
            }
        }
        return res % 2 == 0;
    }

//    Predicate<Integer> predicate = new Predicate<Integer>() {
//        @Override
//        public boolean test(Integer integer) {
//            return integer > 0;
//        }
//    };

//    private boolean resultRange(Integer number) {
//        return number <= end && number >= start;
//    }
}
