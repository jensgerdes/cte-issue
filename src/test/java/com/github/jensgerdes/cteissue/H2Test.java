package com.github.jensgerdes.cteissue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("h2")
class H2Test {

    @Autowired
    private DatabaseRepository repository;

    @Test
    void crashesWithCte() {
        repository.performRequest(false);
    }

    @Test
    void crashesWithCteInlined() {
        repository.performRequest(true);
    }
}
