package com.first;

import com.microsoft.playwright.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FirstPlayWright {
    public static void main(String[] args) {

        Playwright playwright=Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false).setArgs(Collections.singletonList("--start-maximized")).setSlowMo(1000));
        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        Page page=context.newPage();
            page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            Locator userName=page.locator("//div[.='Username']/following-sibling::div/child::input");
            highlightElement(userName,"red");
            userName.fill("Admin");
            Locator password=page.locator("//div[.='Password']/following-sibling::div/child::input");
            highlightElement(password,"red");
            password.fill("admin123");
            Locator loginBtn=page.locator("button:has-text('Login')");
            highlightElement(loginBtn,"blue");
            loginBtn.click();
            Locator dashboard=page.locator("//h6");
            assertThat(dashboard).hasText("Dashboard");
            System.out.println(page.title());
        page.pause();
        // Click b:has-text("Leave")
        page.locator("b:has-text(\"Leave\")").click();
        // assertThat(page).hasURL("https://opensource-demo.orangehrmlive.com/index.php/leave/viewLeaveList");
        // Check #leaveList_chkSearchFilter_checkboxgroup_allcheck
        page.locator("#leaveList_chkSearchFilter_checkboxgroup_allcheck").check();
        // Click input[name="leaveList\[txtEmployee\]\[empName\]"]
        page.locator("input[name=\"leaveList\\[txtEmployee\\]\\[empName\\]\"]").click();
        // Fill input[name="leaveList\[txtEmployee\]\[empName\]"]
        page.locator("input[name=\"leaveList\\[txtEmployee\\]\\[empName\\]\"]").fill("Krishnasai");
        // Press Enter
        page.locator("input[name=\"leaveList\\[txtEmployee\\]\\[empName\\]\"]").press("Enter");
        // Select 13
        page.locator("select[name=\"leaveList\\[cmbSubunit\\]\"]").selectOption("13");
        // Click text=Search
        page.locator("text=Search").click();
        // assertThat(page).hasURL("https://opensource-demo.orangehrmlive.com/index.php/leave/viewLeaveList");
        // Close page
        page.close();
        browser.close();
    }
    public static void highlightElement( Locator locator, String color) {
        locator.evaluate("element => {" +
                "element.style.border = '3px solid " + color + "';" +
                "}");
    }
}
