package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EspecialidadService {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    /**
     * Crear una nueva especialidad
     * 
     * @param especialidadEntity
     * @return la entidad de la especialidad luego de persistirla
     * @throws BusinessLogicException
     */
    @Transactional
    public EspecialidadEntity createEspecialidad(EspecialidadEntity especialidadEntity) {
        log.info("Inicia proceso de creación de especialidad");
        // Verificar que la descripcion tenga minimo 10 caracteres
        if (especialidadEntity.getDescripcion().length() < 10) {
            throw new IllegalArgumentException("La descripción debe tener al menos 10 caracteres");
        }
        log.info("Termina proceso de creación de especialidad");
        return especialidadRepository.save(especialidadEntity);
    }

    

    
}
