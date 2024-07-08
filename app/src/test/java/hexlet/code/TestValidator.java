package hexlet.code;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestValidator {
    private Validator v = new Validator();
    private StringSchema schema = v.string();

    @Test
    public void testStringSchema() {
        assertTrue(schema.required().isValid(""));
        assertTrue(schema.minLength(3).contains("ol").isValid("cool"));
        assertFalse(schema.minLength(3).minLength(10).minLength(2).isValid("Hexlet"));
    }
}
