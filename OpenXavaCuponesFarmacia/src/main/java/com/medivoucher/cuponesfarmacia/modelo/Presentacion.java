package com.medivoucher.cuponesfarmacia.modelo;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;
 
@Entity 
@Getter @Setter
@Views({
    @View(name = "Default", members = "descripcion"),  
    @View(name = "WithId", members = "idPresentacion; descripcion")  
})
@Tab(properties = "idPresentacion, descripcion")  
public class Presentacion {
 
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ReadOnly
    private int idPresentacion;

    @Column(length = 50) 
    @Required
    private String descripcion;
}
