package selenium;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MatchiSelenium {

	private WebDriver webDriver;

	
	//One constructor for various sizes
	public MatchiSelenium(String size) {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		webDriver = new ChromeDriver();
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		if (size.contains("max")) {
			webDriver.manage().window().maximize();
		} else if (size.contains("ipad")) {
			webDriver.manage().window().setSize(new Dimension(768, 1080));
		} else if (size.contains("mobile")) {
			webDriver.manage().window().setSize(new Dimension(375, 1080));
		}
		goToStart();

	}
	
	//One constructor without parameter for always maxed
	public MatchiSelenium() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		webDriver = new ChromeDriver();
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		webDriver.manage().window().maximize();
		goToStart();
	}

	public String getUrl() {
		return webDriver.getCurrentUrl();
	}

	public String checkLogin() {
		WebElement e = webDriver.findElement(By.linkText("Mjuk"));
		return e.getText();
	}

	
	public void goToStart() {
		webDriver.get("https://beta1.matchi.se/");
	}

	public void quitSelenium() {
		delay(200);
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

		//Click "anläggningar"
		webDriver.findElement(By.xpath("//a[contains(text(),'Anläggningar')]")).click();
		//
		
		//Search for court
		webDriver.findElement(By.id("q")).click();
		webDriver.findElement(By.id("q")).clear();
		webDriver.findElement(By.id("q")).sendKeys(search);
		webDriver.findElement(By.xpath("//input[@id='submit']")).click();
		//
		
		//Click the searched court
		webDriver.findElement(By.linkText(search)).click();
		//
		
		//Click at "22:00" Bana 2
		//(Only works if the browser shows the white squares
		//and not the squares with time in them 
		//(These switch based on browser width)
		webDriver.findElement(By.xpath(
				"//tbody//tr[3]//td[2]//table[1]//tbody[1]//tr[1]//td[16]"))
				.click();
		delay(2000);


	}
	
	public void paymentCard() {
		
		delay(1500);
		
//
		
		//Click "Nytt kort" if the use an existing card exist
		//(If use an existing card doesn't exist it selects "Nytt kort" by default
		try {
		webDriver.findElement(By.xpath("//label[@for='CREDIT_CARD']")).click();
		} catch (Exception e) {
			
		}
		webDriver.findElement(By.cssSelector("#btnSubmit")).click();
		delay(500);
		//
		
		WebElement e;
		//Enter kortnummer
		e = webDriver.findElement(By.xpath("//input[@placeholder='Kortnummer']"));
		e.click();
		e.sendKeys("2223000048410010");
		//
		
		//Enter name
		e = webDriver.findElement(By.cssSelector("#adyen-encrypted-form > div.modal-body.relative > div > div > div:nth-child(2) > div:nth-child(2) > input"));
		e.click();
		e.sendKeys("Mjuk Varutestare");
		//
		
		//Enter month
		webDriver.findElement(By.xpath("//option[@value='10']")).click();
		
		//Enter year
		webDriver.findElement(By.xpath("//option[@value='2020']")).click();
		
		//Enter cvc
		e = webDriver.findElement(By.xpath("//input[@placeholder='cvc / cid']"));
		e.click();
		e.sendKeys("737");
		
		//Click "Slutför betalning"
		webDriver.findElement(By.xpath("//input[@value='Slutför betalning']")).click();
		delay(2000);
		webDriver.findElement(By.xpath("//a[@class='btn btn-success']")).click();
		//
		
		delay(400);
		
		}
		
	public void paymentSwish(String mobileNr) {
		delay(1500);
		// Click "Swish" 
		webDriver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Nytt konto-/kreditkort'])[1]/following::label[1]")).click();
		webDriver.findElement(By.id("btnSubmit")).click();
		// Klicka i mobilnummer rutan
		webDriver.findElement(By.id("swish.telephoneNumber")).click();
		webDriver.findElement(By.id("swish.telephoneNumber")).clear();
		//Skriv in ett test mobilnr
		webDriver.findElement(By.id("swish.telephoneNumber")).sendKeys(mobileNr);
		webDriver.findElement(By.id("mainSubmit")).click();
		delay(2000);
		// Här failar Swish betalningen
		// TO-DO höra på fredag varför det failar och skriva klart swish betalningen
		
		
		}
	
	public void paymentCardWrongCVC() {
		
		delay(1500);
		
		//
		
		//Click "Nytt kort" if the use an existing card exist
		//(If use an existing card doesn't exist it selects "Nytt kort" by default
		try {
			webDriver.findElement(By.xpath("//label[@for='CREDIT_CARD']")).click();
			} catch (Exception e) {
				
			}
		webDriver.findElement(By.cssSelector("#btnSubmit")).click();
		delay(500);
		//
		
		WebElement e;
		//Enter kortnummer
		e = webDriver.findElement(By.xpath("//input[@placeholder='Kortnummer']"));
		e.click();
		e.sendKeys("2223000048410010");
		//
		
		//Enter name
		e = webDriver.findElement(By.cssSelector("#adyen-encrypted-form > div.modal-body.relative > div > div > div:nth-child(2) > div:nth-child(2) > input"));
		e.click();
		e.sendKeys("Mjuk Varutestare");
		//
		
		//Enter month
		webDriver.findElement(By.xpath("//option[@value='10']")).click();
		
		//Enter year
		webDriver.findElement(By.xpath("//option[@value='2020']")).click();
		
		//Enter wrong CVC
		e = webDriver.findElement(By.xpath("//input[@placeholder='cvc / cid']"));
		e.click();
		e.sendKeys("738");
		webDriver.findElement(By.xpath("//input[@value='Slutför betalning']")).click();
		
		
		delay(2000);
	}

	public boolean checkWrongCVCNumber(String checkCVC) {
		WebElement element = webDriver.findElement(By.xpath("//*[@id=\"userBookingModal\"]/div[1]/div/div[2]/h6"));
		String text = element.getText();
		System.out.println(text);
		delay(2000);
		webDriver.findElement(By.xpath("//a[@class='btn btn-success']")).click();
		return text.contains(checkCVC);
	}
		
	public void unbook() {
		
		
		//Click calendar
		delay(1500);
		webDriver.findElement(By.xpath("//*[@id=\"navbar-collapse\"]/ul[2]/li[3]/a")).click();
				
		//Click booking at top in dropdown
		webDriver.findElement(By.xpath("//a[@class='userCancelBooking']//div[@class='media']")).click();
		delay(500);
				
		//Click "Avboka"
		webDriver.findElement(By.xpath("//a[@class='btn btn-danger'][contains(text(),'Avboka')]")).click();
				
		//Click away the window that appears
		webDriver.findElement(By.xpath("//button[@id='cancelCloseBtn']")).click();
		
	}

	public boolean checkIfBooked() {
		
		
		//Se ifall det finns en ikon på schemaknappen längst upp på sidan
		try {
		webDriver.findElement(By.xpath("//span[@class='badge']"));
		} catch (Exception e) {
			return false;
		}
		return true;
		
		

	}

	public void delay(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
		}
	}

}
