import io.appium.java_client.AppiumDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class MainTest {
    private AppiumDriver<WebElement> driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "G:\\github\\MobileAppAutomator\\MobileAppAutomator-Ex07\\apks\\org.wikipedia.apk");

        driver = new AppiumDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        setPortraitScreenOrientation();
    }

    @After
    public void tearsDown() {
        if (driver != null) {
            setPortraitScreenOrientation();
            driver.quit();
        }
    }

    @Test
    public void testRotateScreen() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
        Assert.assertSame(driver.getOrientation(), ScreenOrientation.LANDSCAPE);
    }

    @Test
    public void testAfterScreenRotation() {
        Assert.assertSame(driver.getOrientation(), ScreenOrientation.PORTRAIT);
    }

    private void setPortraitScreenOrientation() {
        if (driver.getOrientation() != ScreenOrientation.PORTRAIT) {
            driver.rotate(ScreenOrientation.PORTRAIT);
        }
    }
}
