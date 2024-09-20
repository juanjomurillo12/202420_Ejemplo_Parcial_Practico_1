package co.edu.uniandes.dse.parcialprueba.services;


import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import uk.co.jemos.podam.api.PodamFactory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import org.junit.jupiter.api.Test;


@DataJpaTest
@Import(EspecialidadService.class)
public class EspecialidadServiceTest {

    @Autowired
    private EspecialidadService especialidadService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private List<EspecialidadEntity> especialidadesList = new ArrayList<>();


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
        entityManager.getEntityManager().createQuery("delete from EspecialidadEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("delete from MedicoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            EspecialidadEntity especialidad = factory.manufacturePojo(EspecialidadEntity.class);
            entityManager.persist(especialidad);
            especialidadesList.add(especialidad);
        }}
    
    /**
     * Prueba para crear una especialidad
     * @throws IllegalOperationException
     */
    @Test
    void testCreateEspecialidad() throws IllegalOperationException {
        EspecialidadEntity newEspecialidad = factory.manufacturePojo(EspecialidadEntity.class);
        newEspecialidad.setDescripcion("Esta es una descripcion de la especialidad");
        EspecialidadEntity result = especialidadService.createEspecialidad(newEspecialidad);
        assertNotNull(result);
        EspecialidadEntity entity = entityManager.find(EspecialidadEntity.class, result.getId());
        assertNotNull(entity);
        assertEquals(newEspecialidad.getNombre(), entity.getNombre());
        assertEquals(newEspecialidad.getDescripcion(), entity.getDescripcion());
    }

    /**
     * Prueba que la especialidad tenga minimo 10 caracteres
     * @throws IllegalOperationException
     */
    @Test
    void testCreateEspecialidadNombreMenos10Caracteres() {
        assertThrows(IllegalArgumentException.class, () -> {
            EspecialidadEntity newEntity = factory.manufacturePojo(EspecialidadEntity.class);
            newEntity.setDescripcion("No es");
            especialidadService.createEspecialidad(newEntity);
            
        });
    }
    






    
}
