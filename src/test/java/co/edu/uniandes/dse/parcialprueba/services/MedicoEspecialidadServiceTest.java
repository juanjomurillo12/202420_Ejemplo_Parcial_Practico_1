package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


@DataJpaTest
@Import({MedicoEspecialidadService.class})
public class MedicoEspecialidadServiceTest {

    @Autowired
    private MedicoEspecialidadService medicoEspecialidadService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private MedicoEntity medico = new MedicoEntity();
    
    private List<EspecialidadEntity> especialidadesList = new ArrayList<>();
    
    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData(){
        entityManager.getEntityManager().createQuery("delete from MedicoEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("delete from EspecialidadEntity").executeUpdate();
    }

    /**
     * 
     * Insertar los datos iniciales para el correcto funcionamiento de las pruebas.
     */
    private void insertData(){
        medico = factory.manufacturePojo(MedicoEntity.class);
        entityManager.persist(medico);
        for (int i = 0; i < 3; i++) {
            EspecialidadEntity especialidad = factory.manufacturePojo(EspecialidadEntity.class);
            entityManager.persist(especialidad);
            especialidadesList.add(especialidad);
        }
    }

    /*
     * Prueba para agregar una especialidad a un medico
     */
    @Test
    public void addEspecialidadTest(){
        EspecialidadEntity especialidad = especialidadesList.get(0);
        MedicoEntity newMedico = medicoEspecialidadService.addEspecialidad(this.medico.getId(), especialidad.getId());
        assertNotNull(newMedico);
        assertEquals(newMedico.getEspecialidades().size(), 1);
        assertTrue(newMedico.getEspecialidades().contains(especialidad));

    }

    /*
     * Prueba para agregar una especialidad a un medico que no existe
     */
    @Test
    public void addEspecialidadMedicoNoExisteTest(){
        EspecialidadEntity especialidad = especialidadesList.get(0);
        assertThrows(IllegalArgumentException.class, () -> {
            medicoEspecialidadService.addEspecialidad(0L, especialidad.getId());
        });
    }

    /*
     * Prueba para agregar una especialidad que no existe a un medico
     */
    @Test
    public void addEspecialidadEspecialidadNoExisteTest(){
        assertThrows(IllegalArgumentException.class, () -> {
            medicoEspecialidadService.addEspecialidad(this.medico.getId(), 0L);
        });
    }




    
}
