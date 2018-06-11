package com.weborder;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Order {
	
	protected static int a;
	
	protected static String getRandomName() {
		String allLeters = "abcdefghijklmnopqrstuvwxyz";
		StringBuilder random = new StringBuilder();
		Random rnd = new Random();
		while (random.length() < 10) { // length of the random string.
			int index = (int) (rnd.nextFloat() * allLeters.length());

			random.append(allLeters.charAt(index));
		}
		String randomString = random.toString();
		return randomString;

	}


	protected static long getRandomZip() {

		return (int) ((Math.random() * 9 + 1) * 10000);

	}
	
	protected static String getRandomCard() {
		Random rd = new Random();
		String[] CardType = { "ctl00_MainContent_fmwOrder_cardList_0", "ctl00_MainContent_fmwOrder_cardList_1",
		"ctl00_MainContent_fmwOrder_cardList_2" };
		a = rd.nextInt(3);
		return CardType[a];
		
	}
	
//	public static int randNum(int a, int b) {
//		Random rd = new Random();
//		return rd.nextInt(b + 1 - a) + a;
//	}
	
	public static String getCartNumber() {
		Random rd = new Random();
		String cart = "";
		switch (a) {
		case 0:
			cart += 4;
			break;
		case 1:
			cart += 5;
			break;
		case 2:
			cart += 3;
			break;
		default:
			break;
		}
		if (a == 0 || a == 1) {
			for (int i = 0; i < 15; i++) {
				cart += rd.nextInt(10);
			}
		} else {
			for (int i = 0; i < 14; i++) {
				cart += rd.nextInt(10);
			}
		}

		return cart;
	}



	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver",
				"/Users/viorica/Documents/selenium dependencies/drivers/chromedriver");

		WebDriver driver = new ChromeDriver();

		driver.navigate().to("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");

		driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester");
		driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test");
		driver.findElement(By.name("ctl00$MainContent$login_button")).click();
		driver.findElement(By.cssSelector("a[href='Process.aspx']")).click();

		Random r1 = new Random();
		int num1 = r1.nextInt(100) + 1;
		String rundNum1 = "" + num1;

		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity")).sendKeys(Keys.BACK_SPACE +rundNum1);
		// ctl00$MainContent$fmwOrder$txtName
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName"))
				.sendKeys("John " + getRandomName() + " Smith");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox2")).sendKeys("7925 Jones Branch Dr");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3")).sendKeys("McLean");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3")).sendKeys("VA");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5")).sendKeys("" + getRandomZip());
		driver.findElement(By.id(getRandomCard())).click();
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(getCartNumber() );
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1")).sendKeys("08/22");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();
		
		String expected = "New order has been successfully added.";
		String actual = driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder\"]/tbody/tr/td/div/strong")).getText();

		if ( actual.contains(expected) ) {
			System.out.println("pass");
		} else {
			System.out.println("fail");
			System.out.println("Expected:\t" + expected);
			System.out.println("Actual:\t" + actual);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
