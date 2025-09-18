package duckDuckGo;

import engine.WebDriverBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DuckDuckResults {
    //    variables
    private final WebDriverBot bot;
    //    locators
    private By Result = null ;
    //    constructors
    public DuckDuckResults(WebDriverBot bot) {
        this.bot=bot;}


    //    Actions
    public String getResultsLink(int i) {
        Result = By.xpath("(//a[@data-testid='result-title-a'])["+i+"]");
        return bot.getLink(Result);
    }

    public String getResultsText(int i) {
        Result = By.xpath("(//a[@data-testid='result-title-a'])["+i+"]");
        return bot.getText(Result);
    }
}
