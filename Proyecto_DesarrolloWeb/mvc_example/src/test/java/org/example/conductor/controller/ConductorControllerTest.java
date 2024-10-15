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
        
    }

    @Test
    void verConductor() {

    }

    @Test
    void actualizarConductor() {

    }

    @Test
    void guardarConductor() {

    }

    @Test
    void crearConductor() {

    }

    @Test
    void eliminarConductor() {

    }

    @Test
    void buscarConductores() {

    }

}
