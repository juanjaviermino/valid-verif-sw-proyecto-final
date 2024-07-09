package com.medivoucher.cuponesfarmacia.modelo;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
@Views({
    @View(name = "Complete", members = "idMedicamento; nombre; marca.nombre; presentacion.descripcion"),  
    @View(name = "ForFarmacia", members = "idMedicamento, nombre, marca.nombre, presentacion.descripcion")  
})
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ReadOnly
    private int idMedicamento;  

    @Column(length = 50)
    @Required
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @DescriptionsList(descriptionProperties = "nombre")
    @JoinColumn(name = "marca_id", referencedColumnName = "idMarca")
    private Marca marca;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @DescriptionsList(descriptionProperties = "descripcion")
    @JoinColumn(name = "presentacion_id", referencedColumnName = "idPresentacion")  
    private Presentacion presentacion;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "medicamento_farmacia",
        joinColumns = @JoinColumn(name = "medicamento_id"),
        inverseJoinColumns = @JoinColumn(name = "farmacia_id")
    )
    @CollectionView("inList") 
    private List<Farmacia> farmacias;
}
