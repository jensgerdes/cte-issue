package com.github.jensgerdes.cteissue;


import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("postgres")
class PostgreSQLTest {

    @Autowired
    private DatabaseRepository repository;

    @Test
    void succeedsList() {
        // when
        val result = repository.performRequest(false);

        // then
        assertThat(result)
                .hasSize(7);
    }

    @Test
    void crashesWithCteInlined() {
        repository.performRequest(true);
    }
}
