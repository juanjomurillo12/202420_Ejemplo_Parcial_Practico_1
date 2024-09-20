package co.edu.uniandes.dse.parcialprueba.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicoEspecialidadService {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    /**
     * Agregar una especialidad a un medico
     * 
     * @param medicoId
     * @param especialidadId
     * @return la entidad de la especialidad luego de persistirla
     * @throws BusinessLogicException
     */
    @Transactional
    public MedicoEntity addEspecialidad(Long medicoId, Long especialidadId) {
        log.info("Inicia proceso de agregar especialidad al medico con id = {0}", medicoId);

        // verificar que el medico exista
        if (!medicoRepository.existsById(medicoId)) {
            throw new IllegalArgumentException("El medico con id = " + medicoId + " no existe");
        }

        // verificar que la especialidad exista
        if (!especialidadRepository.existsById(especialidadId)) {
            throw new IllegalArgumentException("La especialidad con id = " + especialidadId + " no existe");
        }
        MedicoEntity medico = medicoRepository.findById(medicoId).get();
        EspecialidadEntity especialidad = especialidadRepository.findById(especialidadId).get();
        medico.getEspecialidades().add(especialidad);
        log.info("Termina proceso de agregar especialidad al medico con id = {0}", medicoId);
        return medicoRepository.save(medico);
    }


    
}
