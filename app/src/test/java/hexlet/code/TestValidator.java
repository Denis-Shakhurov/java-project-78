package hexlet.code;
import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestValidator {
    private Validator v = new Validator();

    @Test
    public void testStringSchema() {
        StringSchema schema = v.string();

        assertFalse(schema.required().isValid(""));
        assertTrue(schema.minLength(3).contains("ol").isValid("cool"));
        assertFalse(schema.minLength(3).minLength(10).minLength(12).isValid("Hexlet"));
        assertFalse(schema.minLength(2).isValid(null));
    }

    @Test
    public void testNumberSchema() {
        NumberSchema schema = v.number();

        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(null));
        assertFalse(schema.required().isValid(null));
        assertFalse(schema.positive().isValid(-5));
        assertFalse(schema.isValid(0));
        assertFalse(schema.range(2, 9).isValid(-4));
        assertTrue(schema.isValid(7));
    }

    @Test
    public void testMapSchema() {
        MapSchema schema = v.map();

        assertTrue(schema.isValid(null));
        assertFalse(schema.required().isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));

        Map<String, String > data = new HashMap<>();
        data.put("key1", "value1");

        assertTrue(schema.isValid(data));
        assertFalse(schema.sizeof(2).isValid(data));

        data.put("key2", "value2");

        assertTrue(schema.isValid(data));
    }

    @Test
    public void testMapSchemaShape() {
        MapSchema schema = v.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(schema.isValid(human2));

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(schema.isValid(human3));
    }
}