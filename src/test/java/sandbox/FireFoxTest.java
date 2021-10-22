package sandbox;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FireFoxTest extends TestBase {


  private WebDriver driver;


  @BeforeTest
  public void setUp() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    DriverManagerType firefox = DriverManagerType.FIREFOX;
    WebDriverManager.getInstance(firefox).setup();
    Class<?> chromeClass = Class.forName(firefox.browserClass());
    driver = (WebDriver) chromeClass.newInstance();
  }


  @Test
  public void test() {
    driver.get("https://www.google.com");
  }


  @AfterTest
  public void teardown() {
    if (driver != null) {
      driver.quit();
    }
  }


}

