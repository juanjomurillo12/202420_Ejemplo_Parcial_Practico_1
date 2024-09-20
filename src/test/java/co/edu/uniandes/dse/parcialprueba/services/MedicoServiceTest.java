package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;


@DataJpaTest
@Import(MedicoService.class)
public class MedicoServiceTest {

    @Autowired
    private MedicoService medicoService;

    @Autowired
    protected TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private List<MedicoEntity> medicosList = new ArrayList<>();

    /**
     * Configuración inicial de la prueba.
     */
    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from MedicoEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("delete from EspecialidadEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            MedicoEntity medico = factory.manufacturePojo(MedicoEntity.class);
            entityManager.persist(medico);
            medicosList.add(medico);
        }}

    /**
     * Prueba para crear un Medico
     * @throws IllegalOperationException
     */
    @Test
    void testCreateMedico() throws IllegalOperationException {
        MedicoEntity newEntity = factory.manufacturePojo(MedicoEntity.class);
        newEntity.setRegistro_medico("RM123456");
        MedicoEntity result = medicoService.createMedico(newEntity);
        assertNotNull(result);
        MedicoEntity entity = entityManager.find(MedicoEntity.class, result.getId());
        assertNotNull(entity);
        assertEquals(newEntity.getNombre(), entity.getNombre());
        assertEquals(newEntity.getApellido(), entity.getApellido());
        assertEquals(newEntity.getRegistro_medico(), entity.getRegistro_medico());
    }

    /**
     * Prueba que el registro medico empiece con RM
     * @throws IllegalOperationException
     */
    @Test
    void testCreateMedicoRegistroMedico() throws IllegalOperationException {
        assertThrows(IllegalArgumentException.class, () -> {
            MedicoEntity newEntity = factory.manufacturePojo(MedicoEntity.class);
            newEntity.setRegistro_medico("123456");
            medicoService.createMedico(newEntity);
        });
    }




    
    


    
}
