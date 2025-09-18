package duckDuckGo;

import engine.WebDriverBot;
import org.openqa.selenium.By;


public class DuckHome {
    //variables
    private final  WebDriverBot bot ;
    private   final String url="https://duckduckgo.com/";
    //    locators
    private final By logo = By.xpath("//div[contains(@class,'Desktop')]//img");
    private  final  By searchInput= By.id("searchbox_input");
    //    constructors
    public DuckHome(WebDriverBot bot) {

        this.bot=bot;
    }


    //    Actions
    public DuckHome navigateHome() {

       bot.navigateTo(url);
        return this;
    }
    public String getTitle() {
        return bot.getTitle();}


    public boolean LogoIsDisplayed() {
        return bot.isDisplayed(logo);
    }

    public DuckDuckResults searchFor(String searchItem) {
    bot.search(searchInput,searchItem);
        return new DuckDuckResults(bot);

    }


}
