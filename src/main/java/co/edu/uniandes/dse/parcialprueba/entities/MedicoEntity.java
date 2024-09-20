package co.edu.uniandes.dse.parcialprueba.entities;

import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

@Data
@Entity
public class MedicoEntity extends BaseEntity {

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String registro_medico;

    @PodamExclude
    @ManyToMany(mappedBy = "medicos")
    private List<EspecialidadEntity> especialidades = new ArrayList<>();




    


    
}
