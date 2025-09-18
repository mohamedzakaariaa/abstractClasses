package engine;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class WebDriverBot {
    WebDriver driver;
    Wait<WebDriver> wait ;
    Actions actions;
    private String loadBrowserType(){
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("C://Users//mzzzz//IdeaProjects//abstractClasses//src//main//resources//config.properties")) {
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(" Could not read config.properties file", e);
        }
        String browser = props.getProperty("TargetBrowser");
        if (browser == null){
            throw new RuntimeException("targetBrowser property not found in config.properties file");
        }
        return browser;

    }
    public WebDriverBot (){

        String browserType = loadBrowserType();
        if (browserType.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        } else {
            throw new IllegalStateException("Unsupported browser type: " + browserType);
        }

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

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean isDisplayed(By logo) {
        return wait.until(d->driver.findElement(logo).isDisplayed());
    }

    public void search(By searchInput, String searchItem) {
        wait.until(d->{
            driver.findElement(searchInput).sendKeys(searchItem, Keys.ENTER);
            return true;
        });

    }

    public String getLink(By result) {
        return wait.until(d -> {
            String href = driver.findElement(result).getAttribute("href");
            if (href == null) {
                throw new IllegalStateException("Element found but has no href attribute!");
            }
            return href;
        });
}

    public String getText(By result) {
        return wait.until(d->driver.findElement(result).getText());
    }
}
