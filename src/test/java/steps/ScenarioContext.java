package steps;

import com.microsoft.playwright.*;
import enums.SharedInfoTag;

import java.nio.file.Paths;
import java.util.concurrent.ConcurrentHashMap;

import static utilities.WebActions.getProperty;

public class ScenarioContext {
    public Browser browser;
    BrowserType browserType = null;
    public Page page;
    boolean headless = Boolean.parseBoolean(getProperty("headless"));
    public static Playwright playwright = Playwright.create();
    public BrowserContext context;
    private final ConcurrentHashMap<String, Object> sharedInfo = new ConcurrentHashMap<>();

    public ScenarioContext() {
        switch (getProperty("browser")) {
            case "firefox":
                browserType = playwright.firefox();
                browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
            case "chrome":
                browserType = playwright.chromium();
                browser = browserType.launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(headless));
                break;
            case "webkit":
                browserType = playwright.webkit();
                browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
        }
        context = browser.newContext(
                new Browser.NewContextOptions().setBaseURL(getProperty("baseurl"))
                        .setRecordVideoDir(Paths.get("target", "results","videos/"))
                        .setRecordVideoSize(640, 480)
        );

        context.tracing().start(
                new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(true)
        );

        page = context.newPage();
    }

    public <T> T getSharedInfo(SharedInfoTag key, Class<T> valueType) {
        return getSharedInfo(key.name(), valueType);
    }

    public <T> T getSharedInfo(String key, Class<T> valueType) {
        try {
            return valueType.cast(sharedInfo.get(key));
        } catch (ClassCastException e) {
            return null;
        }
    }

    public void setSharedInfo(SharedInfoTag key, Object obj) {
        setSharedInfo(key.name(), obj);
    }

    public void setSharedInfo(String key, Object obj) {
        sharedInfo.put(key, obj);
    }

    public void cleanUp() {
        sharedInfo.clear();
    }
}
