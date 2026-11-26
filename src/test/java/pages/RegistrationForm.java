package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;
import org.testng.Assert;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class RegistrationForm {
    private Page page;

    public RegistrationForm(Page page) {
        this.page = page;
    }


    public void setFirstName(String firstName) {
        page.getByLabel("Name:").fill(firstName);
    }

    public void setEmail(String email) {
        page.getByPlaceholder("name@example.com").fill(email);
    }

    public void chooseGender(int index) {
        Locator radioButton = page.locator("[type='radio']").nth(index);
        radioButton.check();
        assertThat(radioButton).isChecked();
    }

    public void setPhone(String phone) {
        page.locator("#mobile").fill(phone);
    }

    public void setDob(String dob) {
        Locator dobLocator = page.locator("#dob");
        dobLocator.fill(dob);
//        page.getByRole(AriaRole.GRIDCELL, new Page.GetByRoleOptions().setName("dob")).fill(dob);

    }

    public void setSubject(String subject) {
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Subjects")).fill(subject);
    }

    public void checkHobbies(int index) {
        Locator hobbyCheckbox = page.locator("[type='checkbox']").nth(index);
        hobbyCheckbox.check();
        assertThat(hobbyCheckbox).isChecked();
    }

    public void uploadPicture() {
        page.locator("[type=\"file\"]").setInputFiles(Paths.get("./src/test/resources/img.png"));
    }

    public void setAddress(String address) {
        page.getByPlaceholder("Currend Address").fill(address, new Locator.FillOptions().setTimeout(5000));
    }

    public void selectState(String state) {
        Locator stateDropdown = page.locator("#state");
        stateDropdown.selectOption(new SelectOption().setLabel(state), new Locator.SelectOptionOptions().setTimeout(5000));
        assertThat(stateDropdown).hasValue(state);
    }

    public void selectCity(String city) {
        Locator cityDropdown = page.locator("#city");
        cityDropdown.selectOption(new SelectOption().setLabel(city), new Locator.SelectOptionOptions().setTimeout(5000));
        assertThat(cityDropdown).hasValue(city);
    }

    public void clickLogin() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click(new Locator.ClickOptions().setTimeout(5000));
    }

    public String headerText() {
       return page.locator("h1").nth(1).textContent();
    }
}
