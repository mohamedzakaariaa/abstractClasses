package engine;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
    Logger logger = LogManager.getLogger(getClass());
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
    @Step("Initialize the WebDriver")
    private void initializeDriver(){
        String browserType = loadBrowserType();
        if (browserType.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
            logger.info("Chrome browser started");
        } else if (browserType.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
            logger.info(" Firefox browser started");
        } else {
            logger.error(" Unsupported browser type: {}", browserType);
            throw new IllegalStateException("Unsupported browser type: " + browserType);
        }
        this.actions = new Actions(driver);
        this.wait =new FluentWait<>(driver)
                .withTimeout(java.time.Duration.ofSeconds(10))
                .pollingEvery(java.time.Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }


    public WebDriverBot (){

    this.initializeDriver();

    }

    public WebDriverBot(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait=wait;
    }
@Step("Quit the browser")
    public void quit(){
        if (driver != null){
            driver.quit();
            logger.info(" Browser closed");
        }
    }
    @Step("Navigate to {url}")

    public WebDriverBot navigateTo(String url) {
        driver.get(url);
        logger.info(" Navigated to {}", url);
        return this;
    }
@Step("Drag element {dragMe} and drop it on {dropHere}")
    public void dragAndDrop(By dragMe, By dropHere) {
       wait.until(d-> {
              actions.dragAndDrop(driver.findElement(dragMe), driver.findElement(dropHere)).perform();

              return true;
       });
    logger.info(" Dragged element {} and dropped it on {}", dragMe, dropHere);
    }
@Step("Check if element {droppedText} is dropped")
    public Boolean isDropped(By droppedText) {
       Boolean actualValue= wait.until(d->driver.findElement(droppedText).getText().equals("Dropped!"));
    logger.info("  Is element {} dropped? {}", droppedText, actualValue);
   return actualValue;
    }
@Step("Check the checkbox located by {checkboxLocator}")
    public void check(By checkboxLocator) {
        wait.until(d->{
            if (!driver.findElement(checkboxLocator).isSelected()){
                driver.findElement(checkboxLocator).click();

            }
            return true;
        });


    }
@Step("Check if the checkbox located by {checkLocator} is checked")
    public Boolean isChecked(By checkLocator) {
        return wait.until(d->driver.findElement(checkLocator).isSelected());
    }
    @Step("Check if the checkboxes located by {checkLocator} and {checkLocator2} are both checked")
    public Boolean isTwoChecked(By checkLocator, By checkLocator2) {
        return wait.until(d->driver.findElement(checkLocator).isSelected()&&driver.findElement(checkLocator2).isSelected());
    }
@Step("Get the page title")
    public String getTitle() {
        return driver.getTitle();
    }
@Step("Check if element {logo} is displayed")
    public boolean isDisplayed(By logo) {
        return wait.until(d->driver.findElement(logo).isDisplayed());
    }
@Step("Search for {searchItem} using the search input located by {searchInput}")
    public void search(By searchInput, String searchItem) {
        wait.until(d->{
            driver.findElement(searchInput).sendKeys(searchItem, Keys.ENTER);
            return true;
        });
    }
@Step("Get the href attribute of the element located by {result}")
    public String getLink(By result) {
        return wait.until(d -> {
            String href = driver.findElement(result).getAttribute("href");
            if (href == null) {
                throw new IllegalStateException("Element found but has no href attribute!");
            }
            return href;
        });
}
@Step("Get the text of the element located by {result}")
    public String getText(By result) {
        return wait.until(d->driver.findElement(result).getText());
    }
}
