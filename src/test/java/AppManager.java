import io.qameta.allure.Attachment;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AppManager {
//    @BeforeEach
//    public void init(){
//        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
//        System.setProperty("selenide.browser", "Chrome");
//    }
//
    @AfterEach
    public void quit(){
        saveAllureScreenshot();
        getWebDriver().quit();
    }

    @Attachment(value = "screen", type = "image/png")
    protected byte[] saveAllureScreenshot() {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}