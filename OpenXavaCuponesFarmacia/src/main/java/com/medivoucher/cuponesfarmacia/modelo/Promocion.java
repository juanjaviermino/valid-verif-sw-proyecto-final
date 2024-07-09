package com.medivoucher.cuponesfarmacia.modelo;

import javax.persistence.*;
import org.openxava.annotations.*;
import org.openxava.validators.ValidationException;

import lombok.*;

import java.util.Date;

@Entity
@Getter @Setter
@Views({
    @View(name = "Simple", members = "idPromocion; descripcion; fechaInicio; fechaFin")
})
public class Promocion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ReadOnly
    private int idPromocion;

    @Column(length = 255)
    @Required
    private String descripcion;

    @Required
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Required
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @PrePersist
    @PreUpdate
    private void validateDates() {
        if (fechaFin != null && fechaInicio != null && !fechaInicio.before(fechaFin)) {
            throw new ValidationException("Fecha inicio debe ser antes de la Fecha fin");
        }
    }
}
