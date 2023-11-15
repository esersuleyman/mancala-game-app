package com.seser.mancalagame.service;

import com.seser.mancalagame.domain.entities.PlayerEntity;
import com.seser.mancalagame.domain.enums.PlayerType;
import com.seser.mancalagame.repository.PlayerRepository;
import com.seser.mancalagame.service.impl.PlayerServiceImpl;
import com.seser.mancalagame.utilities.DataHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImpTest {

    private final DataHelper dataHelper = DataHelper.getInstance();

    @InjectMocks
    private PlayerServiceImpl playerService;
    @Mock
    private PlayerRepository playerRepository;

    @Test
    void GIVEN_username_and_type_WHEN_create_called_THEN_should_create_player_successfully() {
        //GIVEN
        PlayerEntity expected = dataHelper.firstPlayerEntity();
        when(playerRepository.save(any(PlayerEntity.class)))
                .thenReturn(expected);
        //WHEN
        PlayerEntity actual = playerService.create(DataHelper.FIRST_PLAYER, PlayerType.FIRST);
        //THEN
        assertEquals(expected, actual);
    }
}
