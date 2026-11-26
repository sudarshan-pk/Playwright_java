package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class AlertPage {

    private final Page page;

    public AlertPage(Page page) {
        this.page = page;
    }


    public void clickAlertButton() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Alert").setExact(true)).click();
    }

    public void clickMeAlertTab() {
        page.locator(".btn-primary").nth(2).click(new Locator.ClickOptions().setTimeout(3000));
    }

    public String returnMessage() {
        return page.locator("#desk").textContent();
    }

    public void clickMePromptAlertTab() {
        page.locator(".btn-primary").nth(3).click(new Locator.ClickOptions().setTimeout(3000));
    }

    public void clickMeWaitAlertTab() {
        page.locator(".btn-primary").nth(1).click(new Locator.ClickOptions().setTimeout(7000));
    }

}
