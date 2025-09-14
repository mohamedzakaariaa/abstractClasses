package engine;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;



public class WebDriverBot {
    WebDriver driver;
    Wait<WebDriver> wait ;
    Actions actions;

    public WebDriverBot (){

        this.driver = new ChromeDriver();
       this.actions = new Actions(driver);
        this.wait =new FluentWait<>(driver)
                .withTimeout(java.time.Duration.ofSeconds(10))
                .pollingEvery(java.time.Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);




    }

    public WebDriverBot(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait=wait;
    }

    public void quit(){
        if (driver != null){
            driver.quit();
        }
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void dragAndDrop(By dragMe, By dropHere) {
       wait.until(d-> {
              actions.dragAndDrop(driver.findElement(dragMe), driver.findElement(dropHere)).perform();
              return true;
       });

    }

    public Boolean isdropped(By droppedText) {
        return wait.until(d->driver.findElement(droppedText).getText().equals("Dropped!"));

    }

    public void check(By checkboxLocator) {
        wait.until(d->{
            if (!driver.findElement(checkboxLocator).isSelected()){
                driver.findElement(checkboxLocator).click();
            }
            return true;
        });


    }

    public Boolean isChecked(By checkLocator) {
        return wait.until(d->driver.findElement(checkLocator).isSelected());
    }
    public Boolean isTwoChecked(By checkLocator, By checkLocator2) {
        return wait.until(d->driver.findElement(checkLocator).isSelected()&&driver.findElement(checkLocator2).isSelected());
    }
}
