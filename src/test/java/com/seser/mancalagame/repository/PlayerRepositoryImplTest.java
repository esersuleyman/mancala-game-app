package com.seser.mancalagame.repository;

import com.seser.mancalagame.domain.entities.PlayerEntity;
import com.seser.mancalagame.utilities.DataHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@DataMongoTest
class PlayerRepositoryImplTest {

    private final DataHelper dataHelper = DataHelper.getInstance();

    @Autowired
    private PlayerRepository playerRepositoryImpl;

    @Test
    void GIVEN_player_entity_WHEN_save_called_THEN_should_save_successfully() {
        //GIVEN
        PlayerEntity expected = dataHelper.firstPlayerEntity();
        //WHEN
        PlayerEntity actual = playerRepositoryImpl.save(expected);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void GIVEN_uuid_WHEN_findByUuid_called_THEN_should_find_successfully() {
        //GIVEN
        PlayerEntity expected = dataHelper.firstPlayerEntity();
        playerRepositoryImpl.save(expected);
        //WHEN
        PlayerEntity actual = playerRepositoryImpl.findByUuid(expected.getUuid());
        //THEN
        assertEquals(expected, actual);
    }
}
