package org.example.conductor.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.example.persistencia.model.Conductor;
import org.example.persistencia.repository.ConductorRepository;
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


@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("desarrollador-back")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ConductorControllerTest{
    // @Value("${server.port}")
    // private int serverPort;

    private final String SERVER_URL;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ConductorRepository conductorRepository;

    public ConductorControllerTest(@Value("${server.port}") int serverPort) {
        SERVER_URL = "http://localhost:" + serverPort;
    }

    @BeforeEach
    void init() {
        Conductor conductor1 = new Conductor("Juan Pérez", "1234567890", "+123456789", "Calle Falsa 123");
        Conductor conductor2 = new Conductor("María López", "0987654321", "+987654321", "Avenida Siempreviva 742");
        Conductor conductor3 = new Conductor("Carlos García", "1122334455", "+1122334455", "Boulevard de los Sueños 1010");
        List<Conductor> conductores = Arrays.asList(conductor1, conductor2, conductor3);
        conductorRepository.saveAll(conductores);
    }

    @Test
    void listarConductores() {
        webTestClient.get().uri(SERVER_URL + "/conductor/list")
                .exchange()
                .expectStatus().isOk()  
                .expectBodyList(ConductorDTO.class)  
                .hasSize(3);
    }

    @Test
    void verConductor() {
        Long idConductor = 1L;
    
        webTestClient.get().uri(SERVER_URL + "/conductor/view/{id}", idConductor)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ConductorDTO.class)
                .value(conductor -> assertEquals("Juan Pérez", conductor.getNombre()));  // Verifica que el nombre del conductor sea correcto
    }

    @Test
    void actualizarConductor() {
        Long idConductor = 1L;
        ConductorDTO conductorDTO = new ConductorDTO();
        conductorDTO.setNombre("Juan Pérez Actualizado");
        conductorDTO.setCedula("1234567890");
        conductorDTO.setTelefono("+123456789");
        conductorDTO.setDireccion("Calle Falsa 456");
    
        webTestClient.put().uri(SERVER_URL + "/conductor/update/{id}", idConductor)
                .bodyValue(conductorDTO)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ConductorDTO.class)
                .value(conductor -> assertEquals("Juan Pérez Actualizado", conductor.getNombre()));
    }

    @Test
    void guardarConductor() {
        Conductor conductor = new Conductor("Ana García", "2233445566", "+1122334455", "Calle Nueva 789");
    
        webTestClient.post().uri(SERVER_URL + "/conductor/save")
                .bodyValue(conductor)
                .exchange()
                .expectStatus().isOk(); 
    }

    @Test
    void crearConductor() {
        ConductorDTO conductorDTO = new ConductorDTO();
        conductorDTO.setNombre("Luis Pérez");
        conductorDTO.setCedula("3344556677");
        conductorDTO.setTelefono("+9988776655");
        conductorDTO.setDireccion("Avenida Central 100");
    
        webTestClient.post().uri(SERVER_URL + "/conductor/create")
                .bodyValue(conductorDTO)
                .exchange()
                .expectStatus().isOk();  
    }

    @Test
    void eliminarConductor() {
        Long idConductor = 1L;
    
        webTestClient.delete().uri(SERVER_URL + "/conductor/delete/{id}", idConductor)
                .exchange()
                .expectStatus().isOk();  
    }

    @Test
    void buscarConductores() {
        String nombreBuscado = "Juan";  
    
        webTestClient.get().uri(SERVER_URL + "/conductor/search?nombre={nombre}", nombreBuscado)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ConductorDTO.class)
                .hasSize(1)  
                .value(conductores -> assertEquals("Juan Pérez", conductores.get(0).getNombre()));
    }

}
