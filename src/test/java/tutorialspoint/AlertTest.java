package tutorialspoint;

import com.microsoft.playwright.Dialog;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AlertPage;
import pages.RegistrationPage;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

public class AlertTest extends BasePlayTest {

    private RegistrationPage registrationPage;

    @Test(description = "Test handling alerts")
    public void testAlertHandling() {
        System.out.println("Starting the alert handling test...");
        // Implement your alert handling test logic here

        registrationPage = new RegistrationPage(page);
        AlertPage alertPage = new AlertPage(page);
        page.navigate("https://www.tutorialspoint.com/selenium/practice/register.php");
        registrationPage.clickAlertFrameWindow();
        registrationPage.clickAlertTab();
        page.onceDialog(dialog -> {
            dialog.accept();
            System.out.println("Message: " + dialog.message());
        });
        alertPage.clickAlertButton();
    }

    @Test(description = "Test handling alerts02")
    public void testAlertMessageHandling() {
        System.out.println("Starting the alert handling test...");
        registrationPage = new RegistrationPage(page);
        AlertPage alertPage = new AlertPage(page);
        page.navigate("https://www.tutorialspoint.com/selenium/practice/register.php");
        registrationPage.clickAlertFrameWindow();
        registrationPage.clickAlertTab();
        page.onceDialog(dialog2 -> {
            dialog2.accept();
            System.out.println("Message: " + dialog2.message());
        });
        alertPage.clickMeAlertTab();
        Assert.assertEquals(alertPage.returnMessage(), "You pressed OK!");

        page.onceDialog(dialog2 -> {
            dialog2.dismiss();
            System.out.println("Message: " + dialog2.message());
        });
        alertPage.clickMeAlertTab();
        Assert.assertEquals(alertPage.returnMessage(), "You pressed Cancel!");

    }

    @Test(description = "Test handling alerts03")
    public void testAlertSendMessageHandling() {
        System.out.println("Send message to the alert");
        registrationPage = new RegistrationPage(page);
        AlertPage alertPage = new AlertPage(page);
        page.navigate("https://www.tutorialspoint.com/selenium/practice/register.php");
        registrationPage.clickAlertFrameWindow();
        registrationPage.clickAlertTab();
        page.onceDialog(dialog3 -> {
            dialog3.accept("Sending text message");
            System.out.println("Message: " + dialog3.message());
        });
        alertPage.clickMePromptAlertTab();
//        Assert.assertEquals(alertPage.returnMessage(), "You pressed OK!");
    }

    @Test(description = "Test handling alerts04")
    public void testAlertWaitHandling() {
        System.out.println("Wait alert");
        registrationPage = new RegistrationPage(page);
        AlertPage alertPage = new AlertPage(page);
        page.navigate("https://www.tutorialspoint.com/selenium/practice/register.php");
        registrationPage.clickAlertFrameWindow();
        registrationPage.clickAlertTab();

        AtomicBoolean handled = new AtomicBoolean(false);

// 1. Register listener BEFORE the click
        page.onceDialog(dialog -> {
            System.out.println("Message: " + dialog.message());
            dialog.accept();          // ← required, otherwise page freezes
            handled.set(true);// ← signals the wait below

            Assert.assertEquals(dialog.message(), "Hello just appeared");
        });

// 2. Click returns immediately (the alert is triggered via setTimeout in the app)
        alertPage.clickMeWaitAlertTab();

// 3. THIS is the wait — blocks ~4s until the dialog fires and handler sets flag
        page.waitForCondition(() -> handled.get(),
                new Page.WaitForConditionOptions().setTimeout(10000)); // max 10s safety net


    }


}
