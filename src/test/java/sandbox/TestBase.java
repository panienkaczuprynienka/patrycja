package sandbox;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestBase {
  protected WebDriver driver;

  @BeforeEach
  public void setUp() throws ClassNotFoundException, IllegalAccessException, InstantiationException, MalformedURLException {
    //WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    //FirefoxOptions options = new FirefoxOptions();
    //options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
    //options.setCapability(CapabilityType.VERSION, "89");
    System.out.println(options.asMap());
    driver = new RemoteWebDriver(new URL("http://192.168.0.143:4444/wd/hub"), options);

    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

/*
    System.setProperty("webdriver.chrome.driver", "C:\\code\\patrycja\\src\\main\\resources\\chromedriver.exe");
     driver = new ChromeDriver();
  */
    //WebDriverManager.chromedriver().setup();
    //driver = new ChromeDriver();

  }


  @AfterEach
  public void teardown() throws IOException {
    if (driver != null) {
      driver.quit();
    }
  }
}
