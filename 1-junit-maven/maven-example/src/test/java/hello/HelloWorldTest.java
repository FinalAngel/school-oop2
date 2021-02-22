package hello;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloWorldTest {

    @Test
    public void testGreeting() {
        assertEquals("Hello, World!", HelloWorld.greeting("World"));
        assertEquals("Hello, Planet Earth!", HelloWorld.greeting("Planet Earth"));
    }
}
