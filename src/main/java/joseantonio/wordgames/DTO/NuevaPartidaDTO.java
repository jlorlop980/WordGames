package joseantonio.wordgames.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NuevaPartidaDTO {

    private Long jugador_id;
    private Long juego_id;
    private String palabra;
}
