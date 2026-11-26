package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class RegistrationPage {

    private Page page;


    public RegistrationPage(Page page) {
        this.page = page;
    }


    public void setFirstName(String firstName) {
        page.getByPlaceholder("First Name").fill(firstName);
    }

    public void setLastName(String lastName) {
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("lastname")).fill(lastName);
    }

    public void setUsername(String username) {
        page.locator("#username").fill(username);
    }

    public void setPassword(String password) {
        page.locator("input[name='password']").fill(password, new Locator.FillOptions().setTimeout(5000));
    }

    public String getPageHeader() {
        return page.locator("h1").first().textContent();
    }

    public void clickRegisterButton() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Register")).click(new Locator.ClickOptions().setTimeout(5000));
    }

    public void clickAlertFrameWindow() {
        Locator alertFrameWindowLink = page.getByText(" Alerts, Frames & Windows ",  new Page.GetByTextOptions().setExact(false));
        alertFrameWindowLink.waitFor(new Locator.WaitForOptions().setTimeout(5000));
        alertFrameWindowLink.click();
    }

    public void clickBrowserWindow() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Browser Windows")).click();
    }

    public void clickAlertTab() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Alerts")).click();
    }



}
