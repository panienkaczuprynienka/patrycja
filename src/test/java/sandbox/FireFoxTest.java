package sandbox;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class FireFoxTest extends TestBase {


  private WebDriver driver;


  @BeforeEach
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


  @AfterEach
  public void teardown() {
    if (driver != null) {
      driver.quit();
    }
  }


}

