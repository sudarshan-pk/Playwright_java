package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class BrowserWindowsPage {


    private Page page;
    private Locator newTabButton;
    private Locator newWindowButton;
    private Locator newWindowMessageButton;


    public BrowserWindowsPage(Page page){
        this.page = page;
    }


    public void clickNewTabButton() {
        newTabButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("New Tab"));
        newTabButton.click();
    }

    public void clickNewWindowButton() {
        newWindowButton = page.getByText("New Window", new Page.GetByTextOptions().setExact(true));
        newWindowButton.click();
    }

    public void clickNewWindowMessageButton() {
        newWindowMessageButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("New Window Message"));
        newWindowMessageButton.click();
    }
}
