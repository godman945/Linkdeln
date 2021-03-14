import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.enu.LinkdelnDetailEnum;

@Component
public class SeleniumTest {

	public  void process() {
		//System.setProperty("webdriver.chrome.driver","‪chromedriver.txt");
		
		
//		System.setProperty("webdriver.chrome.driver","C:\\Users\\User\\git\\Linkdeln\\chromedriver.exe");
////		System.setProperty("webdriver.chrome.driver","‪chromedriver.exe");
//		ChromeOptions chromeOptions = new ChromeOptions();
//		chromeOptions.addArguments("headless");
//		chromeOptions.addArguments("--disable-gpu");
//		chromeOptions.addArguments("--hide-scrollbars");
//		chromeOptions.addArguments("window-size=1920x3000");
//		WebDriver driver= new ChromeDriver(chromeOptions);
//		
		File targetFile = new File("C:\\Users\\User\\git\\Linkdeln\\chromedriver2.exe");
		
		System.setProperty("webdriver.chrome.driver", targetFile.getPath());
		
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("headless");
		chromeOptions.addArguments("--disable-gpu");
		chromeOptions.addArguments("--hide-scrollbars");
		chromeOptions.addArguments("window-size=1920x3000");
		 
		
		WebDriver webDriver = null;
		JSONObject result = new JSONObject();
		try {
			webDriver = new ChromeDriver(chromeOptions);
			
			webDriver.get("https://www.linkedin.com/login/zh-tw?fromSignIn=true&trk=guest_homepage-basic_nav-header-signin");
			// 定位帳號
			WebElement username = webDriver.findElement(By.id("username"));
			username.sendKeys("dfimarcom@dfi.com.tw");
			// 定位密碼
			WebElement password = webDriver.findElement(By.id("password"));// 定位密码输入框
			password.sendKeys("Dfi-1981");
			// 定位送出
			WebElement loginbtn = webDriver.findElement(By.xpath("//button[@type='submit']"));
			loginbtn.click();
			
			
//			webDriver.get("https://www.linkedin.com/company/pchome-online/about"); 
			
			
			
			
			
			
			
			
			
			
			result.put("errorMsg", "");
			result.put("returnType", "");
			result.put("result", "");
			
			JSONObject linkedinData = new JSONObject();
			linkedinData.put("website", "");
			linkedinData.put("phone", "");
			linkedinData.put("industry", "");
			linkedinData.put("companySize", "");
			linkedinData.put("headquarters", "");
			linkedinData.put("type", "");
			linkedinData.put("founded", "");
			linkedinData.put("specialties", "");
			linkedinData.put("linkedinurl", "");
			linkedinData.put("picName", "");
			
			String company="dfi-inc";
			
			String url = "https://www.linkedin.com/company/" + company + "/about";
			linkedinData.put("linkedinurl", url);
			
			webDriver.get(url);
			String currentUrl = webDriver.getCurrentUrl();
			if(webDriver.getCurrentUrl().contains("unavailable")) {
				result.put("returnType", "success");
				result.put("result", linkedinData);
				System.out.println(result.toString());
			}else {
				
//				System.out.println(webDriver.findElement(By.className("overflow-hidden")).getText());
				
//				WebElement div = webDriver.findElement(By.className("org-grid__core-rail--no-margin-left"));
//				WebElement dl = div.findElement(By.tagName("dl"));
				
				WebElement dl = webDriver.findElement(By.className("overflow-hidden"));
				
//				WebElement div = webDriver.findElement(By.id("ember82"));
//				WebElement dl = div.findElement(By.tagName("dl"));
				String infoStr = dl.getText();
				String[] arra = infoStr.split("\n");
				String title = "";
				for (String string : arra) {
					String content = string;
					System.out.println(content);
					
					
					if(content.equals(LinkdelnDetailEnum.WEBSITE.getTitle()) || content.equals(LinkdelnDetailEnum.WEBSITE.getEnitle())) {
						title = "website";
						continue;
					}
					if(content.equals(LinkdelnDetailEnum.PHONE.getTitle()) || content.equals(LinkdelnDetailEnum.PHONE.getEnitle())) {
						title = "phone";
						continue;
					}
					if(content.equals(LinkdelnDetailEnum.INDUSTRY.getTitle()) || content.equals(LinkdelnDetailEnum.INDUSTRY.getEnitle())) {
						title = "industry";
						continue;
					}
					if(content.equals(LinkdelnDetailEnum.SCALE.getTitle()) || content.equals(LinkdelnDetailEnum.SCALE.getEnitle())) {
						title = "companySize";
						continue;
					}
					if(content.equals(LinkdelnDetailEnum.HEADQUARTERS.getTitle()) || content.equals(LinkdelnDetailEnum.HEADQUARTERS.getEnitle())) {
						title = "headquarters";
						continue;
					}
					if(content.equals(LinkdelnDetailEnum.TYPE.getTitle()) || content.equals(LinkdelnDetailEnum.TYPE.getEnitle())) {
						title = "type";
						continue;
					}
					if(content.equals(LinkdelnDetailEnum.SINCE.getTitle()) || content.equals(LinkdelnDetailEnum.SINCE.getEnitle())) {
						title = "founded";
						continue;
					}
					if(content.equals(LinkdelnDetailEnum.FIELD.getTitle()) || content.equals(LinkdelnDetailEnum.FIELD.getEnitle())) {
						title = "specialties";
						continue;
					}
					if(StringUtils.isBlank(title)) {
						continue;
					}
					String oldContent = linkedinData.getString(title);
					oldContent = oldContent + " "+ content;
					
					if(oldContent.substring(0,1).equals(" ")) {
						oldContent = oldContent.trim();
					}
					linkedinData.put(title, oldContent);
				}
				
				
				
				result.put("result", linkedinData);	
				result.put("returnType", "success");
			
			}
				webDriver.quit();
			
				System.out.println(result);
			
		} catch (Exception e) {
			e.printStackTrace();
			webDriver.quit();
		} finally {
//			driver.quit();
		}

	}

	
	
	
	
	
	public static void main(String[] args) {
		try {
//			File b = new File("C:\\Users\\User\\git\\Linkdeln\\target\\Linkedin.jar");
//			System.out.println(b);
//			System.out.println(b.toString().replaceAll("\\\\", "/"));
//			
//			
//			JSONObject j = new JSONObject();
//			j.put("alex",b.toString());
//			System.out.println(j);
			
//			System.out.println(a.replaceAll("(*\\*)", "\\"));
			ApplicationContext ctx = new SpringApplicationBuilder(SeleniumTest.class).web(WebApplicationType.NONE).run(args);
			SeleniumTest seleniumTest = ctx.getBean(SeleniumTest.class);
			seleniumTest.process();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
