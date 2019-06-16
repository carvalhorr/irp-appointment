package carvalhorr.irp.appointment.crawler;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class SearchAppointmentsCrawler {

	private static final int waitTimeAfterEachAction = 100;
	
	private WebDriver driver;
	private AppointmentDataExtractor extractor;
	private String inisWebsite;

	public SearchAppointmentsCrawler(WebDriver driver,
									 AppointmentDataExtractor extractor,
									 String inisWebsite) {
		this.driver = driver;
		this.extractor = extractor;
		this.inisWebsite = inisWebsite;
	}
	
	public void openWebsite() throws InterruptedException {
		driver.get(inisWebsite);
		fillForm();
	}

	public List<LocalDateTime> search() throws InterruptedException {
		clickSearchAppointments();
		String source = driver.getPageSource();
		AppointmentDataExtractor extractor = new AppointmentDataExtractor();
		List<LocalDateTime> availableAppointments = extractor.extractAppointments(source);
		return availableAppointments;
	}

	private void fillForm() throws InterruptedException {
        selectCategory("All");
        selectSubcategory("All");
        confirmPreviousGnib("Yes");
        typeGnibNumber("");
        selectGnibExpirationDate("07/08/2019");
        confirmStatementsAreTrue();
        typeGivenName("");
        typeSurname("");
        selectDateOfBirth("");
        selectNationality("");
        typeEmail("");
        typeEmailConfirmation("");
        confirmFamilyApplication("No");
        confirmHavePassport("Yes");
        typePassportNumber("");
        clickLookForAppointment();
        selectSearchMode("closest to today");
	}

	private void clickSearchAppointments() throws InterruptedException {
		WebElement btSrch = driver.findElement(By.id("btSrch4Apps"));
		btSrch.click();
		while ((btSrch.getAttribute("Disabled") != null)) {
			Thread.sleep(waitTimeAfterEachAction);
		}
	}
	
	private void selectSearchMode(String searchMode) throws InterruptedException {
		new Select(driver.findElement(By.id("AppSelectChoice")))
			.selectByVisibleText(searchMode);
		Thread.sleep(waitTimeAfterEachAction * 3);
	}

	private void clickLookForAppointment() throws InterruptedException {
		driver.findElement(By.id("btLook4App")).click();
		Thread.sleep(waitTimeAfterEachAction);
	}

	private void typePassportNumber(String passportNumber) throws InterruptedException {
		driver.findElement(By.id("PPNo")).sendKeys(passportNumber);
		Thread.sleep(waitTimeAfterEachAction);
	}

	private void confirmHavePassport(String havePassport) throws InterruptedException {
		new Select(driver.findElement(By.id("PPNoYN")))
			.selectByVisibleText(havePassport);
		Thread.sleep(waitTimeAfterEachAction);
	}

	private void confirmFamilyApplication(String familyApplication) throws InterruptedException {
		new Select(driver.findElement(By.id("FamAppYN")))
			.selectByVisibleText(familyApplication);
		Thread.sleep(waitTimeAfterEachAction);
	}

	private void typeEmailConfirmation(String email) throws InterruptedException {
		driver.findElement(By.id("EmailConfirm")).sendKeys(email);
		Thread.sleep(waitTimeAfterEachAction);
	}

	private void typeEmail(String email) throws InterruptedException {
		driver.findElement(By.id("Email")).sendKeys(email);
		Thread.sleep(waitTimeAfterEachAction);
	}

	private void selectNationality(String nationality) throws InterruptedException {
		new Select(driver.findElement(By.id("Nationality")))
			.selectByVisibleText(nationality);
		Thread.sleep(waitTimeAfterEachAction);
	}

	private void selectDateOfBirth(String dateOfBirth) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('DOB').setAttribute('value', '" + dateOfBirth + "')");
		Thread.sleep(waitTimeAfterEachAction);
	}

	private void typeSurname(String surname) throws InterruptedException {
		driver.findElement(By.id("SurName")).sendKeys(surname);
		Thread.sleep(waitTimeAfterEachAction);
	}

	private void typeGivenName(String givenName) throws InterruptedException {
		driver.findElement(By.id("GivenName")).sendKeys(givenName);
		Thread.sleep(waitTimeAfterEachAction);
	}

	private void confirmStatementsAreTrue() throws InterruptedException {
		driver.findElement(By.id("UsrDeclaration")).click();
		Thread.sleep(waitTimeAfterEachAction);
	}

	private void selectGnibExpirationDate(String gnibExpirationDate) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('GNIBExDT').setAttribute('value', '" + gnibExpirationDate + "')");
		Thread.sleep(waitTimeAfterEachAction);
	}

	private void typeGnibNumber(String gnibNumber) throws InterruptedException {
		driver.findElement(By.id("GNIBNo")).sendKeys(gnibNumber);
		Thread.sleep(waitTimeAfterEachAction);
	}

	private void confirmPreviousGnib(String previousGnib) throws InterruptedException {
		new Select(driver.findElement(By.id("ConfirmGNIB")))
			.selectByVisibleText(previousGnib);
		Thread.sleep(waitTimeAfterEachAction);
	}

	private void selectSubcategory(String subcategory) throws InterruptedException {
		new Select(driver.findElement(By.id("SubCategory")))
			.selectByVisibleText(subcategory);
		Thread.sleep(waitTimeAfterEachAction);
	}

	private void selectCategory(String category) throws InterruptedException {
		new Select(driver.findElement(By.id("Category")))
			.selectByVisibleText(category);
		Thread.sleep(waitTimeAfterEachAction);
	}
	
}
