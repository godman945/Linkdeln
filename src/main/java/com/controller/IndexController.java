package com.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enu.LinkdelnDetailEnum;

@Controller
public class IndexController {

	
	@Autowired
    private ResourceLoader resourceLoader;
	
	@Autowired
	private WebDriver webDriver;
	
	
	@RequestMapping("/linkedinInfo")
	@ResponseBody
	public String linkdelnInfo(Model model,@RequestParam(required=false) String company,@RequestParam(required=false) String picPath) {
		JSONObject result = new JSONObject();
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
		
		if(StringUtils.isBlank(company)) {
			result.put("errorMsg", "company can't null");
			result.put("returnType", "fail");
			result.put("result", new JSONObject());
			return result.toString();
		}
		if(StringUtils.isBlank(picPath)) {
			result.put("errorMsg", "picPath can't null");
			result.put("returnType", "fail");
			result.put("result", new JSONObject());
			return result.toString();
		}
		
		try {
			String url = "https://www.linkedin.com/company/" + company + "/about";
			linkedinData.put("linkedinurl", url);
			
			webDriver.get(url);
			String currentUrl = webDriver.getCurrentUrl();
			if(webDriver.getCurrentUrl().contains("unavailable")) {
				result.put("returnType", "success");
				result.put("result", linkedinData);
				return result.toString();
			}else {
				
				
				WebElement div = webDriver.findElement(By.className("org-grid__core-rail--no-margin-left"));
				WebElement dl = div.findElement(By.tagName("dl"));
				
//				WebElement dl = webDriver.findElement(By.className("overflow-hidden"));
				
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
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String picName = picPath+"\\"+sdf.format(new Date())+".png";
				File f = new File(picName);
				linkedinData.put("picName", picName.replaceAll("\\\\", "/"));
				
				
				result.put("result", linkedinData);	
				result.put("returnType", "success");

				// 截圖
				WebElement imgArea = div.findElement(By.className("artdeco-card"));
				int eleWidth = imgArea.getSize().getWidth();
				int eleHeight = imgArea.getSize().getHeight()+500;
				Point point = imgArea.getLocation();
				File screenshot = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
				BufferedImage  fullImg = ImageIO.read(screenshot);
				BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),eleWidth, eleHeight);
				ImageIO.write(eleScreenshot, "png", screenshot);
				
				FileUtils.copyFile(screenshot, new File(picName));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result.toString();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@RequestMapping("/linkdelnInfo")
//	@ResponseBody
//	public String linkdelnInfo(Model model,@RequestParam(required=false) String company) {
//		System.out.println(company);
//
//		JSONObject resultData = new JSONObject();
//		for (LinkdelnDetailEnum linkdelnDetailEnum : LinkdelnDetailEnum.values()) {
//			resultData.put(linkdelnDetailEnum.getTitle(), "");
//		}
//		
//		try {
////			Resource resource = resourceLoader.getResource("classpath:chromedriver.exe");
////			InputStream input = resource.getInputStream();
////			File chromedriverFile = resource.getFile();
////			System.setProperty("webdriver.chrome.driver", chromedriverFile.getPath());
////
////			ChromeOptions chromeOptions = new ChromeOptions();
////			chromeOptions.addArguments("headless");
////			chromeOptions.addArguments("--disable-gpu");
////			chromeOptions.addArguments("--hide-scrollbars");
////			chromeOptions.addArguments("window-size=1920x3000");
////			WebDriver webDriver = new ChromeDriver(chromeOptions);
//
////			webDriver.get("https://www.linkedin.com/login/zh-tw?fromSignIn=true&trk=guest_homepage-basic_nav-header-signin");
////
////			// 定位帳號
////			WebElement username = webDriver.findElement(By.id("username"));
////			username.sendKeys("godman945@gmail.com");
////			// 定位密碼
////			WebElement password = webDriver.findElement(By.id("password"));// 定位密码输入框
////			password.sendKeys("pca03711");
////			// 定位送出
////			WebElement loginbtn = webDriver.findElement(By.xpath("//button[@type='submit']"));
////			loginbtn.click();
//
//			// Thread.sleep(1500);
//
////		        driver.get(company);
//
//			webDriver.get("https://www.linkedin.com/company/" + company + "/about");
////		        driver.get("https://www.linkedin.com/company/pchome-online/about"); 
//
//			String currentUrl = webDriver.getCurrentUrl();
//		
//			
//			if(webDriver.getCurrentUrl().contains("unavailable")) {
//				resultData.put("return_type", "fail");
//				return resultData.toString();
//			}else {
//				WebElement div = webDriver.findElement(By.className("org-grid__core-rail--no-margin-left"));
//				WebElement dl = div.findElement(By.tagName("dl"));
//				String infoStr = dl.getText();
//				String[] arra = infoStr.split("\n");
//				String title = "";
//				for (String string : arra) {
//					String content = string;
//					for (LinkdelnDetailEnum linkdelnDetailEnum : LinkdelnDetailEnum.values()) {
//						if (string.contentEquals(linkdelnDetailEnum.getTitle())) {
//							title = linkdelnDetailEnum.getTitle();
//							resultData.put(title, "");
//							break;
//						}
//					}
//
//					if (content.equals(title)) {
//						continue;
//					} else {
//						String oldContent = resultData.getString(title);
//						oldContent = oldContent + " " + content;
//						resultData.put(title, oldContent);
//					}
//				}
//				resultData.put("return_type", "success");
//				System.out.println(resultData);
//
//				// 截圖
//				WebElement imgArea = div.findElement(By.className("org-locations-module__map-container--small display-flex relative"));
//				Point point = imgArea.getLocation();
//
//				int eleWidth = imgArea.getSize().getWidth();
//				int eleHeight = imgArea.getSize().getHeight();
//				File screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
//				BufferedImage fullImg = ImageIO.read(screenshot);
//				BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
//				ImageIO.write(eleScreenshot, "png", screenshot);
//				FileUtils.copyFile(screenshot, new File("D:\\test\\screen.png"));
//			}
//			
//			
//			
//
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			
//		}
//		
//		return resultData.toString();
//		
//	}
}
