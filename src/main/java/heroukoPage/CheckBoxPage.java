package heroukoPage;

import engine.WebDriverBot;
import org.openqa.selenium.By;

public class CheckBoxPage {
    WebDriverBot bot;
    private By checkLocator =null;
    private By checkSecondLocator =null;
    public CheckBoxPage(WebDriverBot bot) {
        this.bot = bot;
    }
        public CheckBoxPage check(int i) {
        checkLocator = By.xpath("(//input[@type='checkbox'])[" + i + "]");
            bot.check(checkLocator);
            return this;
        }
    public Boolean isChecked(int i) {
        checkLocator = By.xpath("(//input[@type='checkbox'])[" + i + "]");
        return bot.isChecked(checkLocator);
    }
    public Boolean isTwoChecked(int i,int j) {
        checkLocator = By.xpath("(//input[@type='checkbox'])[" + i + "]");
        checkSecondLocator = By.xpath("(//input[@type='checkbox'])[" + j + "]");
        return bot.isTwoChecked(checkLocator,checkSecondLocator);
    }
}
