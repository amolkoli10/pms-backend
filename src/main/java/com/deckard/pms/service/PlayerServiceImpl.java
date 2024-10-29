package com.deckard.pms.service;

import com.deckard.pms.dto.PlayerDTO;
import com.deckard.pms.exception.ResourceNotFoundException;
import com.deckard.pms.mapper.PlayerMapper;
import com.deckard.pms.model.Player;
import com.deckard.pms.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService{

    private PlayerRepository playerRepository;

    @Override
    public PlayerDTO createPlayer(PlayerDTO playerDTO) {
        Player player = PlayerMapper.mapToPlayer(playerDTO);
        Player savedPlayer = playerRepository.save(player);
        return PlayerMapper.mapToPlayerDto(savedPlayer);
    }

    @Override
    public PlayerDTO getPlayerById(Long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found with id" + playerId));
        return PlayerMapper.mapToPlayerDto(player);
    }

    @Override
    public List<PlayerDTO> getAllPlayers() {
        List<Player> playerList = playerRepository.findAll();
        return playerList.stream().map(PlayerMapper::mapToPlayerDto)
                .collect(Collectors.toList());
    }

    @Override
    public PlayerDTO updatePlayer(Long playerId, PlayerDTO updatedPlayerDTO) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found with id" + playerId));
        player.setFirstName(updatedPlayerDTO.getFirstName());
        player.setLastName(updatedPlayerDTO.getLastName());
        player.setClub(updatedPlayerDTO.getClub());
        Player updatedPlayer = playerRepository.save(player);
        return PlayerMapper.mapToPlayerDto(updatedPlayer);
    }

    @Override
    public void deletePlayer(Long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found with id" + playerId));
        playerRepository.deleteById(playerId);
    }
}
