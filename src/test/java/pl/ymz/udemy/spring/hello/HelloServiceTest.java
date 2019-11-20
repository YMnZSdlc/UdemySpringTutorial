package pl.ymz.udemy.spring.hello;

import org.junit.Test;
import pl.ymz.udemy.spring.hello.HelloService;
import pl.ymz.udemy.spring.lang.Lang;
import pl.ymz.udemy.spring.lang.LangRepository;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {
    private final static String HELLO = "Hello";
    private final static String FALLBACK_ID_WELCOME = "Greeting";



    @Test
    public void test_prepareGreeting_nullName_returnsGreetingWithFallbackName() {
        // given
        var mockRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mockRepository); // System Under Test = SUT

        // when
        var result = SUT.prepareGreeting(null, "-1");

        // then
        assertEquals(HELLO + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_name_returnsGreetingWithName() {
        // given
        var mockRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mockRepository); // System Under Test = SUT
        var name = "TestName";

        // when
        var result = SUT.prepareGreeting(name, "-1");

        // then
        assertEquals(HELLO + " " + name + "!", result);
    }

    @Test
    public void test_prepareGreeting_nonExistingLang_returnsGreetingWithFallbackLang() {
        // given
        var mockRepository = new LangRepository(){
            @Override
            public Optional<Lang> findById(Integer id) {
                return Optional.empty();
            }
        };
        var SUT = new HelloService(mockRepository); // System Under Test = SUT

        // when
        var result = SUT.prepareGreeting(null, "-1");

        // then
        assertEquals(HelloService.FALLBACK_LANG.getWelcomeMsg() + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_nullLang_returnsGreetingWithFallbackIdLang() {
        // given
        var mockRepository = fallbackIdRepository();
        var SUT = new HelloService(mockRepository); // System Under Test = SUT

        // when
        var result = SUT.prepareGreeting(null, null);

        // then
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_textLang_returnsGreetingWithFallbackIdLang() {
        // given
        var mockRepository = fallbackIdRepository();
        var SUT = new HelloService(mockRepository); // System Under Test = SUT

        // when
        var result = SUT.prepareGreeting(null, "abc");

        // then
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    private LangRepository fallbackIdRepository() {
        return new LangRepository(){
            @Override
            public Optional<Lang> findById(Integer id) {
                if(id.equals(HelloService.FALLBACK_LANG.getId())){
                    return Optional.of(new Lang(null, FALLBACK_ID_WELCOME,null));
                }
                return Optional.empty();
            }
        };
    }


    private LangRepository alwaysReturningHelloRepository() {
        return new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                return Optional.of(new Lang(null, HELLO, null));
            }
        };
    }
}
