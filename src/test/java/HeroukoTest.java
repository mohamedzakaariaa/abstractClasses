import heroukoPage.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HeroukoTest extends TestBase {

/**
 * Open Google Chrome
 * Navigate to [https://jqueryui.com/resources/demos/droppable/default.html]
 * Drag [Drag me to my target] and drop it on [Drop here]
 * Assert that the text has been changed to [Dropped!]
 * Close Google Chrome
 */


@Test
    public void dragAndDropTest() {
Boolean isTwiceChecked =new HomePage(bot)
        .goTocheckBoxPage()
        .check(1).isTwoChecked(1,2);
Assert.assertTrue(isTwiceChecked,"one of them is not checked");


}
}
