package com.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class BaseClass {

	public static WebDriver driver;
	public static Actions act;
	public static Robot robo;
	public static JavascriptExecutor js;
	public static TakesScreenshot ss;
	public static Select se;
	public static RequestSpecification requestSpecification;

	public static void chromeBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public static void edgeBrowser() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public static void fireFoxBrowser() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public static void urlLaunch(String url) {
		driver.get(url);
	}

	public static void clickElement(WebElement element) {
		element.click();
	}

	public static WebElement locators(String locator, String value) {
		if (locator.equalsIgnoreCase("id")) {
			WebElement findElement = driver.findElement(By.id(value));
			return findElement;
		} else if (locator.equalsIgnoreCase("name")) {
			WebElement findElement = driver.findElement(By.name(value));
			return findElement;
		} else if (locator.equalsIgnoreCase("xpath")) {
			WebElement findElement = driver.findElement(By.xpath(value));
			return findElement;
		} else if (locator.equalsIgnoreCase("cssSelector")) {
			WebElement findElement = driver.findElement(By.cssSelector(value));
			return findElement;
		} else {
			System.out.println("None");
			return null;
		}
	}

	public static void sendText(WebElement element, String keysToSend) {
		element.sendKeys(keysToSend);
	}

	public static void clearElement(WebElement element) {
		element.clear();
	}

	public static void navigateTo(String url) {
		driver.navigate().to(url);
	}

	public static void navigateBack() {
		driver.navigate().back();
	}

	public static void navigateForward() {
		driver.navigate().forward();
	}

	public static void refresh() {
		driver.navigate().refresh();
	}

	public static void deleteCookies() {
		driver.manage().deleteAllCookies();
	}

	public static void mouseClick(WebElement element) {
		act = new Actions(driver);
		act.click(element).perform();
	}

	public static void RightClick(WebElement element) {
		act = new Actions(driver);
		act.contextClick(element).perform();
	}

	public static void DoubleClick(WebElement element) {
		act = new Actions(driver);
		act.doubleClick(element).perform();
	}

	public static void moveToElement(WebElement element) {
		act = new Actions(driver);
		act.moveToElement(element).perform();
	}

	public static void dragAndDrop(WebElement element, WebElement element2) {
		act = new Actions(driver);
		act.dragAndDrop(element, element2);
	}

	public static void keyboardEnter() throws AWTException {
		robo = new Robot();
		robo.keyPress(KeyEvent.VK_ENTER);
	}

	public static void keyboardDown() throws AWTException {
		robo = new Robot();
		robo.keyPress(KeyEvent.VK_DOWN);
	}

	public static void keyboardUp() throws AWTException {
		robo = new Robot();
		robo.keyPress(KeyEvent.VK_UP);
	}

	public static void scrollDownPixel() {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
	}

	public static void scrollUpPixel() {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-1000)");
	}

	public static void scrollDownElement(WebElement element) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
	}

	public static void scrollUpElement(WebElement element) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(false)", element);
	}

	public static void takeScreenShot() throws IOException {
		File file = new File("C:\\Users\\DELL\\OneDrive\\Documents\\CLASS\\ADVANCED FRAMEWORK\\ss.png");

		ss = (TakesScreenshot) driver;

		File screenshotAs = ss.getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(screenshotAs, file);
	}

	public static String getExcelData(int rownum, int cellnum) throws IOException {

		try {

			File file = new File("C:\\Users\\DELL\\OneDrive\\Documents\\CLASS\\ADVANCED FRAMEWORK\\EXCEL.xlsx");

			FileInputStream in = new FileInputStream(file);

			Workbook book = new XSSFWorkbook(in);

			Sheet sheet = book.getSheet("Sheet1");

			Row row = sheet.getRow(rownum);

			Cell cell = row.getCell(cellnum);

			CellType cellType = cell.getCellType();
			String value = "";
			switch (cellType) {
			case STRING:
				value = cell.getStringCellValue();
				break;

			default:
				if (DateUtil.isCellDateFormatted(cell)) {
					Date dateCellValue = cell.getDateCellValue();
					SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
					value = simple.format(dateCellValue);
				} else {
					double numericCellValue = cell.getNumericCellValue();
					long lo = (long) numericCellValue;
					BigDecimal valueOf = BigDecimal.valueOf(lo);
					value = valueOf.toString();
				}
				break;
			}
			return value;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public static void selectValue(WebElement element,String value) {
		se = new Select(element);
		se.selectByValue(value);
	}
	
	public static void selectIndex(WebElement element,int index) {
		se = new Select(element);
		se.selectByIndex(index);
	}
	
	public static void selectVisibleText(WebElement element,String text) {
		se = new Select(element);
		se.selectByVisibleText(text);
	}
	
	public static void deSelectAll(WebElement element,String value) {
		se = new Select(element);
		se.deselectAll();
	}
	
	public static void exit() {
		driver.quit();
	}
	
	public static void sleep() throws InterruptedException {
		Thread.sleep(3000);
	}
	
	//REST ASSURED API
	public static void requestObject(String baseuri) {
		RestAssured.baseURI = baseuri;
		requestSpecification = RestAssured.given();
	}
	
	public static void queryParameter(String parameterName, String parameterValue) {
		requestSpecification = requestSpecification.queryParam(parameterName, parameterValue);
	}
	
	public static void pathParameter(String parameterName, String parameterValue) {
		requestSpecification = requestSpecification.pathParam(parameterName, parameterValue);
	}

	public static Response responseObject(String requestType, String resource) {
		switch (requestType) {
		case "GET":{
			return requestSpecification.request(Method.GET, resource);
		}
		case "POST":{
			return requestSpecification.request(Method.POST,resource);
		}
		case "PUT":{
			return requestSpecification.request(Method.PUT,resource);
		}
		default :{
			return requestSpecification.request(Method.DELETE, resource);
		}	
		}
	}
	
	public static int responseCode(Response response) {
		int statusCode = response.getStatusCode();
		return statusCode;
	}
	
	public static void responseBody(Response response) {
		String asPrettyString = response.getBody().asPrettyString();
		System.out.println(asPrettyString);
	}
	
	public static void validationOfCode(int expected, Response response) {
		Assert.assertEquals(expected, response.getStatusCode());
		System.out.println("Status code validation done");
	}
	
	public static void validationOfBody(Response response, String jsonpath, String expected) {
		Object obj = response.jsonPath().get(jsonpath);
		String str = (String)obj;
		Assert.assertEquals(expected, str);
		System.out.println("status body validation done");
	}
}
