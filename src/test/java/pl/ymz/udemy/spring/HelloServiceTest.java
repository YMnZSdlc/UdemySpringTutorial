package pl.ymz.udemy.spring;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {

    private HelloService SUT = new HelloService(); // System Under Test = SUT

    @Test
    public void test_prepareGreeting_null_returnsGreeteingWithFallback() {
        // given + then
        var result = SUT.prepareGreeting(null);

        // then
        assertEquals("Hello " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_name_returnsGreetingWitchName() {
        // given
        var name = "TestName";

        // then
        var result = SUT.prepareGreeting(name);

        // then
        assertEquals("Hello " + name + "!", result);
    }
}