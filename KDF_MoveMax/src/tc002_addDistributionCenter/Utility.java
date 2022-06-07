package tc002_addDistributionCenter;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {
	private WebDriver driver = null;

	public void browser_open(String fURL) {
		if (driver == null) {
			System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} else {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		driver.get(fURL);
		
	}

	public void login() {
		driver.findElement(By.name("username")).sendKeys("itsci");
		driver.findElement(By.name("password")).sendKeys("itsci");
		driver.findElement(By.xpath("//button[contains(text(),'LOGIN')]")).click();
//		try {
//			driver.findElement(By.xpath("//button[contains(text(),'LOGIN')]")).click();
//		} catch (Exception e) {
//		}
	}

	public void radio_select(String xPath, String fText) {
		List<WebElement> radio = driver.findElements(By.xpath(xPath));
		for (int i = 0; i < radio.size(); i++) {
			if (radio.get(i).getAttribute("value").equals(fText)) {
				radio.get(i).click();
				return;
			}
		}
		throw new NoSuchElementException("Invalid Radio button Selected");
	}

	public void edit_input(String xPath, String fText) {
		driver.findElement(By.xpath(xPath)).clear();
		driver.findElement(By.xpath(xPath)).sendKeys(fText);
	}

	public void file_input(String xPath, String fText) {
		driver.findElement(By.xpath(xPath)).clear();
		driver.findElement(By.xpath(xPath)).sendKeys("" + fText);
	}

	public String get_text(String xPath1, String xPath2) {
		String acResult = "";
		try {
			acResult = driver.findElement(By.xpath(xPath1)).getText();
		} catch (Exception e) {
			acResult = "";
		}
		return acResult;
	}

	public void list_select(String xPath, String fText) {
//		Select select = new Select(driver.findElement(By.xpath(xPath)));
//		List<WebElement> options = select.getOptions();
//		for (int i = 0; i < options.size(); i++) {
//			if (options.get(i).getText().equals(fText)) {
//				if (!options.get(i).isSelected())
//					options.get(i).click();
//				return;
//			}
//		}
		try {
			driver.findElement(By.xpath(xPath)).click();
			driver.findElement(By.xpath("//li[contains(text(),'" + fText + "')]")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath(xPath)).click();
		}
		throw new NoSuchElementException("Invalid list selected");
	}

	public void checkbox_set(String xPath, String fText) {
		String[] extract = fText.split(",");
		if (extract.length == 4) {
			List<WebElement> checkBox = driver.findElements(By.xpath(xPath));
			for (int i = 0; i < extract.length; i++) {
				if (extract[i].equalsIgnoreCase("ON")) {
					checkBox.get(i).click();
				}
			}
		} else {
			throw new NoSuchElementException("Invalid CheckBox Selected");
		}
	}

	public void button_click(String xPath) {
		try {
			driver.findElement(By.xpath(xPath)).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void date_picker(String xPath, String date) {
		int i = Integer.parseInt(date);
		System.out.print(i);
		String xPaths = xPath + "" + (i + 1) + "]";
		System.out.print(xPaths);
		driver.findElement(By.xpath(xPaths)).click();
	}

	public void link_click(String fText) {
		driver.findElement(By.linkText(fText)).click();
	}

	public boolean dialog_click(String fText, int k, String resultPath) {
		String text = "";
		Driver.Alert2 = "No Error";
		Driver.Alert1 = "";
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			text = alert.getText();

			Thread.sleep(100);
			Driver.Alert1 = alert.getText();
			System.out.println(Driver.Alert1);

			if (text.equals(fText)) {
				alert.accept();
				return true;
			} else {
				Driver.Alert2 = "Mismatch alert";
				saveScreen(k);
				alert.accept();
				return false;
			}
		} catch (Exception e) {
			if (Driver.Alert1.equals("")) {
//				Driver.Alert1 = driver.findElement(By.xpath(resultPath)).getText();
				Driver.Alert1 = driver.findElement(By.xpath(resultPath)).getText().replaceAll("×\n", "");
				if (Driver.Alert1.equals(fText)) {
					return true;
				} else {
					Driver.Alert2 = "Mismatch alert";
					return false;
				}
			} else {
				if (text.equals(fText)) {
					Driver.Alert2 = "No alert";
					return true;
				} else {
					Driver.Alert2 = "Mismatch alert";
					saveScreen(k);
					return false;
				}
			}
		}
	}

	public void browser_close() {
		driver.close();
	}

	public void saveScreen(int index) {
		BufferedImage image = null;
		try {
			image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		try {
			ImageIO.write(image, "png", new File(
					"D:/eclipse-workspace-tester/KDF_MoveMax/Excel/Screencaps/TC002" + "_" + (index + 1) + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void maximizewin() {
		driver.manage().window().maximize();
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} // try
		catch (NoAlertPresentException Ex) {
			return false;
		} // catch
	}
}
