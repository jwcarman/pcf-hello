package hello;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HelloControllerTest {

    @Test
    public void testSomething() {
        final HelloController helloController = new HelloController();
        assertEquals("Hello, PCF!", helloController.index());
    }

}