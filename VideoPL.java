import com.microsoft.playwright.*;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class VideoPL {

    @Test
    public void launch() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        BrowserContext browserContext= browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("MyVideo/")));
        Page page = browserContext.newPage();
        page.navigate("https://www.saucedemo.com/");

        String title = page.title();
        assertThat(page).hasTitle("Swag Labs");
        System.out.println("Title is expected: " + title);

        assertThat(page.locator("#user-name")).hasId("user-name");
        page.click("#user-name");
        page.fill("#user-name", "standard_user");

        assertThat(page.locator("#password")).hasId("password");
        page.click("#password");
        page.fill("#password", "secret_sauce");


        assertThat(page.locator("#login-button")).isEnabled();
        page.click("#login-button");
        System.out.println("the Login Button is Enabled ");

        // page.locator("#login-button");

        browserContext.close();
        page.close();
        playwright.close();


    }
}
