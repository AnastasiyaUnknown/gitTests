import org.junit.jupiter.api.AfterEach;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AppManager {

    //    @Before
//    public void init(){
//        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
//        System.setProperty("selenide.browser", "Chrome");
//    }
//
    @AfterEach
    public void quit(){
        getWebDriver().quit();
    }
}