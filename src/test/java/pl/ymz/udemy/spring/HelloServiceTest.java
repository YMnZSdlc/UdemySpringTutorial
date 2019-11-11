package pl.ymz.udemy.spring;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {
    private final static String HELLO = "Hello";

    @Test
    public void test_prepareGreeting_nullName_returnsGreetingWithFallbackName() {
        // given
        var mockRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mockRepository); // System Under Test = SUT

        // then
        var result = SUT.prepareGreeting(null, "-1");

        // then
        assertEquals(HELLO + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_name_returnsGreetingWitchName() {
        // given
        var mockRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mockRepository); // System Under Test = SUT
        var name = "TestName";

        // then
        var result = SUT.prepareGreeting(name, "-1");

        // then
        assertEquals(HELLO + " " + name + "!", result);
    }

    private LangRepository alwaysReturningHelloRepository() {
        return new LangRepository() {
            @Override
            Optional<Lang> findById(Long id) {
                return Optional.of(new Lang(null, HELLO, null));
            }
        };
    }
}
