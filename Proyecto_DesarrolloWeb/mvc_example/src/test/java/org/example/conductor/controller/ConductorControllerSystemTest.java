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
    void listarConductoresEnUI() throws InterruptedException {
    page.navigate(baseUrl + "/list-conductor"); // Navegar a la página de listados de conductores
    Thread.sleep(1000);  // Esperar a que la página cargue completamente

    // Obtener todos los conductores mostrados en la tabla (como lo haces en la prueba de la tabla)
    List<String> conductores = page.locator(CELLS_XPATH).all()
                                   .stream()
                                   .map(Locator::textContent)
                                   .collect(Collectors.toList());

    // Verificar que el número de conductores mostrados es correcto
    assertEquals(3, conductores.size(), "Debería haber 3 conductores listados");

    // Verificar que al menos uno de los conductores tiene el nombre esperado
    assert conductores.stream().anyMatch(c -> c.contains("Juan Pérez"));
    }

    @Test
    void crearConductorDesdeUI() throws InterruptedException {
        page.navigate(baseUrl + "/create-conductor");  // Navegar al formulario de creación de conductor
        Thread.sleep(1000);

        // Llenar el formulario
        page.fill("input[name='nombre']", "Luis Pérez");
        page.fill("input[name='cedula']", "3344556677");
        page.fill("input[name='telefono']", "+9988776655");
        page.fill("input[name='direccion']", "Avenida Central 100");

        // Enviar el formulario
        page.click("button[type='submit']");

        // Esperar la redirección o alguna confirmación
        Thread.sleep(1000);  // Esperar el tiempo necesario para que el sistema procese la solicitud

        // Verificar que el conductor se haya añadido a la lista
        page.navigate(baseUrl + "/list-conductor");
        List<String> conductores = page.locator(CELLS_XPATH).all()
                                    .stream()
                                    .map(Locator::textContent)
                                    .collect(Collectors.toList());

        assert conductores.stream().anyMatch(c -> c.contains("Luis Pérez"));
    }

    @Test
    void editarConductorDesdeUI() throws InterruptedException {
        page.navigate(baseUrl + "/edit-conductor/1");  // Navegar al formulario de edición del conductor con id 1
        Thread.sleep(1000);

        // Modificar los valores del conductor
        page.fill("input[name='nombre']", "Juan Pérez Actualizado");
        page.fill("input[name='cedula']", "1234567890");
        page.fill("input[name='telefono']", "+123456789");
        page.fill("input[name='direccion']", "Calle Falsa 456");

        // Enviar el formulario
        page.click("button[type='submit']");

        // Esperar la redirección o alguna confirmación
        Thread.sleep(1000);

        // Verificar que el conductor se haya actualizado correctamente en la lista
        page.navigate(baseUrl + "/list-conductor");
        List<String> conductores = page.locator(CELLS_XPATH).all()
                                    .stream()
                                    .map(Locator::textContent)
                                    .collect(Collectors.toList());

        assert conductores.stream().anyMatch(c -> c.contains("Juan Pérez Actualizado"));
    }

    @Test
    void eliminarConductorDesdeUI() throws InterruptedException {
        page.navigate(baseUrl + "/list-conductor");  // Navegar a la lista de conductores
        Thread.sleep(1000);

        // Buscar el conductor en la lista y hacer clic en el botón de eliminar
        page.click("button#eliminar-conductor-1");  // Suponiendo que el id de eliminar es único para cada conductor

        // Confirmar eliminación (si hay una ventana emergente de confirmación)
        page.click("button.confirmar-eliminacion");

        // Esperar que la lista se actualice
        Thread.sleep(1000);

        // Verificar que el conductor ya no aparece en la lista
        page.navigate(baseUrl + "/list-conductor");
        List<String> conductores = page.locator(CELLS_XPATH).all()
                                    .stream()
                                    .map(Locator::textContent)
                                    .collect(Collectors.toList());

        assertEquals(2, conductores.size(), "Después de la eliminación, deberían quedar 2 conductores");
    }

    @Test
    void loginUI() throws InterruptedException {
        page.navigate(baseUrl + "/login");  // Navegar al formulario de login
        Thread.sleep(1000);

        // Rellenar el formulario de login
        page.fill("input[name='email']", "alice@alice.com");
        page.fill("input[name='password']", "alice123");

        // Hacer clic en el botón de login
        page.click("button[type='submit']");

        // Esperar la redirección a la página principal
        Thread.sleep(1000);

        // Verificar que el nombre de usuario aparece después del login (confirmando que el login fue exitoso)
        String username = page.locator("span#username").textContent();
        assertEquals("Alice", username, "El usuario logueado debería ser Alice");
    }

    @Test
    void buscarConductorDesdeUI() throws InterruptedException {
        page.navigate(baseUrl + "/search-conductor");  // Navegar a la página de búsqueda de conductores
        Thread.sleep(1000);

        // Llenar el campo de búsqueda con el nombre del conductor a buscar
        String nombreBuscado = "Juan Pérez";
        page.fill("input[name='nombre']", nombreBuscado);

        // Hacer clic en el botón de búsqueda
        page.click("button[type='submit']");

        // Esperar que la página cargue los resultados
        Thread.sleep(1000);

        // Obtener los nombres de los conductores que aparecen como resultado
        List<String> conductores = page.locator(CELLS_XPATH).all()
                                    .stream()
                                    .map(Locator::textContent)
                                    .collect(Collectors.toList());

        // Verificar que el número de conductores encontrados es correcto
        assertEquals(1, conductores.size(), "Debería haber 1 conductor con el nombre 'Juan Pérez'");

        // Verificar que el conductor encontrado es el correcto
        assert conductores.stream().anyMatch(c -> c.contains("Juan Pérez"));
    }

}