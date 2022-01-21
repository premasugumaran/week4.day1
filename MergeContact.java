package week4.day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();

		ChromeDriver driver = new ChromeDriver();
		// load url
		driver.get("http://leaftaps.com/opentaps/");
		// Maximize the browser
		driver.manage().window().maximize();
		// implicit wait

		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		// get title
		// String title = driver.getTitle();
		// System.out.println(title);

		// enter username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("DemoSalesManager");
		// Enter Password

		driver.findElement(By.id("password")).sendKeys("crmsfa");
		// click login button
		driver.findElement(By.className("decorativeSubmit")).click();
		// click CRM/SFA

		driver.findElement(By.linkText("CRM/SFA")).click();

		driver.findElement(By.xpath("//a[text()='Contacts']")).click();

		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();

		driver.findElement(By.xpath("//input[@id = 'partyIdFrom']/following::a")).click();
		Thread.sleep(1000);

		Set<String> handles = driver.getWindowHandles();
		List<String> windows = new ArrayList<String>(handles);
		// String childwindow = windows.get(1);
		driver.switchTo().window(windows.get(1));

		/*
		 * Set<String> window1 = driver.getWindowHandles(); List<String> load = new
		 * ArrayList<String>(window1); driver.switchTo().window(load.get(1)); //Move to
		 * the new Window
		 */
		driver.findElement(By.xpath("//div[@class = 'x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
		driver.switchTo().window(windows.get(0));

		driver.findElement(By.xpath("//input[@id = 'partyIdTo']/following::a")).click();
		Thread.sleep(1000);

		Set<String> handles1 = driver.getWindowHandles();
		List<String> windows1 = new ArrayList<String>(handles1);
		// String childwindow = windows.get(1);
		driver.switchTo().window(windows1.get(1));
		driver.findElement(By.xpath("(//div[@class = 'x-grid3-cell-inner x-grid3-col-partyId']/a)[2]")).click();
		driver.switchTo().window(windows1.get(0));

		//driver.findElement(By.xpath("//img[@alt='Lookup'])[2]")).click();

		//driver.findElement(By.xpath("//img[@alt='Lookup'][1]")).click();

		// to merge contacts
		driver.findElement(By.className("buttonDangerous")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();

	}

}
