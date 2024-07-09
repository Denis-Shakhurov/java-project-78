package hexlet.code.schemas;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MapSchema extends BaseSchema<Map> {
    private List<String> listMethods = new LinkedList<>();
    private int sizeMap;

    public MapSchema required() {
        listMethods.remove("required");
        listMethods.add("required");
        return this;
    }

    public MapSchema sizeof(int size) {
        this.sizeMap = size;
        listMethods.remove("sizeof");
        listMethods.add("sizeof");
        return this;
    }

    @Override
    public boolean isValid(Map value) {
        int res = 0;
        for (String nameMethod : listMethods) {
            if (nameMethod.equals("required")) {
                res += value == null ? 1 : 2;
            } else if (nameMethod.equals("sizeof")) {
                res += value.size() == sizeMap ? 2 : 1;
            }
        }
        return res % 2 == 0;
    }
}
