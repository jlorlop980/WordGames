package joseantonio.wordgames.controller;

import joseantonio.wordgames.DTO.NuevaPartidaDTO;
import joseantonio.wordgames.model.Equipo;
import joseantonio.wordgames.model.Juego;
import joseantonio.wordgames.model.Jugador;
import joseantonio.wordgames.model.Partida;
import joseantonio.wordgames.repo.EquipoRepo;
import joseantonio.wordgames.repo.JuegoRepo;
import joseantonio.wordgames.repo.JugadorRepo;
import joseantonio.wordgames.repo.PartidaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
public class PartidaController {

    private final PartidaRepo partidaRepo;
    private final JugadorRepo jugadorRepo;
    private final JuegoRepo juegoRepo;
    private final EquipoRepo equipoRepo;

    //todas las partidas
    @GetMapping("/partidas")
    public ResponseEntity<?> getAllMatches(){

        List<Partida> allMatches = partidaRepo.findAll();

        if(allMatches.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(allMatches);

    }

    //partida por id
    @GetMapping("/partidas/{id}")
    public ResponseEntity<?> getMatch(@PathVariable Long id){
        Partida match = partidaRepo.findById(id).orElse(null);

        return (match == null) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(match);
    }

    @PostMapping("/partidas")
    public ResponseEntity<?> registerMatch(@RequestBody NuevaPartidaDTO newMatchData) {
        // Comprobar si los campos requeridos están en el DTO
        if (newMatchData.getJugador_id() == null || newMatchData.getJuego_id() == null || newMatchData.getPalabra() == null) {
            return ResponseEntity.badRequest().body("Faltan campos requeridos en el DTO.");
        }

        // Buscar el jugador y el juego por sus identificadores
        Jugador jugador = jugadorRepo.findById(newMatchData.getJugador_id())
                .orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado al jugador especificado."));
        Juego juego = juegoRepo.findById(newMatchData.getJuego_id())
                .orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado el juego especificado."));

        // Crear una nueva partida
        Partida partida = new Partida();
        partida.setJugador(jugador);
        partida.setJuego(juego);
        partida.setDate(new Date());
        partida.setPalabra(newMatchData.getPalabra());

        // Calcular los puntos obtenidos y asignarlos a la partida y jugador
        int puntos = calcularPuntos(newMatchData.getPalabra(), juego.getDificultad());
        partida.setPuntos(puntos);
        jugador.setPuntos(jugador.getPuntos() + puntos);

        // Si el jugador tiene un equipo, aumentar los puntos del equipo
        Equipo equipo = jugador.getEquipo();
        if (equipo != null) {
            equipo.setPuntos(equipo.getPuntos() + puntos);
            equipoRepo.save(equipo);
        }

        // Guardar la partida y el jugador actualizados
        partidaRepo.save(partida);
        jugadorRepo.save(jugador);

        return ResponseEntity.ok(partida);
    }

    // Función auxiliar para calcular los puntos obtenidos en una partida
    private int calcularPuntos(String palabra, String dificultad) {
        Set<Character> letrasDiferentes = new HashSet<>();
        for (int i = 0; i < palabra.length(); i++) {
            letrasDiferentes.add(palabra.charAt(i));
        }
        int longitud = palabra.length();
        int multiplicador = 1;

        if (longitud >= 8 && letrasDiferentes.size() >= 6) {
            multiplicador = 3;
        } else if (longitud >= 6 && letrasDiferentes.size() >= 4) {
            multiplicador = 2;
        }

        int puntos = longitud * multiplicador;

        switch (dificultad){
            case "Facil":
                puntos += 1;
                break;

            case "Medio":
                puntos *= 2;
                break;
            case "Dificil":
                puntos *= 3;
                break;
        }

        return puntos;
    }

    @DeleteMapping("/partidas/{id}")
    public ResponseEntity<?> deleteMatch(@PathVariable Long id){
        Partida matchToDelete = partidaRepo.findById(id).orElse(null);

        if(matchToDelete == null){
            return ResponseEntity.notFound().build();
        }

        partidaRepo.deleteById(id);

        // Al eliminar una partida conidero que se deben eliminar los puntos del jugador en concretp
        Jugador jugadorAfectado = matchToDelete.getJugador();
        jugadorAfectado.setPuntos(jugadorAfectado.getPuntos() - matchToDelete.getPuntos());

        //En el caso que el jugador pertenezca a un equipo elimino tambien los puntos del equipo
        if (jugadorAfectado.getEquipo()!=null){
            Equipo equipoAfectado = equipoRepo.findById(jugadorAfectado.getEquipo().getId()).orElse(null);
            equipoAfectado.setPuntos(equipoAfectado.getPuntos()-matchToDelete.getPuntos());
        }

        jugadorRepo.save(jugadorAfectado);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(matchToDelete);
    }

    // No le veo mucho sentido un put ya que jugador es el que lo jugó la palabra tampoco tiene sentido de cambiar ni el juego al que jugó

}
