package com.api.app.rest.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "proyectos")
public class Proyecto {
    private static  final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_proyecto;

    @Basic
    private String titulo;
    private String contexto;
    private String imagen;
    private String linkRepositorio;

}
