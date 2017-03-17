import com.hlp.test.pageobjects.EmailHomePage;
import com.hlp.test.pageobjects.SignInPage;
import com.hlp.util.WebUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Sean on 14-Mar-17.
 */
public class GmailSignInTest {

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, 30);

    @BeforeClass
    public static void setup()
    {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
    }

    @Test
    public void signInSuccess()
    {

        //1. Go to gmail site
        SignInPage signInPage = WebUtils.goToSignInPage(driver);
        //2. Fill in the username
        signInPage.fillInUsername(driver, "iamanothergod");
        signInPage.clickNxt(driver);
        //3. Fill in the password
        signInPage.fillInPassword(driver, "Iamanothergod01");
        //4. Click signin
        signInPage.clickStayLoggedIn(driver);//default is checked  Stop persistent Cookie
        EmailHomePage emailHomePage  = signInPage.clickSignIn(driver);
        //5. verify did signin
        Assert.assertTrue("Inbox should exist", emailHomePage.isInboxExist(driver));
        //6. sign out
        signInPage = emailHomePage.signOut(driver);
        //7. verify
        Assert.assertTrue("Should be sign in button", signInPage.isNextButton(driver));


    }


    @Test
    public void gmailSendAndReceiveEmailTest()
    {
        // 1. Click Sign In
        driver.get("http://gmail.com");
        //2. Fill in the username
        WebElement usernameTextBox =  driver.findElement(By.id("Email"));
        usernameTextBox.clear();
        usernameTextBox.sendKeys("iamanothergod");
        WebElement usernameEnterButton = driver.findElement(By.id("next"));
        usernameEnterButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Passwd")));
        //3. Fill in the password
        WebElement passwordTextBox = driver.findElement(By.id("Passwd"));
        passwordTextBox.clear();
        passwordTextBox.sendKeys("Iamanothergod01");
        //4. Click signin
        WebElement signInButton = driver.findElement(By.id("signIn"));
        // Stop persistent Cookie
        WebElement staySignedIn = driver.findElement(By.id("PersistentCookie"));
        // UNCHECK Stay signed in
        staySignedIn.click();
        signInButton.click();
            //verify signed in
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox")));
        Assert.assertTrue("Inbox should exist", driver.findElements(By.partialLinkText("Inbox")).size() > 0);
        // 2. Click compose
        WebElement composeButtom = driver.findElement(By.cssSelector("div[role='button'][gh='cm']"));
        composeButtom.click();
        // 3. Fill in recipiwnt
        WebElement toTextBox = driver.findElement(By.cssSelector("textArea[name='to']"));
        toTextBox.clear();
        toTextBox.sendKeys("iamanothergod@gmail.com");
        // 4. Fill in subject
        WebElement subjectTextBox = driver.findElement(By.name("subjectbox"));
        final String subject = "Gmail Email Test";
        subjectTextBox.clear();
        subjectTextBox.sendKeys(subject);
        // 5. Fill in email bidy
        WebElement emailBodyTextArea = driver.findElement(By.cssSelector("div[aria-label='Message Body']"));
        final String body = "Hello Testers!!";
        emailBodyTextArea.clear();
        emailBodyTextArea.sendKeys(body);
        // 6. Click Send  *= means that it contains that text (partially)
        WebElement sendButton = driver.findElement(By.cssSelector("div[aria-label*=\"Send\"]"));
        sendButton.click();
        // 7. Click Inbox ahaim
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox (")));
        WebElement inboxlinkage = driver.findElement(By.partialLinkText("Inbox ("));
        inboxlinkage.click();
        // 8, Click email
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='xS'][role='link']")));
        WebElement newEmail = driver.findElement(By.cssSelector("div[class='xS'][role='link']"));
        newEmail.click();
        // 9. Verify the email subject and wmail bidu is correct
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2[class='hP']")));
        WebElement subjectArea = driver.findElement(By.cssSelector("h2[class='hP']"));
        Assert.assertEquals("Gmail Email Test", subject, subjectArea.getText());
        WebElement bodyArea = driver.findElement(By.cssSelector("div[class='nH aHU'] div[dir='ltr']"));
        Assert.assertEquals("Text does not match", body, bodyArea.getText());

//        /*Next phaze
//        * delete the email before signing out
//        */
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[role='checkbox'][dir='ltr']")));
//        WebElement selectEmail = driver.findElement(By.cssSelector("div[role='checkbox'][dir='ltr']"));
//        selectEmail.click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Delete'][data-tooltip='Delete']")));
//        WebElement dustbinIcon = driver.findElement(By.cssSelector("div[aria-label='Delete'][data-tooltip='Delete']"));
//        dustbinIcon.click();


        // 10, Sign Out
        WebElement profileButtonLinkage = driver.findElement(By.cssSelector("span[class='gb_9a gbii']"));
        profileButtonLinkage.click();
        WebElement signOutButton = driver.findElement(By.id("gb_71"));
        signOutButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("next")));
        Assert.assertTrue("Should be sign in button", driver.findElements(By.id("next")).size() > 0);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }
}
