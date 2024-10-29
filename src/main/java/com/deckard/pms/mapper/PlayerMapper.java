package com.deckard.pms.mapper;

import com.deckard.pms.dto.PlayerDTO;
import com.deckard.pms.model.Player;

public class PlayerMapper {

    public static PlayerDTO mapToPlayerDto(Player player) {
        return new PlayerDTO(
                player.getId(),
                player.getFirstName(),
                player.getLastName(),
                player.getClub()
        );
    }
    public static Player mapToPlayer(PlayerDTO playerDTO) {
        return new Player(
          playerDTO.getId(),
                playerDTO.getFirstName(),
                playerDTO.getLastName(),
                playerDTO.getClub()
        );
    }
}
