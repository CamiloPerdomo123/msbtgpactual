package com.ms.mspactual.infrastructurecross.metricas;

import com.ms.mspactual.infrastructure.repository.entity.Fondo;
import com.ms.mspactual.infrastructure.repository.persistencia.FondoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;

@Configuration
@RequiredArgsConstructor
public class DataLoader {

    private final FondoRepository fondoRepository;

    @Bean
    CommandLineRunner cargarFondos() {
        return args -> {

            if (fondoRepository.count() == 0) {

                fondoRepository.save(new Fondo("1","FPV_BTG_PACTUAL_RECAUDADORA",75000.0,"FPV"));
                fondoRepository.save(new Fondo("2","FPV_BTG_PACTUAL_ECOPETROL",125000.0,"FPV"));
                fondoRepository.save(new Fondo("3","DEUDAPRIVADA",50000.0,"FIC"));
                fondoRepository.save(new Fondo("4","FDO-ACCIONES",250000.0,"FIC"));
                fondoRepository.save(new Fondo("5","FPV_BTG_PACTUAL_DINAMICA",100000.0,"FPV"));

                System.out.println("Fondos de pensión cargados correctamente");
            }

        };
    }
}
