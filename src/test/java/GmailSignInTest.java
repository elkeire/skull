import com.hlp.test.pageobjects.EmailHomePage;
import com.hlp.test.pageobjects.EmailViewPage;
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
        EmailHomePage emailHomePage = signInPage.clickSignIn(driver);
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
    	SignInPage signInPage = WebUtils.goToSignInPage(driver);
        //2. Fill in the username
        signInPage.fillInUsername(driver, "iamanothergod");
        signInPage.clickNxt(driver);
        //3. Fill in the password
        signInPage.fillInPassword(driver, "Iamanothergod01");
        //4. Click signin
        signInPage.clickStayLoggedIn(driver);//default is checked  Stop persistent Cookie
        EmailHomePage emailHomePage  = signInPage.clickSignIn(driver);
        //verify signed in 
        Assert.assertTrue("Inbox should exist", driver.findElements(By.partialLinkText("Inbox")).size() > 0);
        // 2. Click compose
        emailHomePage.clickComposeButton(driver);
        
        // 3. Fill in recipient
        emailHomePage.fillInRecipient(driver, "");
        
        // 4. Fill in subject
        final String subject = "Gmail Email Test";
        emailHomePage.fillInSubject(driver, subject);
        
        // 5. Fill in email bidy
        final String body = "Hello Testers!!";
        emailHomePage.fillInEmailBody(driver, body);
        
        // 6. Click Send  *= means that it contains that text (partially)
        emailHomePage.clickSendEmail(driver);
        // 7. Click Inbox ahaim
        
        emailHomePage.clickEmailLink(driver);
        // 8, Click email
        EmailViewPage emailViewPage = emailHomePage.clickNewEmail(driver); //Reading the email

        // 9. Verify the email subject and wmail bidu is correct
        String actualSubject = emailViewPage.getEmailSubjectText(driver);
        
        Assert.assertEquals("Gmail Email Test", subject, actualSubject);
        
        String bodyText = emailViewPage.getEmailBodyText(driver);
        
        Assert.assertEquals("Text does not match", body, bodyText);

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
        emailHomePage.signOut(driver);

    }

    @After
    public void tearDown()
    {
        driver.quit();
    }
}
