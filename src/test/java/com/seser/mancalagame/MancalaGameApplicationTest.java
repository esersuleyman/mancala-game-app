package com.seser.mancalagame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
class MancalaGameApplicationTest {

    @Test
    void GIVEN_spring_boot_application_class_WHEN_main_called_THEN_should_start_successfully() throws SecurityException {
        MancalaGameApplication.main(new String[] {});
    }
}
