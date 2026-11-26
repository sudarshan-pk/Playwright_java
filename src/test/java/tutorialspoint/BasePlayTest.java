package tutorialspoint;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.annotations.*;

import java.util.List;

public class BasePlayTest {

    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeClass(description = "Set up for playwright")
    public void setup() {
        System.out.println("Setting up the test environment...");

        playwright = Playwright.create();

        BrowserType.LaunchOptions browserOptions = new BrowserType.LaunchOptions();
        browserOptions.setHeadless(false);
        browserOptions.setArgs(List.of("--start-maximized"));
//        browserOptions.setChannel("chrome");

        browser = playwright.chromium().launch(browserOptions);
//        browser = playwright.chromium().launch();

        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions();
        contextOptions.setViewportSize(null);

        BrowserContext browserContext = browser.newContext(contextOptions);
        page = browserContext.newPage();

//        page = browser.newPage();
        page.setDefaultTimeout(5000);
        // Set default timeout for actions like click, fill, etc. to 5000 milliseconds (5 seconds). Note Playwright's default timeout is 30 seconds, so this will override that.

        PlaywrightAssertions.setDefaultAssertionTimeout(3000);
    }


    @AfterClass(description = "Tear down for playwright", alwaysRun = true)
    public void tearDown() {
        System.out.println("Tearing down the test environment...");

        if (page != null) {
            page.close();
            page = null;
        }
        if (browser != null) {
            browser.close();
            browser = null;
        }

        if (browser != null) {
            browser.close();
            browser = null;
        }

        if (playwright != null) {
            playwright.close();
            playwright = null;
        }


    }

}
