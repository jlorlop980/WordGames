package joseantonio.wordgames.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NewJugadorDTO {
    private int admin;
    private String nombre;
    private String clave;
    private Long puntos;
    private Long equipo_id;

    private String avatar;

}

