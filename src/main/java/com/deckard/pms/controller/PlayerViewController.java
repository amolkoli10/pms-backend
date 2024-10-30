package com.deckard.pms.controller;

import com.deckard.pms.dto.PlayerDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/players")
public class PlayerViewController {

  private final RestTemplate restTemplate;

  // Base URL for your REST API
  private static final String API_URL = "http://localhost:8080/api/players";

  public PlayerViewController(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @GetMapping
  public String listPlayers(Model model) {
    List<PlayerDTO> players =
        new ArrayList<>(
            List.of(Objects.requireNonNull(restTemplate.getForObject(API_URL, PlayerDTO[].class))));
    if (players.isEmpty()) {
      PlayerDTO defaultPlayer = new PlayerDTO();
      defaultPlayer.setId(0L);
      defaultPlayer.setFirstName("Deckard");
      defaultPlayer.setLastName("Shaw");
      defaultPlayer.setClub("Fulham");
      players.add(defaultPlayer);
    }
    model.addAttribute("players", players);
    return "players"; // Thymeleaf template name
  }

  @GetMapping("/new")
  public String showCreateForm(Model model) {
    model.addAttribute("player", new PlayerDTO());
    return "player-form"; // Thymeleaf template name for form
  }

  @PostMapping
  public String createPlayer(@ModelAttribute PlayerDTO player) {
    restTemplate.postForObject(API_URL, player, PlayerDTO.class);
    return "redirect:/players"; // Redirect to list
  }

  @GetMapping("/{id}/edit")
  public String showEditForm(@PathVariable Long id, Model model) {
    PlayerDTO player = restTemplate.getForObject(API_URL + "/" + id, PlayerDTO.class);
    model.addAttribute("player", player);
    return "player-form"; // Thymeleaf template name for form
  }

  @PostMapping("/{id}")
  public String updatePlayer(@PathVariable Long id, @ModelAttribute PlayerDTO player) {
    restTemplate.put(API_URL + "/" + id, player);
    return "redirect:/players"; // Redirect to list
  }

  @GetMapping("/{id}/delete")
  public String deletePlayer(@PathVariable Long id) {
    restTemplate.delete(API_URL + "/" + id);
    return "redirect:/players"; // Redirect to list
  }
}
