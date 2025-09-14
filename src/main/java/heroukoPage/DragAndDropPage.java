package heroukoPage;

import engine.WebDriverBot;
import org.openqa.selenium.By;

public class DragAndDropPage {
    WebDriverBot bot;

    private static final By  dragMe = By.id("draggable");
   private static final  By dropHere = By.id("droppable");
   private static final By droppedText = By.xpath("//p");
    public DragAndDropPage(WebDriverBot bot) {
        this.bot = bot;
    }
public DragAndDropPage dragAndDrop () {
bot.dragAndDrop(dragMe,dropHere);
    return this;
}
public Boolean isDropped(){
      return bot.isdropped(droppedText);

}


}
