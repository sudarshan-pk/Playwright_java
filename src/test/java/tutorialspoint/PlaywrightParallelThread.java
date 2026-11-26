package tutorialspoint;

import com.microsoft.playwright.*;
import org.testng.annotations.*;

public class PlaywrightParallelThread {

    private static final ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static final ThreadLocal<Page> page = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // Launch browser only ONCE per thread
        if (browser.get() == null) {
            playwright.set(Playwright.create());
            browser.set(playwright.get().chromium()
                    .launch(new BrowserType.LaunchOptions().setHeadless(false)));
        }
        // Always create a fresh context + page per test (isolation)
        context.set(browser.get().newContext());
        page.set(context.get().newPage());
    }

    public Page getPage() {
        return page.get();
    }

    public BrowserContext getBrowserContext() {
        return context.get();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Close per-test resources only
        context.get().close();
        page.remove();
        context.remove();
        // Do NOT close browser/playwright — they're reused by the next method on this thread
    }

    @AfterSuite(alwaysRun = true)
    public void cleanup() {
        // Close shared thread resources at the very end
        if (browser.get() != null) {
            browser.get().close();
            playwright.get().close();
        }
        browser.remove();
        playwright.remove();
    }
}