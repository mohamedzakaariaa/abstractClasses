import engine.WebDriverBot;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestBase {
WebDriverBot bot;
    @BeforeTest
    public void setUp(){
        bot =new WebDriverBot();
    }
    @AfterTest
    public void tearDown(){
     bot.quit();
    }

}
