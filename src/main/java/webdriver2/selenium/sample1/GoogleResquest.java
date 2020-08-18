package webdriver2.selenium.sample1;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.io.IOException;
 

public class GoogleResquest {
	 public static void main(String[] args) throws IOException {
		 String current = new java.io.File( "." ).getCanonicalPath();
	        System.out.println("Current dir:"+current);
	        System.setProperty("webdriver.gecko.driver", "./browserLib/geckodriver.exe");
	        WebDriver driver = new FirefoxDriver();

	        WebDriverWait wait = new WebDriverWait(driver,10);
	        try {
	            driver.get("https://google.com/ncr");
	            driver.findElement(By.name("q")).sendKeys("cheese" + Keys.ENTER);
	            WebElement firstResult = wait.until(presenceOfElementLocated(By.cssSelector("h3>div")));
	            System.out.println(firstResult.getAttribute("textContent"));
	        } finally {
//	            driver.quit();
	        }
	    }
}
