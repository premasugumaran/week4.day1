
package week4.day1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		// Sysytem.setProperty("webdriver.chrome.driver","c:\\users\\chromedriver.exe");")
		// launch the browser
		ChromeDriver driver = new ChromeDriver();
		// load url
		driver.get("http://developer.servicenow.com");
		// Maximize the browser
		driver.manage().window().maximize();
				// Enter the frame
				driver.switchTo().frame("gsft_main");
				driver.findElement(By.id("user_name")).sendKeys("admin");
				driver.findElement(By.id("user_password")).sendKeys("pnFNeqw5GWB8");
				driver.findElement(By.id("sysverb_login")).click();
				Thread.sleep(3000);

				WebElement search = driver.findElement(By.xpath("//input[@id='filter']"));
				search.sendKeys("incident");
				Thread.sleep(2000);
				search.sendKeys(Keys.ENTER);
				Thread.sleep(1000);
				// Enter the frame
				driver.switchTo().frame("gsft_main");
				driver.findElement(By.xpath("//b[text()='All']")).click();
				Thread.sleep(500);
				driver.findElement(By.id("sysverb_new")).click();
				driver.findElement(By.xpath("//span[@class='icon icon-search']")).click();
				// Switch to the newly opened window
				Set<String> windowHandles = driver.getWindowHandles();
				List<String> window1 = new ArrayList<String>(windowHandles);
				driver.switchTo().window(window1.get(1));
				driver.findElement(By.xpath("//td/a")).click();
				Thread.sleep(1000);
				// Moving the control to parent window
				driver.switchTo().window(window1.get(0));
				driver.switchTo().frame("gsft_main");
				driver.findElement(By.xpath("//a[@id='lookup.incident.short_description']")).click();
				//Switching to new window
				Set<String> windowHandles1 = driver.getWindowHandles();
				List<String> window2 = new ArrayList<String>(windowHandles1);
				driver.switchTo().window(window2.get(1));
				driver.findElement(By.xpath("//td/a")).click();
				Thread.sleep(1000);
				driver.switchTo().window(window2.get(0));
				driver.switchTo().frame("gsft_main");
				// Getting the attribute of incident number
				WebElement num = driver.findElement(By.xpath("//input[@id='incident.number']"));
				String incidentNum = num.getAttribute("value");
				System.out.println("Incident Number : " + incidentNum);
				driver.findElement(By.id("sysverb_insert_bottom")).click();
				Thread.sleep(2000);
				WebElement search1 = driver.findElement(By.xpath("//input[@class='form-control']"));
				search1.sendKeys(incidentNum);
				search1.sendKeys(Keys.ENTER);
				Thread.sleep(500);
				String inciNum = driver.findElement(By.xpath("//td[@class='vt']/a")).getText();
				if(inciNum.equals(incidentNum)) {
					System.out.println("The incident creation is Successful");
					File source = driver.getScreenshotAs(OutputType.FILE);
					File destination = new File("C:/Users/Rishikesh/Desktop/images/Incident.png");
					FileUtils.copyFile(source, destination);
				}else {
					System.out.println("The incident creation is unsuccessful");
				}
				driver.close();

			}
}
