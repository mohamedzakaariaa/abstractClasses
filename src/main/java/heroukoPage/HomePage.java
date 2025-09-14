package heroukoPage;

import engine.WebDriverBot;

public class HomePage {

    String url = "http://the-internet.herokuapp.com/";
    WebDriverBot bot;

        public HomePage(WebDriverBot bot) {
        this.bot = bot;
    }
    public HomePage openHomePage() {
        bot.navigateTo(url);
        return this;
    }
    public CheckBoxPage  goTocheckBoxPage() {
        bot.navigateTo(url + "checkboxes");
        return new CheckBoxPage(bot);
    }

}
