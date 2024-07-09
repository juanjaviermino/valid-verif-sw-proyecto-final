package com.medivoucher.cuponesfarmacia.modelo;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity
@Getter @Setter
@Views({
    @View(name = "Default", members = "nombre"),  
    @View(name = "WithId", members = "idMarca; nombre")  
})
@Tab(properties = "idMarca, nombre")  
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @ReadOnly
    private int idMarca;

    @Column(length = 50)
    @Required
    private String nombre;
}
