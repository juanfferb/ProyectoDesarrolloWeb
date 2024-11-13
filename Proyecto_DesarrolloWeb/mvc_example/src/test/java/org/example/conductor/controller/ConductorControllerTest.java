package org.example.conductor.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.example.persistencia.model.Conductor;
import org.example.persistencia.model.Role;
import org.example.persistencia.model.User;
import org.example.persistencia.PersistenciaApplication;
import org.example.persistencia.dto.ConductorDTO;
import org.example.persistencia.dto.JwtAuthenticationResponse;
import org.example.persistencia.dto.LoginDTO;
import org.example.persistencia.repository.ConductorRepository;
import org.example.persistencia.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest(classes = PersistenciaApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("desarrollador-back")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ConductorControllerTest {

    @Value("${server.port}")
    private int serverPort;

    private String SERVER_URL;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConductorRepository conductorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void init() {
        // Usuario para las pruebas
        userRepository.save(
                new User("Alice", "Alisson", "alice@alice.com", passwordEncoder.encode("alice123"), Role.ADMIN));
        userRepository.save(
                new User("Bob", "Bobson", "bob@bob.com", passwordEncoder.encode("bob123"), Role.USER));
        
        SERVER_URL = "http://localhost:" + serverPort;

        // Se crean los conductores para las pruebas
        Conductor conductor1 = new Conductor("Juan Pérez", "1234567890", "+123456789", "Calle Falsa 123");
        Conductor conductor2 = new Conductor("María López", "0987654321", "+987654321", "Avenida Siempreviva 742");
        Conductor conductor3 = new Conductor("Carlos García", "1122334455", "+1122334455", "Boulevard de los Sueños 1010");
        List<Conductor> conductores = Arrays.asList(conductor1, conductor2, conductor3);
        conductorRepository.saveAll(conductores);
    }

    private JwtAuthenticationResponse login(String email, String password) {
        return webTestClient.post()
                .uri(SERVER_URL + "/auth/login")
                .bodyValue(new LoginDTO(email, password))
                .exchange()
                .expectStatus().isOk()
                .expectBody(JwtAuthenticationResponse.class)
                .returnResult()
                .getResponseBody();
    }

    @Test
    void listarConductores() {
        JwtAuthenticationResponse alice = login("alice@alice.com", "alice123");
        JwtAuthenticationResponse bob = login("bob@bob.com", "bob123");

        // Verificar que el admin (Alice) puede acceder a la lista de conductores
        webTestClient.get().uri(SERVER_URL + "/conductor/list")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + alice.getToken())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ConductorDTO.class)
                .hasSize(3);

        // Verificar que el user (Bob) también puede acceder a la lista de conductores
        webTestClient.get().uri(SERVER_URL + "/conductor/list")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + bob.getToken())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ConductorDTO.class)
                .hasSize(3);
    }


    @Test
    void verConductor() {
        Long idConductor = 1L;

        // Probar con un ADMIN
        JwtAuthenticationResponse alice = login("alice@alice.com", "alice123");
        webTestClient.get().uri(SERVER_URL + "/conductor/view/{id}", idConductor)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + alice.getToken())
                .exchange()
                .expectStatus().isOk()
                .expectBody(ConductorDTO.class)
                .value(conductor -> assertEquals("Juan Pérez", conductor.getNombre()));

        // Probar con un USER
        JwtAuthenticationResponse bob = login("bob@bob.com", "bob123");
        webTestClient.get().uri(SERVER_URL + "/conductor/view/{id}", idConductor)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + bob.getToken())
                .exchange()
                .expectStatus().isOk()
                .expectBody(ConductorDTO.class)
                .value(conductor -> assertEquals("Juan Pérez", conductor.getNombre()));
    }


    @Test
    void actualizarConductorAutorizado() {
        Long idConductor = 1L;

        // Probar con un ADMIN (Alice)
        JwtAuthenticationResponse alice = login("alice@alice.com", "alice123");
        ConductorDTO conductorDTO = new ConductorDTO();
        conductorDTO.setNombre("Juan Pérez Actualizado");
        conductorDTO.setCedula("1234567890");
        conductorDTO.setTelefono("+123456789");
        conductorDTO.setDireccion("Calle Falsa 456");

        webTestClient.put().uri(SERVER_URL + "/conductor/update/{id}", idConductor)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + alice.getToken())
                .bodyValue(conductorDTO)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ConductorDTO.class)
                .value(conductor -> assertEquals("Juan Pérez Actualizado", conductor.getNombre()));
    }

    @Test
    void actualizarConductorNoAutorizado(){
        Long idConductor = 1L;
        // Probar con un USER (Bob) - Debe fallar
        JwtAuthenticationResponse bob = login("bob@bob.com", "bob123");
        ConductorDTO conductorDTO = new ConductorDTO();
        conductorDTO.setNombre("Juan Pérez Actualizado");
        conductorDTO.setCedula("1234567890");
        conductorDTO.setTelefono("+123456789");
        conductorDTO.setDireccion("Calle Falsa 456");
        webTestClient.put().uri(SERVER_URL + "/conductor/update/{id}", idConductor)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + bob.getToken())
                .bodyValue(conductorDTO)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    void guardarConductorAutorizado() {
        // Probar con un ADMIN (Alice)
        JwtAuthenticationResponse alice = login("alice@alice.com", "alice123");
        Conductor conductor = new Conductor("Ana García", "2233445566", "+1122334455", "Calle Nueva 789");

        webTestClient.post().uri(SERVER_URL + "/conductor/save")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + alice.getToken())
                .bodyValue(conductor)
                .exchange()
                .expectStatus().isOk();  // Alice (ADMIN) puede guardar un conductor

        // Probar con un USER (Bob) - Debe fallar
        JwtAuthenticationResponse bob = login("bob@bob.com", "bob123");
        webTestClient.post().uri(SERVER_URL + "/conductor/save")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + bob.getToken())
                .bodyValue(conductor)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.FORBIDDEN);  // Bob (USER) no tiene permiso
    }

    @Test
    void guardarConductorNoAutorizado() {
        // Probar con un USER (Bob) - Debe fallar
        JwtAuthenticationResponse bob = login("bob@bob.com", "bob123");
        Conductor conductor = new Conductor("Ana García", "2233445566", "+1122334455", "Calle Nueva 789");
        webTestClient.post().uri(SERVER_URL + "/conductor/save")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + bob.getToken())
                .bodyValue(conductor)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.FORBIDDEN);  // Bob (USER) no tiene permiso
    }

    @Test
    void crearConductorAutorizado() {
        // Probar con un ADMIN (Alice)
        JwtAuthenticationResponse alice = login("alice@alice.com", "alice123");

        ConductorDTO conductorDTO = new ConductorDTO();
        conductorDTO.setNombre("Luis Pérez");
        conductorDTO.setCedula("3344556677");
        conductorDTO.setTelefono("+9988776655");
        conductorDTO.setDireccion("Avenida Central 100");

        webTestClient.post().uri(SERVER_URL + "/conductor/create")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + alice.getToken())
                .bodyValue(conductorDTO)
                .exchange()
                .expectStatus().isOk();  // Alice (ADMIN) puede crear un conductor

    }

    @Test
    void crearConductorNoAutorizado() {
        // Probar con un USER (Bob) - Debe fallar
        JwtAuthenticationResponse bob = login("bob@bob.com", "bob123");
        
        ConductorDTO conductorDTO = new ConductorDTO();
        conductorDTO.setNombre("Luis Pérez");
        conductorDTO.setCedula("3344556677");
        conductorDTO.setTelefono("+9988776655");
        conductorDTO.setDireccion("Avenida Central 100");

        webTestClient.post().uri(SERVER_URL + "/conductor/create")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + bob.getToken())
                .bodyValue(conductorDTO)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.FORBIDDEN); // Bob (USER) no tiene permiso
    }

    @Test
    void eliminarConductorAutorizado() {
        Long idConductor = 1L;

        // Probar con un ADMIN (Alice)
        JwtAuthenticationResponse alice = login("alice@alice.com", "alice123");

        webTestClient.delete().uri(SERVER_URL + "/conductor/delete/{id}", idConductor)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + alice.getToken())
                .exchange()
                .expectStatus().isOk();  // Alice (ADMIN) puede eliminar el conductor

    }

    @Test
    void eliminarConductorNoAutorizado() {
        Long idConductor = 1L;

        // Probar con un USER (Bob) - Debe fallar
        JwtAuthenticationResponse bob = login("bob@bob.com", "bob123");
        webTestClient.delete().uri(SERVER_URL + "/conductor/delete/{id}", idConductor)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + bob.getToken())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.FORBIDDEN);  // Bob (USER) no tiene permiso
    }


    @Test
    void buscarConductores() {
        String nombreBuscado = "Juan";  // Nombre que se busca
        JwtAuthenticationResponse alice = login("alice@alice.com", "alice123");
        JwtAuthenticationResponse bob = login("bob@bob.com", "bob123");

        // Probar con un ADMIN (Alice)
        webTestClient.get().uri(SERVER_URL + "/conductor/search?nombre={nombre}", nombreBuscado)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + alice.getToken())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ConductorDTO.class)
                .hasSize(1)  // Se espera encontrar 1 conductor con el nombre "Juan"
                .value(conductores -> assertEquals("Juan Pérez", conductores.get(0).getNombre()));  // Verifica el nombre del conductor

        // Probar con un USER (Bob)
        webTestClient.get().uri(SERVER_URL + "/conductor/search?nombre={nombre}", nombreBuscado)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + bob.getToken())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ConductorDTO.class)
                .hasSize(1)  // Se espera encontrar 1 conductor con el nombre "Juan"
                .value(conductores -> assertEquals("Juan Pérez", conductores.get(0).getNombre()));  // Verifica el nombre del conductor
    }
}