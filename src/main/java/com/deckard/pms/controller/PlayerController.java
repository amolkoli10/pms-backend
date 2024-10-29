package com.deckard.pms.controller;

import com.deckard.pms.dto.PlayerDTO;
import com.deckard.pms.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private PlayerService playerService;

    @PostMapping
    public ResponseEntity<PlayerDTO> createPlayer(@RequestBody PlayerDTO playerDTO) {
        PlayerDTO savedPlayer = playerService.createPlayer(playerDTO);
        return new ResponseEntity<>(savedPlayer, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable("id") Long playerId) {
        PlayerDTO playerDTO = playerService.getPlayerById(playerId);
        return ResponseEntity.ok(playerDTO);
    }

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        List<PlayerDTO> allPlayers = playerService.getAllPlayers();
        return ResponseEntity.ok(allPlayers);
    }

    @PutMapping("{id}")
    public ResponseEntity<PlayerDTO> updatePlayer(@PathVariable("id") Long playerId,
                                                  @RequestBody PlayerDTO updatedPlayerDTO) {
        PlayerDTO playerDTO = playerService.updatePlayer(playerId, updatedPlayerDTO);
        return ResponseEntity.ok(playerDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable("id") Long playerId) {
        playerService.deletePlayer(playerId);
        return ResponseEntity.ok("Player Deleted Successfully");
    }
}
