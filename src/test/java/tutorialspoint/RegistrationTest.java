package tutorialspoint;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class RegistrationTest extends BasePlayTest {

    RegistrationPage registrationPage;


    //    With page objects
    @Test(description = "Test registration functionality")
    public void testRegistration() {
        System.out.println("Starting the registration test...");

        registrationPage = new RegistrationPage(page);
        page.navigate("https://www.tutorialspoint.com/selenium/practice/register.php");
        registrationPage.setFirstName("John");
        registrationPage.setLastName("Doe");
        registrationPage.setUsername("john.doe@example.com"); // Set a timeout of 5 seconds for the fill action
        registrationPage.setPassword("Doe12312");
        page.waitForTimeout(2000); // Wait for 2 seconds to ensure the page is loaded
        registrationPage.clickRegisterButton(); // Set a timeout of 5 seconds for the click action
        assertThat(page.getByText("Welcome,Register")).isVisible();
        Assert.assertEquals(registrationPage.getPageHeader(), "Selenium - Automation Practice Form");
    }


    //Plan test


//    @Test(description = "Test registration functionality")
//    public void testRegistration() {
//        System.out.println("Starting the registration test...");
//        page.navigate("https://www.tutorialspoint.com/selenium/practice/register.php");
//        page.getByPlaceholder("First Name").fill("John");
//        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("lastname")).fill("Doe");
//        page.locator("#username").fill("john.doe@example.com", new Locator.FillOptions().setTimeout(5000)); // Set a timeout of 5 seconds for the fill action
//        page.locator("input[name='password']").fill("Doe12312");
//        page.waitForTimeout(2000); // Wait for 2 seconds to ensure the page is loaded
//        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Register")).click(new Locator.ClickOptions().setTimeout(5000)); // Set a timeout of 5 seconds for the click action
//
//        assertThat(page.getByText("Welcome,Register")).isVisible();
//    }
}
