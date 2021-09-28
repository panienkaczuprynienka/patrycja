package sandbox;


import TestHelpers.TestStatus;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

public class ChromeTest extends TestBase {

  @RegisterExtension
  TestStatus status = new TestStatus();

  @Test
  public void test() {
    //driver.get("https://gofile.io/uploadFiles");
    // Assertions.assertTrue(driver.getCurrentUrl().contains("google"));
    //Set<Cookie> cookies = driver.manage().getCookies();
   /* WebElement uploadBtn = driver.findElement(By.cssSelector("input[type='file']"));
    String path = "E:\\lustro_toaletka.png";
    uploadBtn.sendKeys(path);
    Assertions.assertEquals(driver.findElements(By.cssSelector("[role='row']")).size() == 2,
            true, MessageFormat.format("miało byc 2 a wyszlo {0}",
                    driver.findElements(By.cssSelector("[role='row']")).size()));

    */
    driver.get("https://www.google.com");
    WebElement input = driver.findElement(By.cssSelector("input[class='gLFyf gsfi']"));
    input.sendKeys("patrycja");
    input.submit();


    By by = By.xpath("//span[.='Patrycja – Wikipedia, wolna encyklopedia']");
    WebDriverWait wait = new WebDriverWait(driver, 1, 1000);
    wait.until(ExpectedConditions.presenceOfElementLocated(by));
    Assertions.assertTrue(driver.findElements(by).size() > 0, "Nie dziala!!");


  }

  @Test
  public void frametest() throws InterruptedException {
    driver.get("https://www.nasa.gov/topics/history/index.html");
    driver.manage().window().maximize();
    Thread.sleep(1000);
    //WebElement frameElement = driver.findElement(By.cssSelector(".timeline-Widget"));
    //driver.switchTo().frame(frameElement);
    driver.switchTo().frame("twitter-widget-0");
    driver.findElement(By.cssSelector("a[data-scribe*='twitter_url']")).click();


    driver.switchTo().defaultContent();
    driver.switchTo().parentFrame();
  }

  @Test
  public void jsTest() throws InterruptedException {
    driver.get("https://www.empik.com");
    driver.manage().window().maximize();
    Thread.sleep(1000);
    WebElement box = driver.findElement(By.cssSelector("body > main > div:nth-child(4) > div:nth-child(12) > div:nth-child(2) > section"));
    List<WebElement> pricesWebElements = box.findElements(By.cssSelector(".ta-productlist-price"));
    JavascriptExecutor js = (JavascriptExecutor) driver;
    for (int i = 0; i < pricesWebElements.size(); i++) {
      String text = (String) js.executeScript("return arguments[0].text", pricesWebElements.get(i));
      System.out.println(MessageFormat.format("cena elementu numer {0}  to {1}", i, text));
    }
  }


  @Test
  public void newTestWithAlert() {
    //driver.get("https://www.google.pl");
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String javacript = "prompt('Możesz tutaj wpisać text')";
    js.executeScript(javacript);
    driver.switchTo().alert().sendKeys("Nie chche widzeić tego okna!");
    driver.switchTo().alert().dismiss();
  }


  @Test
  public void storageTest() {
    ChromeDriver driver = new ChromeDriver();
    driver.navigate().to("https://airly.org/map/en/#52.0971398,21.0651967");
    LocalStorage local = driver.getLocalStorage();
    String mapstorage = local.getItem("persist:map");
    int size = local.size();
    System.out.println("size okal storydza to  " + size);
    System.out.println("Moj storydż to " + mapstorage);
    Set<String> secik = local.keySet();
    System.out.println(secik);
    local.setItem("spell", "Kalahomora!");
    System.out.println("xxx");
    Set<String> secikpo_dodaniu = local.keySet();
  }

  @Test
  public void sessionStorageTest() {
    ChromeDriver driver = new ChromeDriver();
    driver.navigate().to("https://www.youtube.com/watch?v=dJ9WFUFwuZM&ab_channel=GazetkiiPromocje");
    final SessionStorage session = driver.getSessionStorage();
    int size = session.size();
    System.out.println("size session storydza to  " + size);

    Set<String> secik = session.keySet();
    System.out.println(secik);
    session.setItem("spell", "Kalahomora!");

    Set<String> secikpo_dodaniu = session.keySet();
    System.out.println("moj session storage po dodoniu zaklecia " + secikpo_dodaniu);
    WebDriverWait wait = new WebDriverWait(driver, 5);
    wait.until(a -> session.size() > 4);
    String myaddicionalSessionCostam = session.getItem("spell");
    System.out.println(myaddicionalSessionCostam + "To moj dodoatkowy");
  }

  @Test
  public void storageJSTest() {
    String key = "persist:map";
    driver.navigate().to("https://airly.org/map/en/#52.0971398,21.0651967");
    String valueLocal = (String) ((JavascriptExecutor) driver).executeScript("return localStorage.getItem(arguments[0]);", key);
    ((JavascriptExecutor) driver).executeScript("localStorage.setItem(arguments[0], arguments[1]);", "spell", "Alohomora!");
    ((JavascriptExecutor) driver).executeScript("localStorage.removeItem(arguments[0]);", key);
    String indexValueLocal = (String) ((JavascriptExecutor) driver).executeScript("return localStorage.key(arguments[0]);", 2);
    long sizeLocal = (long) ((JavascriptExecutor) driver).executeScript("return localStorage.length;");
    ((JavascriptExecutor) driver).executeScript("localStorage.clear();");
  }


  public String takeScreenShot(TestInfo testInfo) throws IOException {
    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    LocalDateTime time = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
    String path = "C:\\code\\patrycja\\screenshots\\TEST_" + testInfo.getDisplayName() + "_" + formatter.format(time) + ".jpg";
    FileHandler.copy(screenshot, new File(path));
    return path;
  }

  //@Override
  @AfterEach
  public void teardown(TestInfo testInfo) throws IOException {
    if (driver != null) {
      System.out.println("Uzyto overrida");
      if (status.isFailed) {
        System.out.println("Screenshot is available at: " + takeScreenShot(testInfo));
      }
      driver.quit();
    }
  }

}

