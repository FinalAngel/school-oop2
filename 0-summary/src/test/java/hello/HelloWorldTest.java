package hello;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HelloWorldTest {

    @Test
    public void testGreeting() {
        assertEquals("Hello, World!", HelloWorld.greeting("World"));
        assertEquals("Hello, Planet Earth!", HelloWorld.greeting("Planet Earth"));
    }

    @Test
    public void testSomething() {
      int i = 3 * 3;
      boolean b = true;
      int[] a1 = new int[9];
      int[] a2 = new int[2];
      Object o = new Object();

      // create test arguments
      // cal methods to test
      // test that return values meet expectation

      // assert methods
      assertEquals(i, 9);
      assertTrue(b);
      assertFalse(!b);
      assertNotEquals(i, 9.5);
      assertSame(a1, a1);
      assertNotSame(a1, a2);
      // assertNull(o);
      assertNotNull(a1);

      // init
      Object init = this.initializeFh();
    }
    
    private Object initializeFh() {
      // function to run initialisation code to be used:
      // JunitTest init = initializeFh();
      return this;
    }

    @BeforeEach // (@BeforeAll)
    public void initFile() {
      // do something before each test
      // for example: open file
    }

    @AfterEach // (@AfterAll)
    public void unloadFile() {
      // run after each test
      // for example: close file
    }

    // Additional from slides
    // DOCS: junit.org/junit5/docs/current/user-guide/
    /**
     * @ParameterizedTest (test multiple inputs)
     * assumeTrue/assumeFalse (deactivate tests if requirements not met)
     * @EnabledOnOs, @EnabledOnJre, @EnabledIfEnvironmentVariable (tests for ci or so)
     */
}
