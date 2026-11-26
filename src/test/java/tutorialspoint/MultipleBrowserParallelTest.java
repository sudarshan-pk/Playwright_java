package tutorialspoint;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BrowserWindowsPage;
import pages.RegistrationPage;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MultipleBrowserParallelTest extends PlaywrightParallelThread {

    @Test(description = "Test opening a new browser tab")
    public void testNewBrowserTab() {
        Page page = getPage();
        BrowserContext browserContext =getBrowserContext();
        RegistrationPage registrationPage = new RegistrationPage(page);
        BrowserWindowsPage browserWindowsPage = new BrowserWindowsPage(page);
//        Page page = getPage();

        page.navigate("https://www.tutorialspoint.com/selenium/practice/register.php");
        registrationPage.clickAlertFrameWindow();
        registrationPage.clickBrowserWindow();

        Page newPage = browserContext.waitForPage(() -> browserWindowsPage.clickNewTabButton());
        assertThat(newPage.locator("h1").nth(1))
                .hasText("New Tab", new LocatorAssertions.HasTextOptions().setTimeout(5000));
        newPage.locator(".container").nth(1)
                .waitFor(new Locator.WaitForOptions().setTimeout(5000));
        Assert.assertTrue(
                newPage.locator(".container").nth(1).textContent().contains("Sample New Tab"),
                "New tab content does not match expected value."
        );
    }

    @Test(description = "Test opening a new browser window")
    public void testNewBrowserWindow() {
        Page page = getPage();
        BrowserContext browserContext =getBrowserContext();
        RegistrationPage registrationPage = new RegistrationPage(page);
        BrowserWindowsPage browserWindowsPage = new BrowserWindowsPage(page);
        page.navigate("https://www.tutorialspoint.com/selenium/practice/register.php");
        registrationPage.clickAlertFrameWindow();
        registrationPage.clickBrowserWindow();

        Page newWindow = browserContext.waitForPage(() -> browserWindowsPage.clickNewWindowButton());
        Locator header = newWindow.locator("h1").nth(1);
        header.waitFor(new Locator.WaitForOptions().setTimeout(5000));
        assertThat(header)
                .hasText("New Window", new LocatorAssertions.HasTextOptions().setTimeout(5000));
        newWindow.locator(".container").nth(1)
                .waitFor(new Locator.WaitForOptions().setTimeout(5000));
        Assert.assertTrue(
                newWindow.locator(".container").nth(1).textContent().contains("Sample Text"),
                "New window content does not match expected value."
        );
    }

    @Test(description = "Test opening a new browser window message")
    public void testNewBrowserWindowMessage() {
        Page page = getPage();
        BrowserContext browserContext =getBrowserContext();
        RegistrationPage registrationPage = new RegistrationPage(page);
        BrowserWindowsPage browserWindowsPage = new BrowserWindowsPage(page);
        page.navigate("https://www.tutorialspoint.com/selenium/practice/register.php");
        registrationPage.clickAlertFrameWindow();
        registrationPage.clickBrowserWindow();

        Page newWindowMessage = browserContext.waitForPage(browserWindowsPage::clickNewWindowMessageButton);
        Locator header = newWindowMessage.locator("body").locator("h1").nth(1);
        header.waitFor(new Locator.WaitForOptions().setTimeout(5000));
        assertThat(header)
                .hasText("New Window Message", new LocatorAssertions.HasTextOptions().setTimeout(5000));
        newWindowMessage.locator(".container").nth(1)
                .waitFor(new Locator.WaitForOptions().setTimeout(5000));
        Assert.assertTrue(
                newWindowMessage.locator(".container").nth(1).textContent()
                        .contains("Knowledge increases by sharing but not by saving"),
                "New window content does not match expected value."
        );
    }
}