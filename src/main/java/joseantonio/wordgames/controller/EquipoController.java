package joseantonio.wordgames.controller;


import joseantonio.wordgames.model.Equipo;
import joseantonio.wordgames.repo.EquipoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EquipoController {

    private final EquipoRepo equipoRepo;

    @GetMapping("/equipos")
    public ResponseEntity<?> getAllTeams(){
        List<Equipo> allTeams = equipoRepo.findAll();

        if(allTeams.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(allTeams);
    }

    @GetMapping("/equipos/{id}")
    public ResponseEntity<?> getTeam(@PathVariable Long id){
        Equipo team = equipoRepo.findById(id).orElse(null);

        return (team == null) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(team);
    }

    @PostMapping("/equipos")
    public ResponseEntity<?> addTeam(@RequestBody Equipo newTeam){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(equipoRepo.save(newTeam));
    }

    @DeleteMapping("/equipos/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long id){
        Equipo deletedTeam = equipoRepo.findById(id).orElse(null);

        if(deletedTeam == null){
            return ResponseEntity.notFound().build();
        }

        equipoRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(deletedTeam);
    }

    @PutMapping("equipos/{id}")
    public ResponseEntity<?> updateTeam(@RequestBody Equipo teamNewData, @PathVariable Long id){
        Equipo team = equipoRepo.findById(id).orElse(null);

        if(team == null){
            return ResponseEntity.notFound().build();
        }

        if (teamNewData.getNombre() != null)
            team.setNombre(teamNewData.getNombre());

        if (teamNewData.getLogo() != null)
            team.setLogo(teamNewData.getLogo());

        equipoRepo.save(team);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(team);
    }

}