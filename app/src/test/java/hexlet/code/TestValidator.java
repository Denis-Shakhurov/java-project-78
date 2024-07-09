package hexlet.code;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

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
    }

    @Test
    public void testNumberSchema() {
        NumberSchema schema = v.number();
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(null));
        assertFalse(schema.required().isValid(null));
        assertFalse(schema.positive().isValid(-5));
        assertFalse(schema.isValid(0));
        assertTrue(schema.range(2, 9).isValid(4));
        assertTrue(schema.isValid(7));
    }
}