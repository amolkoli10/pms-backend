package com.deckard.pms.controller;

import com.deckard.pms.dto.PlayerDTO;
import com.deckard.pms.service.PlayerService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
public class PlayerController {

  private final PlayerService playerService;

  @GetMapping("/players")
  public String listPlayers(Model model) {
    List<PlayerDTO> players = playerService.getAllPlayers();
    if (players.isEmpty()) {
      PlayerDTO defaultPlayer = new PlayerDTO();
      defaultPlayer.setId(0L);
      defaultPlayer.setFirstName("Deckard");
      defaultPlayer.setLastName("Shaw");
      defaultPlayer.setClub("Fulham");
      players.add(defaultPlayer);
    }
    model.addAttribute("players", players);
    return "players";
  }

  @GetMapping("/players/new")
  public String showCreateForm(Model model) {
    model.addAttribute("player", new PlayerDTO());
    return "player-form";
  }

  @PostMapping("/players")
  public String create(@ModelAttribute PlayerDTO player) {
    playerService.createPlayer(player);
    return "redirect:/players";
  }

  @GetMapping("/players/{id}/edit")
  public String showEditForm(@PathVariable Long id, Model model) {
    PlayerDTO player = playerService.getPlayerById(id);
    model.addAttribute("player", player);
    return "player-form";
  }

  @PostMapping("/players/{id}")
  public String update(@PathVariable Long id, @ModelAttribute PlayerDTO player) {
    playerService.updatePlayer(id, player);
    return "redirect:/players";
  }

  @GetMapping("/players/{id}/delete")
  public String delete(@PathVariable Long id) {
    playerService.deletePlayer(id);
    return "redirect:/players";
  }
}
