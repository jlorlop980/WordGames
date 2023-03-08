package joseantonio.wordgames.repo;

import joseantonio.wordgames.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JugadorRepo extends JpaRepository<Jugador,Long> {
}
