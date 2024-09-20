package co.edu.uniandes.dse.parcialprueba.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import uk.co.jemos.podam.common.PodamExclude;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;


@Data
@Entity 
public class EspecialidadEntity extends BaseEntity {

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @PodamExclude
    @ManyToMany
    @JoinTable(name = "especialidad_medico",
            joinColumns = @JoinColumn(name = "especialidad_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "medico_id", referencedColumnName = "id", nullable = false))
    private List<MedicoEntity> medicos = new ArrayList<>();



}
