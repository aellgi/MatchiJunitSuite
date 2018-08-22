package selenium;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MatchiSelenium {

	private WebDriver webDriver;

	public MatchiSelenium() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		webDriver = new ChromeDriver();
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		goToStart();

	}

	public String getUrl() {
		return webDriver.getCurrentUrl();
	}

	public String checkLogin() {
		WebElement e = webDriver.findElement(By.linkText("Mjuk"));
		return e.getText();
	}

	public void chooseSport(String sport) {
		WebElement e;
		e = webDriver.findElement(By.cssSelector(
				"#hero > div > div > div.row > div > form > div > div.form-group.col-sm-3.col-xs-12.no-margin-padding > div > button > span.filter-option.pull-left"));
		e.click();

		if (sport.contains("tennis")) {

			e = webDriver.findElement(By.cssSelector(
					"#hero > div > div > div.row > div > form > div > div.form-group.col-sm-3.col-xs-12.no-margin-padding > div > div > ul > li:nth-child(2) > a"));
			e.click();
		}

		if (sport.contains("Badminton")) {

			e = webDriver.findElement(By.cssSelector(
					"#hero > div > div > div.row > div > form > div > div.form-group.col-sm-3.col-xs-12.no-margin-padding > div > div > ul > li:nth-child(3) > a"));
			e.click();
		}

	}

	public void goToStart() {
		webDriver.get("https://beta1.matchi.se/");
	}

	public void quitSelenium() {
		webDriver.quit();
	}

	public void search(String text) {

		WebElement element = webDriver.findElement(By.cssSelector("#q"));
		element.clear();
		element.sendKeys(text);
		element.sendKeys(Keys.RETURN);
	}

	public void login(String username, String password) {

		webDriver.findElement(By.cssSelector("#navbar-collapse > ul.nav.navbar-nav.navbar-right > li:nth-child(2) > a"))
				.click();
		WebElement e = webDriver.findElement(By.cssSelector("#username"));
		e.click();
		e.sendKeys(username);
		e = webDriver.findElement(By.cssSelector("#password"));
		e.click();
		e.sendKeys(password);
		webDriver.findElement(By.cssSelector("#loginForm > button")).click();
	}

	public void bookCourt(String search) {

		webDriver.findElement(By.cssSelector("#navbar-collapse > ul.nav.navbar-nav.navbar-left > li:nth-child(2) > a"))
				.click();
		webDriver.findElement(By.id("q")).click();
		webDriver.findElement(By.id("q")).clear();
		webDriver.findElement(By.id("q")).sendKeys(search);
		webDriver.findElement(By.cssSelector("#submit")).click();
		webDriver.findElement(By.linkText(search)).click();
		webDriver.findElement(By.cssSelector(
				"#schedule > div > div > div:nth-child(2) > table > tbody > tr:nth-child(3) > td:nth-child(2) > table > tbody > tr > td:nth-child(15)"))
				.click();
		delay(2000);
		//webDriver.findElement(By.cssSelector("#confirmForm > div.modal-body > div:nth-child(5) > div.col-sm-8.col-xs-8 > div.form-group.no-bottom-margin > div > label"))
		//		.click();
		try {
		webDriver.findElement(By.xpath("//label[@for='CREDIT_CARD']")).click();
		} catch (Exception e) {
			
		}
		webDriver.findElement(By.cssSelector("#btnSubmit")).click();
		delay(500);


	}
	
	public void paymentCard() {
		
		delay(1500);
		
		WebElement e;
		e = webDriver.findElement(By.xpath("//input[@placeholder='Kortnummer']"));
		e.click();
		e.sendKeys("2223000048410010");
		e = webDriver.findElement(By.cssSelector("#adyen-encrypted-form > div.modal-body.relative > div > div > div:nth-child(2) > div:nth-child(2) > input"));
		e.click();
		e.sendKeys("Mjuk Varutestare");
		webDriver.findElement(By.xpath("//option[@value='10']")).click();
		webDriver.findElement(By.xpath("//option[@value='2020']")).click();
		e = webDriver.findElement(By.xpath("//input[@placeholder='cvc / cid']"));
		e.click();
		e.sendKeys("737");
		webDriver.findElement(By.xpath("//input[@value='Slutför betalning']")).click();
		delay(500);
		webDriver.findElement(By.xpath("//a[@class='btn btn-success']")).click();
		webDriver.findElement(By.xpath("//body/div[@id='wrap']/nav[@id='main-navigation']/div[@class='container']/div[@id='navbar-collapse']/ul[@class='nav navbar-nav navbar-right']/li[3]/a[1]")).click();
		delay(400);
		
		}
		
	public void paymentSwish() {
		WebElement e = webDriver.findElement(By.cssSelector("#adyen-encrypted-form > div.modal-body.relative > div > div > div:nth-child(2) > div.form-group.col-sm-12.has-feedback.has-feedback-right.has-error > div > input"));
		//e.click();
		e.sendKeys("2223000048410010");
		e = webDriver.findElement(By.cssSelector("#adyen-encrypted-form > div.modal-body.relative > div > div > div:nth-child(2) > div:nth-child(2) > input"));
		e.click();
		e.sendKeys("Mjuk Varutestare");
		e = webDriver.findElement(By.cssSelector("#adyen-encrypted-form > div.modal-body.relative > div > div > div:nth-child(2) > div:nth-child(3) > select"));
		e.click();
		webDriver.findElement(By.linkText("10")).click();
		
		
		}
	public void unbook() {
		
		delay(1500);
		
		webDriver.findElement(By.xpath("//a[contains(text(),'Avboka')]")).click();
		webDriver.findElement(By.xpath("//button[@id='cancelCloseBtn']")).click();
		
	}
		
		

	public String checkIfBooked() {
		webDriver.findElement(By.xpath("//a[contains(text(),'Avboka')]")).click();
		System.out.println(webDriver.findElement(By.xpath("//h5[@class='media-heading']")).getText());;
		return webDriver.findElement(By.xpath("//h5[@class='media-heading']")).getText();

	}

	public void delay(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
		}
	}

}
