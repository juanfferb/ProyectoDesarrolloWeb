package org.example.persistencia.init;

import java.util.Arrays;
import java.util.List;

import org.example.persistencia.model.Asignacion;
import org.example.persistencia.model.Bus;
import org.example.persistencia.model.Conductor;
import org.example.persistencia.model.Role;
import org.example.persistencia.model.Ruta;
import org.example.persistencia.model.User;
import org.example.persistencia.repository.AsignacionRepository;
import org.example.persistencia.repository.BusRepository;
import org.example.persistencia.repository.ConductorRepository;
import org.example.persistencia.repository.RutaRepository;
import org.example.persistencia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
@Profile({ "default" })
@Component
public class DBInitializer implements CommandLineRunner {

    @Autowired
    private ConductorRepository conductorRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private RutaRepository rutaRepository;

    @Autowired
    private AsignacionRepository asignacionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public void run(String... args) throws Exception {

        userRepository.save(
                new User("Alice", "Alisson", "alice@alice.com", passwordEncoder.encode("alice123"), Role.ADMIN));
        userRepository.save(
                new User("Bob", "Bobson", "bob@bob.com", passwordEncoder.encode("bob123"), Role.USER));
        userRepository.save(
                new User("Carlos", "Cruz", "carlos@carlos.com", passwordEncoder.encode("carlos123"), Role.COORD));
        
        Conductor conductor1 = new Conductor("Juan Pérez", "1234567890", "+123456789", "Calle Falsa 123");
        Conductor conductor2 = new Conductor("María López", "0987654321", "+987654321", "Avenida Siempreviva 742");
        Conductor conductor3 = new Conductor("Carlos García", "1122334455", "+1122334455", "Boulevard de los Sueños 1010");
        Conductor conductor4 = new Conductor("Andrés Ramírez", "2345678901", "+3105671234", "Carrera 15 #45-67");
        Conductor conductor5 = new Conductor("Laura Torres", "3456789012", "+3129876543", "Calle 72 #9-30");
        Conductor conductor6 = new Conductor("Fernando Martínez", "4567890123", "+3134567890", "Avenida Suba #123-45");
        Conductor conductor7 = new Conductor("Ana Gómez", "5678901234", "+3141234567", "Carrera 7 #77-10");
        Conductor conductor8 = new Conductor("Juliana Ospina", "6789012345", "+3158765432", "Calle 100 #15-20");
        Conductor conductor9 = new Conductor("Diego Rivera", "7890123456", "+3162345678", "Transversal 6 #54-90");
        Conductor conductor10 = new Conductor("Diana Morales", "8901234567", "+3173456789", "Carrera 50 #12-15");
        Conductor conductor11 = new Conductor("Luis Rojas", "9012345678", "+3184567890", "Avenida 68 #45-10");
        Conductor conductor12 = new Conductor("Sofía Hernández", "0123456789", "+3195678901", "Calle 26 #20-18");
        Conductor conductor13 = new Conductor("Pablo Salazar", "1234509876", "+3206789012", "Carrera 30 #89-12");

        List<Conductor> conductores = Arrays.asList(conductor1, conductor2, conductor3, conductor4, conductor5, conductor6,
                conductor7, conductor8, conductor9, conductor10, conductor11, conductor12, conductor13);
        conductorRepository.saveAll(conductores);

        Bus bus1 = new Bus("ABC123", "Modelo X");
        Bus bus2 = new Bus("XYZ789", "Modelo Y");
        Bus bus3 = new Bus("LMN456", "Modelo Z");
        Bus bus4 = new Bus("DEF123", "Mercedes-Benz Sprinter");
        Bus bus5 = new Bus("GHI456", "Volkswagen Crafter");
        Bus bus6 = new Bus("JKL789", "Toyota Coaster");
        Bus bus7 = new Bus("MNO101", "Hyundai County");
        Bus bus8 = new Bus("PQR112", "Isuzu NPR");
        Bus bus9 = new Bus("STU213", "Chevrolet NPR");
        Bus bus10 = new Bus("VWX314", "Mercedes-Benz Citaro");
        Bus bus11 = new Bus("YZA415", "Renault Master");
        Bus bus12 = new Bus("BCD516", "Volkswagen Transporter");
        Bus bus13 = new Bus("EFG617", "Ford Transit");

        List<Bus> buses = Arrays.asList(bus1, bus2, bus3, bus4, bus5, bus6, bus7, bus8, bus9, bus10, bus11, bus12, bus13);
        busRepository.saveAll(buses);

        Ruta ruta1 = new Ruta("Ruta Norte", "Estacion Norte", "7:00 am -9:00 pm");
        Ruta ruta2 = new Ruta("Ruta Sur", "Estacion Sur", "7:00 am -10:00 pm");
        Ruta ruta3 = new Ruta("Ruta Calle 80 - Portal Norte", "Estacion SantaFe", "7:00 am -9:00 pm");
        Ruta ruta4 = new Ruta("Ruta Av. Caracas - Héroes", "Estacion Heroes", "7:00 am -10:00 pm");
        Ruta ruta5 = new Ruta("Ruta Suba - Rincón", "Estacion Florez", "7:00 am -9:00 pm");
        Ruta ruta6 = new Ruta("Ruta Tunal - Portal Sur", "Estacion Parque Tunal", "8:00 am -10:00 pm");
        Ruta ruta7 = new Ruta("Ruta Bosa - Centro", "Estacion Bosa", "7:00 am -9:00 pm");
        Ruta ruta8 = new Ruta("Ruta Kennedy - Tintal", "Estacion Banderas", "8:00 am -9:00 pm");
        Ruta ruta9 = new Ruta("Ruta Engativá - Normandía", "Estacion Exito", "7:00 am -9:00 pm");
        Ruta ruta10 = new Ruta("Ruta San Cristóbal - Usaquén", "Estacion parque 93", "8:00 am -9:00 pm");
        Ruta ruta11 = new Ruta("Ruta Ciudad Bolívar - Portal Américas", "Estacion Loma", "7:00 am -9:00 pm");
        Ruta ruta12 = new Ruta("Ruta Chapinero - Teusaquillo", "Estacion Thea", "7:00 am -10:00 pm");
        List<Ruta> rutas = Arrays.asList(ruta1, ruta2, ruta3, ruta4, ruta5, ruta6, ruta7, ruta8, ruta9, ruta10, ruta11, ruta12);
        rutaRepository.saveAll(rutas);

        Asignacion asignacion1 = new Asignacion(conductor1, bus1, ruta1, "Lunes, Martes, Miércoles, Jueves, Viernes");
        Asignacion asignacion2 = new Asignacion(conductor1, bus2, ruta2, "Sábado, Domingo");
        Asignacion asignacion3 = new Asignacion(conductor2, bus3, ruta3, "Lunes, Martes, Miércoles, Jueves, Viernes, Sábado, Domingo");
        Asignacion asignacion4 = new Asignacion(conductor2, bus1, ruta4, "Lunes, Martes, Miércoles, Jueves, Viernes, Sábado");
        Asignacion asignacion5 = new Asignacion(conductor3, bus4, ruta5, "Miércoles, Jueves, Viernes");
        Asignacion asignacion6 = new Asignacion(conductor3, bus5, ruta6, "Martes, Jueves");
        Asignacion asignacion7 = new Asignacion(conductor4, bus6, ruta7, "Lunes, Martes, Miércoles");
        Asignacion asignacion8 = new Asignacion(conductor4, bus7, ruta8, "Sábado, Domingo");
        Asignacion asignacion9 = new Asignacion(conductor5, bus8, ruta9, "Lunes, Martes, Miércoles, Jueves, Viernes, Sábado, Domingo");
        Asignacion asignacion10 = new Asignacion(conductor5, bus9, ruta10, "Viernes, Sábado");
        Asignacion asignacion11 = new Asignacion(conductor6, bus10, ruta11, "Lunes, Martes, Miércoles, Jueves, Viernes, Sábado, Domingo");
        Asignacion asignacion12 = new Asignacion(conductor6, bus11, ruta12, "Martes, Miércoles, Jueves, Viernes, Sábado");
        Asignacion asignacion13 = new Asignacion(conductor7, bus12, ruta1, "Jueves, Viernes, Sábado, Domingo");
        Asignacion asignacion14 = new Asignacion(conductor7, bus2, ruta3, "Lunes, Martes, Miércoles, Jueves, Viernes");
        Asignacion asignacion15 = new Asignacion(conductor8, bus3, ruta4, "Sábado, Domingo");
        Asignacion asignacion16 = new Asignacion(conductor8, bus5, ruta2, "Lunes, Martes, Miércoles");
        Asignacion asignacion17 = new Asignacion(conductor9, bus6, ruta5, "Martes, Miércoles, Jueves");
        Asignacion asignacion18 = new Asignacion(conductor9, bus7, ruta7, "Viernes, Sábado, Domingo");
        Asignacion asignacion19 = new Asignacion(conductor10, bus8, ruta9, "Lunes, Martes, Miércoles, Jueves, Viernes, Sábado");
        Asignacion asignacion20 = new Asignacion(conductor10, bus10, ruta11, "Miércoles, Jueves, Viernes, Sábado, Domingo");
        Asignacion asignacion21 = new Asignacion(conductor11, bus1, ruta3, "Lunes, Martes, Miércoles, Jueves, Viernes");
        Asignacion asignacion22 = new Asignacion(conductor11, bus4, ruta5, "Sábado, Domingo");
        Asignacion asignacion23 = new Asignacion(conductor12, bus2, ruta6, "Lunes, Martes, Miércoles, Jueves, Viernes, Sábado, Domingo");
        Asignacion asignacion24 = new Asignacion(conductor12, bus11, ruta8, "Martes, Miércoles, Jueves, Viernes, Sábado");
        Asignacion asignacion25 = new Asignacion(conductor13, bus12, ruta12, "Jueves, Viernes, Sábado, Domingo");
        Asignacion asignacion26 = new Asignacion(conductor13, bus13, ruta2, "Lunes, Martes, Miércoles, Jueves, Viernes, Sábado, Domingo");
        Asignacion asignacion27 = new Asignacion(conductor13, bus9, ruta4, "Martes, Miércoles, Jueves");


        List<Asignacion> listaAsignaciones = Arrays.asList(asignacion1, asignacion2, asignacion3, asignacion4, asignacion5,
                asignacion6, asignacion7, asignacion8, asignacion9, asignacion10, asignacion11, asignacion12, asignacion13,
                asignacion14, asignacion15, asignacion16, asignacion17, asignacion18, asignacion19, asignacion20,
                asignacion21, asignacion22, asignacion23, asignacion24, asignacion25, asignacion26, asignacion27);
        
        asignacionRepository.saveAll(listaAsignaciones);

    }
}
