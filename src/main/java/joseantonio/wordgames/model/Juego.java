package joseantonio.wordgames.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Juego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String instrucciones;

    @Column(name="intentosmax") // Hibernate es muy gracioso y cambia intentosMax a intentos_max...
    private int intentosMax;

    private String dificultad;
}