package org.example.conductor.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.example.persistencia.model.Conductor;
import org.example.persistencia.repository.ConductorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

@ActiveProfiles("system-test")
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ConductorControllerSystemTest{

    @Autowired
    private ConductorRepository conductorRepository;

    private String baseUrl = "http://localhost:4200";

    private String CELLS_XPATH = "//table[@id=\"tablaExtracto\"]/tbody/tr/td[3]";

    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;

    @BeforeEach
    void init() {
        Conductor conductor1 = new Conductor("Juan Pérez", "1234567890", "+123456789", "Calle Falsa 123");
        Conductor conductor2 = new Conductor("María López", "0987654321", "+987654321", "Avenida Siempreviva 742");
        Conductor conductor3 = new Conductor("Carlos García", "1122334455", "+1122334455", "Boulevard de los Sueños 1010");
        List<Conductor> conductores = Arrays.asList(conductor1, conductor2, conductor3);
        conductorRepository.saveAll(conductores);

        this.playwright = Playwright.create();
        this.browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        this.browserContext = browser.newContext();
        this.page = browserContext.newPage();
    
    }

    @AfterEach
    void end() {
        browser.close();
        playwright.close();
    }

    @Test
    void listarConductores() throws InterruptedException{
        page.navigate(baseUrl+"/list-conductor");
        Thread.sleep(1000);
        List<String> conductores = page.locator(CELLS_XPATH).all().stream().map(Locator::textContent).collect(Collectors.toList());
    }

}