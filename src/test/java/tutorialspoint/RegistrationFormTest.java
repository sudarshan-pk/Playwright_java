package tutorialspoint;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationForm;
import pages.RegistrationPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class RegistrationFormTest extends BasePlayTest {

    RegistrationForm registrationForm;


    //    With page objects
    @Test(description = "Test registration functionality")
    public void testRegistrationForm() {
        System.out.println("Starting the registration form test...");
        registrationForm = new RegistrationForm(page);
        page.navigate("https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php");
        registrationForm.setFirstName("John Doe");
        registrationForm.setEmail("john.doe@example.com");
        registrationForm.chooseGender(1);
        registrationForm.setPhone("1234567890");
        registrationForm.setDob("2025-12-15");
        registrationForm.setSubject("Maths");
        registrationForm.checkHobbies(2);
        registrationForm.uploadPicture();
        registrationForm.setAddress("123 Main St, Anytown, USA");
        registrationForm.selectState("Uttar Pradesh");
        registrationForm.selectCity("Lucknow");


        // Verify that the form was submitted successfully

        Assert.assertEquals(page.inputValue("#dob"), "2025-12-15", "Date of Birth is not set correctly.");
        registrationForm.clickLogin();
        Assert.assertEquals(registrationForm.headerText(), "Student Registration Form");

    }
}
