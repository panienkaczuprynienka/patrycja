package sandbox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestBase {
  protected WebDriver driver;

  @BeforeTest
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


  @AfterTest
  public void teardown() throws IOException {
    if (driver != null) {
      driver.quit();
    }
  }
}
