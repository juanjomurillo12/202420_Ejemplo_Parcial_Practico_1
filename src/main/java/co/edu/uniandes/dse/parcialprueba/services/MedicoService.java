package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    /**
     * Crea un nuevo medico
     * 
     * @param medicoEntity
     * @return la entidad del medico luego de persistirla
     * @throws BusinessLogicException
     */
    @Transactional
    public MedicoEntity createMedico(MedicoEntity medicoEntity) {
        log.info("Inicia proceso de creación de medico");
        // Verificar que el registro medico empiece con "RM"
        if (!medicoEntity.getRegistro_medico().startsWith("RM")) {
            throw new IllegalArgumentException("El registro medico debe empezar con RM");
        }
        log.info("Termina proceso de creación de medico");
        return medicoRepository.save(medicoEntity);
    }


    
}
