package com.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;


@Configuration
public class SeleniumUtil {

	
	@Autowired
    ResourceLoader resourceLoader;
	
	@Value("classpath:/chromedriver.exe")
	private Resource resource;
	
	@Bean("webDriver")
	public WebDriver webDriver(){
		WebDriver webDriver = null;
		try {
			 File chromefile = resource.getFile();
			 System.setProperty("webdriver.chrome.driver", chromefile.getPath());
			 ChromeOptions chromeOptions = new ChromeOptions();
			 chromeOptions.addArguments("headless");
			 chromeOptions.addArguments("--disable-gpu");
			 chromeOptions.addArguments("--hide-scrollbars");
			 chromeOptions.addArguments("window-size=1920x3000");
			 
			ApplicationHome applicationHome = new ApplicationHome(getClass());
			File file = applicationHome.getSource();
			String userPath = file.getParentFile().toString()+"\\account.properties";
			InputStream inStream = new FileInputStream(new File(userPath));
			
			Properties prop = new Properties();
			prop.load(inStream);
			String key = prop.getProperty("user").trim();
			System.out.println(">>>>>>STRT USER LINKEDIN ACCOUNT :" + key);
			String pw = prop.getProperty("pw").trim();
			
			webDriver = new ChromeDriver(chromeOptions);
			
			webDriver.get("https://www.linkedin.com/login/zh-tw?fromSignIn=true&trk=guest_homepage-basic_nav-header-signin");
			// 定位帳號
			WebElement username = webDriver.findElement(By.id("username"));
			username.sendKeys(key);
			// 定位密碼
			WebElement password = webDriver.findElement(By.id("password"));// 定位密码输入框
			password.sendKeys(pw);
			// 定位送出
			WebElement loginbtn = webDriver.findElement(By.xpath("//button[@type='submit']"));
			loginbtn.click();
		}catch(Exception e) {
			e.printStackTrace();
			webDriver.quit();
		}
		return webDriver;
	}
	
}
