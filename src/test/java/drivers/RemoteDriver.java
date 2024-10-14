package drivers;

import config.RemoteConfig;
import com.codeborne.selenide.Configuration;

public class RemoteDriver {
    private final RemoteConfig remoteConfig;

    public RemoteDriver(RemoteConfig remoteConfig) {
        this.remoteConfig = remoteConfig;
    }

    public void remoteConfig() {
        Configuration.baseUrl = remoteConfig.getBaseUrl();
        Configuration.browser = remoteConfig.getBrowser();
        Configuration.browserSize = remoteConfig.getBrowserSize();
        Configuration.browserVersion = remoteConfig.getBrowserVersion();
        Configuration.remote =remoteConfig.remoteUrl();
    }
}
