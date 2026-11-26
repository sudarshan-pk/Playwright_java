package tutorialspoint;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BrowserWindowsPage;
import pages.RegistrationPage;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MultipleBrowserTest {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;

    RegistrationPage registrationPage;

    @BeforeMethod
    public void setUp() {
        System.out.println("Setting up the test environment...");
        playwright = Playwright.create();

        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.setHeadless(false); // Set to true if you want to run in head
        launchOptions.setArgs(List.of("--start-maximized")); // Start the browser maximized
        browser = playwright.chromium().launch(launchOptions);

        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions();
        contextOptions.setViewportSize(null);
        browserContext = browser.newContext(contextOptions);
        page = browserContext.newPage();

        PlaywrightAssertions.setDefaultAssertionTimeout(3000);
        page.setDefaultTimeout(5000);
    }

    @Test(description = "Test opening a new browser tab")
    public void testNewBrowserTab() {
        System.out.println("Starting the new browser tab test...");
        registrationPage = new RegistrationPage(page);
        BrowserWindowsPage browserWindowsPage = new BrowserWindowsPage(page);
        page.navigate("https://www.tutorialspoint.com/selenium/practice/register.php");
        registrationPage.clickAlertFrameWindow();
        registrationPage.clickBrowserWindow();
        Page newpage = browserContext.waitForPage(() -> {
            browserWindowsPage.clickNewTabButton();
        });
        assertThat(newpage.locator("h1").nth(1)).hasText("New Tab", new LocatorAssertions.HasTextOptions().setTimeout(5000));
        newpage.locator(".container").nth(1).waitFor(new Locator.WaitForOptions().setTimeout(5000));
        Assert.assertTrue(newpage.locator(".container").nth(1).textContent().contains("Sample New Tab"), "New tab content does not match expected value.");
    }

    @Test(description = "Test opening a new browser window")
    public void testNewBrowserWindow() {
        System.out.println("Starting the new browser window test...");
        registrationPage = new RegistrationPage(page);
        BrowserWindowsPage browserWindowsPage = new BrowserWindowsPage(page);
        page.navigate("https://www.tutorialspoint.com/selenium/practice/register.php");
        registrationPage.clickAlertFrameWindow();
        registrationPage.clickBrowserWindow();
        Page newWindow = browserContext.waitForPage(() -> {
            browserWindowsPage.clickNewWindowButton();
        });

        Locator newWindowHeader = newWindow.locator("h1").nth(1);
        newWindowHeader.waitFor(new Locator.WaitForOptions().setTimeout(5000));
        assertThat(newWindowHeader).hasText("New Window", new LocatorAssertions.HasTextOptions().setTimeout(5000));
        newWindow.locator(".container").nth(1).waitFor(new Locator.WaitForOptions().setTimeout(5000));
        Assert.assertTrue(newWindow.locator(".container").nth(1).textContent().contains("Sample Text"), "New window content does not match expected value.");
    }

    @Test(description = "Test opening a new browser window message")
    public void testNewBrowserWindowMessage() {
        System.out.println("Starting the new browser window message test...");
        registrationPage = new RegistrationPage(page);
        BrowserWindowsPage browserWindowsPage = new BrowserWindowsPage(page);
        page.navigate("https://www.tutorialspoint.com/selenium/practice/register.php");
        registrationPage.clickAlertFrameWindow();
        registrationPage.clickBrowserWindow();
        Page newWindowMessage = browserContext.waitForPage(browserWindowsPage::clickNewWindowMessageButton);
        Locator newWindowMessageHeader = newWindowMessage.locator("body");
        Locator newWindowHeader = newWindowMessageHeader.locator("h1").nth(1);
        newWindowHeader.waitFor(new Locator.WaitForOptions().setTimeout(5000));
        assertThat(newWindowHeader).hasText("New Window Message", new LocatorAssertions.HasTextOptions().setTimeout(5000));
        newWindowMessageHeader.locator(".container").nth(1).waitFor(new Locator.WaitForOptions().setTimeout(5000));
        Assert.assertTrue(newWindowMessageHeader.locator(".container").nth(1).textContent().contains("Knowledge increases by sharing but not by saving"), "New window content does not match expected value.");
    }


    @AfterMethod
    public void tearDown() {
        System.out.println("Tearing down the test environment...");
        if (page != null) {
            page.close();
        }
        if (browserContext != null) {
            browserContext.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
