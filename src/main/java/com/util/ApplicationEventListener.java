package com.util;

import org.openqa.selenium.WebDriver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

public class ApplicationEventListener implements ApplicationListener<ApplicationEvent>{

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if(event instanceof ContextClosedEvent) {
			ContextClosedEvent close = (ContextClosedEvent) event;
			ApplicationContext ctx = close.getApplicationContext();
			WebDriver webDriver = (WebDriver) ctx.getBean("webDriver");
			webDriver.quit();
		}
	}
}
