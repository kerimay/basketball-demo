package com.kerimay.basketball.controller.dto;

import com.kerimay.basketball.domain.Player;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Builder
@Data
public class PlayerDTO {

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    private String nationality;

    private int teamId;

    public static PlayerDTO convertToPlayerDTOFromPlayer(Player player) {
        return PlayerDTO.builder()
                .name(player.getName())
                .lastName(player.getLastName())
                .nationality(player.getNationality())
                .teamId(player.getTeam().getId())
                .build();
    }

}
