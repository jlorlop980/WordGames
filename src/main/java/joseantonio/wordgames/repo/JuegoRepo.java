package joseantonio.wordgames.repo;

import joseantonio.wordgames.model.Juego;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JuegoRepo extends JpaRepository<Juego,Long> {
}
