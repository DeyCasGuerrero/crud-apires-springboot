package com.api.app.rest.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name = "noticias")
public class Noticias {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_noticias;

    @Basic
    private String titular;
    private String contenido;
    private String link;

}
