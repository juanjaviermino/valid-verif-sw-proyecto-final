package com.medivoucher.cuponesfarmacia.modelo;

import javax.persistence.*;
import org.openxava.annotations.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
@Views({
    @View(name = "default", members = "idFarmacia; nombre; medicamentos"),
    @View(name = "inList", members = "idFarmacia, nombre")
})
@Tab(properties = "idFarmacia, nombre") 
public class Farmacia {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ReadOnly
    private int idFarmacia;
 
    @Column(length = 50)
    @Required
    private String nombre;

    @ManyToMany(mappedBy = "farmacias")
    @CollectionView("ForFarmacia")  
    @ListProperties("idMedicamento, nombre, marca.nombre, presentacion.descripcion") 
    private List<Medicamento> medicamentos;  

}
