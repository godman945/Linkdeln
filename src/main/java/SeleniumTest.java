import java.io.File;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumTest {

	public static void main(String[] args) {
		System.out.println("START");
		//System.setProperty("webdriver.chrome.driver","C:\\Users\\User\\Desktop\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver","C:\\Users\\User\\Desktop\\70.0.3538.16\\chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("headless");
		chromeOptions.addArguments("--disable-gpu");
		chromeOptions.addArguments("--hide-scrollbars");
		chromeOptions.addArguments("window-size=1920x3000");
		WebDriver driver= new ChromeDriver(chromeOptions);
		
		
		 
		try {
	       
	        driver.get("https://www.linkedin.com/login/zh-tw?fromSignIn=true&trk=guest_homepage-basic_nav-header-signin");

	        //定位帳號
	        WebElement username = driver.findElement(By.id("username"));
	        username.sendKeys("godman945@gmail.com");
	        //定位密碼
	        WebElement password = driver.findElement(By.id("password"));//定位密码输入框
	        password.sendKeys("pca03711");
	        //定位送出
	        WebElement loginbtn = driver.findElement(By.xpath("//button[@type='submit']"));
	        loginbtn.click();
	        
	       //Thread.sleep(1500);
	        
	        driver.get("https://www.linkedin.com/company/pchome-online/about"); 
	        
	        WebElement div = driver.findElement(By.className("org-grid__core-rail--no-margin-left"));
	      
	        WebElement p = div.findElement(By.tagName("p"));
	        //總覽
	        String outline = p.getText();
	        

	        System.out.println("大綱************************START");
	        System.out.println(outline);
	        System.out.println("大綱************************END");
	        
	        WebElement dl = div.findElement(By.tagName("dl"));
	        System.out.println(dl);
	        System.out.println(dl.getSize());
	        
	        System.out.println(dl.getText());
	        
	        String [] arra = dl.getText().split("\n");
	        System.out.println(arra.length);
	        
	        JSONObject json = new JSONObject();
	        String title = "";
	        for (String string : arra) {
	        	String content = string;
	        	for (LinkdelnDetailEnum linkdelnDetailEnum : LinkdelnDetailEnum.values()) {
					if(string.contentEquals(linkdelnDetailEnum.getTitle())) {
						title = linkdelnDetailEnum.getTitle();
						json.put(title, "");
						break;
					}
				}
	        	
	        	if(content.equals(title)) {
	        		continue;
	        	}else {
	        		String oldContent = json.getString(title);
	        		oldContent = oldContent + " " +content;
	        		json.put(title, oldContent);
	        	}
			}
	        System.out.println(json);
	        
	        
	        //截圖
	        
	       // Point point = dl.getLocation();

		  
		  //  int eleWidth = dl.getSize().getWidth();
		  //  int eleHeight = dl.getSize().getHeight();
		   // File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  //  BufferedImage  fullImg = ImageIO.read(screenshot);
		  //  BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),eleWidth, eleHeight);
		  //  ImageIO.write(eleScreenshot, "png", screenshot);
		    
		  //  FileUtils.copyFile(screenshot, new File("D:\\test\\screen.png"));
		    
		    
		    
		    
	        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        FileUtils.copyFile(src, new File("D:\\test\\screen.png"));
	        
	     //     Thread.sleep(2000);
	        driver.quit();
	   
		} catch (Exception e) {
			e.printStackTrace();
			driver.quit();
		} finally {
			driver.quit();
		}

	}

}
