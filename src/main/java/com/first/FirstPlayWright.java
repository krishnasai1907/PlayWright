package com.first;

import com.microsoft.playwright.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FirstPlayWright {
    public static void main(String[] args) {

        Playwright playwright=Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false).setArgs(Collections.singletonList("--start-maximized")));
        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        Page page=context.newPage();
        page.navigate("https://opensource-demo.orangehrmlive.com/");
        System.out.println("opening");
        page.locator("id=txtUsername").type("Admin");
        page.locator("id=txtPassword").type("admin123");
        page.locator("id=btnLogin").click();
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

}
