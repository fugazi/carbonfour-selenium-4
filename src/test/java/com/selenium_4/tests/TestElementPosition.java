package com.selenium_4.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class TestElementPosition {

    public WebDriver driver;

    /**
     * Initialize the WebDriverManager and EdgeDriver.
     * Go to the website under Test and maximize the browser window.
     */
    @BeforeEach
    public void setUp() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.opencart.com");
    }

    /**
     * Close the browser window.
     */
    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    /**
     * Get Element Position of an Image using built-in method 'getRect' Selenium 4.0.
     * getRect() for element size & position to fetch the x-axis position, same for y-axis, height & width of an element.
     */
    @Test
    void getElementPosition() {
        WebElement logoOpenCart = driver.findElement(By.id("logo"));
        Rectangle logoOpenCartRect = logoOpenCart.getRect();
        assertSoftly(softly -> {
            softly.assertThat(logoOpenCartRect.x).isEqualTo(303);
            softly.assertThat(logoOpenCartRect.y).isEqualTo(64);
            softly.assertThat(logoOpenCartRect.width).isEqualTo(416);
            softly.assertThat(logoOpenCartRect.height).isEqualTo(39);
        });
        System.out.println("X: " + logoOpenCartRect.x + " Y: " + logoOpenCartRect.y + " Width: " + logoOpenCartRect.width + " Height: " + logoOpenCartRect.height);
    }

    /**
     * Get Element Position of a Row-list using built-in method 'getRect' Selenium 4.0.
     * getRect() for element size & position to fetch the width & height of an element.
     */
    @Test
    void getElementPositionToRow() {
        WebElement rowFeaturedList = driver.findElement(By.className("row"));
        Rectangle rowFeaturedListRect = rowFeaturedList.getRect();
        assertSoftly(softly -> {
            softly.assertThat(rowFeaturedListRect.width).isEqualTo(1320);
            softly.assertThat(rowFeaturedListRect.height).isEqualTo(56);
        });
        System.out.println("Width: " + rowFeaturedListRect.getWidth() + " Height: " + rowFeaturedListRect.getHeight());
    }
}
