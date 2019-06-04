package com.sofka.SeleniumTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class SofkaTest {

	WebDriver driver;

	@BeforeSuite
	public void setUp() {

		// Launch_firefox_browser
		System.setProperty("webdriver.gecko.driver", "C:\\Selenium\\Drivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		// maximize the browser
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// open the_url.
		driver.get("http://automationpractice.com/index.php");
	}

	@Test(priority = 0)
	public void Test_Index_Navigate() {

		// Click on the search
		WebElement search = driver.findElement(By.id("search_query_top"));
		search.click();

		// Enter Text.
		WebElement txtBox_firstname = driver.findElement(By.name("search_query"));
		txtBox_firstname.sendKeys("Dress");

		// Enter Search.
		WebElement submit = driver.findElement(By.name("submit_search"));
		submit.click();

		// Go Index.
		WebElement inicio = driver.findElement(By.id("header_logo"));
		inicio.click();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(priority = 1)
	public void Test_Contact() {
		
		// Executor java script
		JavascriptExecutor SelectExecutor = ((JavascriptExecutor) driver);
		WebElement email, reference, mensaje;

		try {
			// Click on the contact link:
			WebElement conta = driver.findElement(By.id("contact-link"));
			conta.click();
			
			// Ingresar_Informacion_Formulario
			Select select = new Select(driver.findElement(By.xpath("//*[@id=\"id_contact\"]")));
			//select.selectByVisibleText("Customer Service");
			select.selectByIndex(1);
			select.selectByValue("Customer Service");

			email = driver.findElement(By.xpath("//*[@id=\"email\"]"));
			SelectExecutor.executeScript("arguments[0].value='test2@gmail.com'",email);
			
			reference = driver.findElement(By.id("id_order"));
			SelectExecutor.executeScript("arguments[0].value='Texto de referencia'",reference);
			
			mensaje = driver.findElement(By.xpath("//*[@id=\\\"message\\\"]"));
			SelectExecutor.executeScript("arguments[0].value='Mensaje de texto de prueba'",mensaje);
			
			WebElement enviar = driver.findElement(By.id("submitMessage"));
			enviar.submit();
			
		} catch (Exception e) {
			
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@AfterTest
	public void doThis() {
		driver.quit();
	}
}
