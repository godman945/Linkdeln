// 
// Decompiled by Procyon v0.5.36
// 

package com.controller;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.awt.image.BufferedImage;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.apache.commons.io.FileUtils;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.enu.LinkdelnDetailEnum;
import org.openqa.selenium.By;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;

@Controller
public class IndexController
{
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private WebDriver webDriver;
    
    @RequestMapping({ "/linkedinInfo" })
    @ResponseBody
    public String linkdelnInfo(final Model model, @RequestParam(required = false) final String company, @RequestParam(required = false) final String picPath) {
        final JSONObject result = new JSONObject();
        result.put("errorMsg", "");
        result.put("returnType", "");
        result.put("result", "");
        final JSONObject linkedinData = new JSONObject();
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
        if (StringUtils.isBlank((CharSequence)company)) {
            result.put("errorMsg", "company can't null");
            result.put("returnType", "fail");
            result.put("result", new JSONObject());
            return result.toString();
        }
        if (StringUtils.isBlank((CharSequence)picPath)) {
            result.put("errorMsg", "picPath can't null");
            result.put("returnType", "fail");
            result.put("result", new JSONObject());
            return result.toString();
        }
        try {
            final String url = "https://www.linkedin.com/company/" + company + "/about";
            linkedinData.put("linkedinurl", url);
            this.webDriver.get(url);
            final String currentUrl = this.webDriver.getCurrentUrl();
            if (this.webDriver.getCurrentUrl().contains("unavailable")) {
                result.put("returnType", "success");
                result.put("result", linkedinData);
                return result.toString();
            }
            final WebElement div = this.webDriver.findElement(By.className("org-grid__core-rail--no-margin-left"));
//            WebElement div = webDriver.findElement(By.className("overflow-hidden"));
            
            WebElement dl = webDriver.findElement(By.className("overflow-hidden"));
            
//            final WebElement dl = div.findElement(By.tagName("dl"));
            final String infoStr = dl.getText();
            final String[] arra = infoStr.split("\n");
            String title = "";
            String[] array;
            for (int length = (array = arra).length, i = 0; i < length; ++i) {
                final String content;
                final String string = content = array[i];
                if (content.equals(LinkdelnDetailEnum.WEBSITE.getTitle()) || content.equals(LinkdelnDetailEnum.WEBSITE.getEnitle())) {
                    title = "website";
                }
                else if (content.equals(LinkdelnDetailEnum.PHONE.getTitle()) || content.equals(LinkdelnDetailEnum.PHONE.getEnitle())) {
                    title = "phone";
                }
                else if (content.equals(LinkdelnDetailEnum.INDUSTRY.getTitle()) || content.equals(LinkdelnDetailEnum.INDUSTRY.getEnitle())) {
                    title = "industry";
                }
                else if (content.equals(LinkdelnDetailEnum.SCALE.getTitle()) || content.equals(LinkdelnDetailEnum.SCALE.getEnitle())) {
                    title = "companySize";
                }
                else if (content.equals(LinkdelnDetailEnum.HEADQUARTERS.getTitle()) || content.equals(LinkdelnDetailEnum.HEADQUARTERS.getEnitle())) {
                    title = "headquarters";
                }
                else if (content.equals(LinkdelnDetailEnum.TYPE.getTitle()) || content.equals(LinkdelnDetailEnum.TYPE.getEnitle())) {
                    title = "type";
                }
                else if (content.equals(LinkdelnDetailEnum.SINCE.getTitle()) || content.equals(LinkdelnDetailEnum.SINCE.getEnitle())) {
                    title = "founded";
                }
                else if (content.equals(LinkdelnDetailEnum.FIELD.getTitle()) || content.equals(LinkdelnDetailEnum.FIELD.getEnitle())) {
                    title = "specialties";
                }
                else {
                    String oldContent = linkedinData.getString(title);
                    oldContent = String.valueOf(oldContent) + " " + content;
                    if (oldContent.substring(0, 1).equals(" ")) {
                        oldContent = oldContent.trim();
                    }
                    linkedinData.put(title, oldContent);
                }
            }
            final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            final String picName = String.valueOf(picPath) + "\\" + sdf.format(new Date()) + ".png";
            final File f = new File(picName);
            linkedinData.put("picName", picName.replaceAll("\\\\", "/"));
            result.put("result", linkedinData);
            result.put("returnType", "success");
            final WebElement imgArea = div.findElement(By.className("artdeco-card"));
            final int eleWidth = imgArea.getSize().getWidth();
            final int eleHeight = imgArea.getSize().getHeight() + 500;
            final Point point = imgArea.getLocation();
            final File screenshot = (File)((TakesScreenshot)this.webDriver).getScreenshotAs(OutputType.FILE);
            final BufferedImage fullImg = ImageIO.read(screenshot);
            final BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
            ImageIO.write(eleScreenshot, "png", screenshot);
            FileUtils.copyFile(screenshot, new File(picName));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}