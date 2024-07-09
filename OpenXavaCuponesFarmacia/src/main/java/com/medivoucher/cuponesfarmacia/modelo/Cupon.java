package com.medivoucher.cuponesfarmacia.modelo;

import javax.persistence.*;
import org.openxava.annotations.*;
import org.apache.commons.lang3.RandomStringUtils;

import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
public class Cupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ReadOnly
    private int idCupon;

    @Column(length = 255)
    @Required
    private String descripcion;

    @Column(length = 10, unique = true)
    @ReadOnly 
    private String codigo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "promocion_id", referencedColumnName = "idPromocion")
    @Required
    @DescriptionsList(descriptionProperties = "descripcion") 
    private Promocion promocion;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "cupon_farmacia",
        joinColumns = @JoinColumn(name = "cupon_id"),
        inverseJoinColumns = @JoinColumn(name = "farmacia_id")
    )
    @ListProperties("idFarmacia, nombre")
    private List<Farmacia> farmaciasAceptadas;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "cupon_medicamento",
        joinColumns = @JoinColumn(name = "cupon_id"),
        inverseJoinColumns = @JoinColumn(name = "medicamento_id")
    )
    @ListProperties("idMedicamento, nombre")
    private List<Medicamento> medicamentosAplicables;

    @PrePersist
    private void generateCodigo() {
        if (codigo == null || codigo.isEmpty()) {
            codigo = RandomStringUtils.randomAlphanumeric(10).toUpperCase(); 
        }
    }
}
