package hello;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HelloControllerTest {

    @Test
    public void testSomething() {
        final HelloController controller = new HelloController();
        assertEquals("Hello, PCF!", controller.index());
    }
}