package com.deckard.pms.service;

import com.deckard.pms.dto.PlayerDTO;
import java.util.List;

public interface PlayerService {
  PlayerDTO createPlayer(PlayerDTO playerDTO);

  PlayerDTO getPlayerById(Long playerId);

  List<PlayerDTO> getAllPlayers();

  PlayerDTO updatePlayer(Long playerId, PlayerDTO playerDTO);

  void deletePlayer(Long playerId);
}
