package org.example.persistencia.init;
import jakarta.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

import org.example.persistencia.model.Conductor;
import org.example.persistencia.repository.ConductorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"desarrollador-front"})
public class DatabaseInitDesarrolladorFront implements CommandLineRunner  {
    @Autowired
    ConductorRepository conductorRepository;


    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Conductor conductor1 = new Conductor("Juan Pérez", "1234567890", "+123456789", "Calle Falsa 123");
        Conductor conductor2 = new Conductor("María López", "0987654321", "+987654321", "Avenida Siempreviva 742");
        Conductor conductor3 = new Conductor("Carlos García", "1122334455", "+1122334455", "Boulevard de los Sueños 1010");
        List<Conductor> conductores = Arrays.asList(conductor1, conductor2, conductor3);
        conductorRepository.saveAll(conductores);
        
    }
}
