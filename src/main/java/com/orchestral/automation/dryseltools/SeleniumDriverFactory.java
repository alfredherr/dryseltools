/*
 * Copyright (c) Orchestral Developments Ltd and the Orion Health group of companies (2001 - 2017).
 * Author: Kuldeep Sinh Chauhan (@KuldeepSinhC)
 * emails: kuldeeps@orionhealth.com, chauhan.kuldeep.sinh@gmail.com
 *
 * This file is provided to you under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an  "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,  either express or implied.  See the License for the specific language governing    
 * permissions and limitations under the License.
 */
package com.orchestral.automation.dryseltools;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumDriverFactory {
	// set initial values to null (explicitly)
	private static WebDriver webDriver;

	public static WebDriver getWebDriver() {
		if (webDriver == null) {
			initializeWebDriver();
		}
		return webDriver;
	}

	public static void setWebDriver(final WebDriver webDriver) {
		SeleniumDriverFactory.webDriver = webDriver;
	}

	private static void initializeWebDriver() {
			System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver");
			SeleniumDriverFactory.setWebDriver(new FirefoxDriver());
			SeleniumDriverFactory.getWebDriver().manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
	}

	public static synchronized void tearDown() {
		try {
			// check if the web-driver is already closed/quit
			if (webDriver != null && webDriver.getTitle() != null) {
				SeleniumDriverFactory.webDriver.quit();
				// this is required to cleanup the instance.
				SeleniumDriverFactory.webDriver = null;
			}
		} catch (final Exception ex) {

		}
	}
}
