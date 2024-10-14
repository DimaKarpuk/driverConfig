
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.LocalConfig;
import config.RemoteConfig;
import drivers.LocalDriver;
import drivers.RemoteDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class TestBase {
    private static final LocalConfig localConfig = ConfigFactory.create(LocalConfig.class, System.getProperties());
    private static final RemoteConfig remoteConfig = ConfigFactory.create(RemoteConfig.class, System.getProperties());

    private static String defHost = "local";

    @BeforeAll
    public static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        SelenideLogger.addListener("allure", new AllureSelenide());
        if (System.getProperty("browserHost", defHost).equals("local")) {
            LocalDriver localDriver = new LocalDriver(localConfig);
            localDriver.localConfig();
        } else if (System.getProperty("browserHost", defHost).equals("remote")) {
            RemoteDriver remoteDriver = new RemoteDriver(remoteConfig);
            remoteDriver.remoteConfig();

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }
    }

    @BeforeEach
    public void beforeEach() {
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        if (System.getProperty("browserHost", defHost).equals("remote")) {
            Attach.addVideo();
        }
        Selenide.closeWebDriver();
    }
}
