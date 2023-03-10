package joseantonio.wordgames.repo;

import joseantonio.wordgames.model.Juego;
import joseantonio.wordgames.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidaRepo extends JpaRepository<Partida,Long> {
}
