package de.lubowiecki.books;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookApiControllerTest {

    @Autowired
    MockMvc mock;

    @Test
    public void findAll() throws Exception {
        mock.perform(get("/api/v1/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", Matchers.is("Hooobbit")));
    }

    @Test
    public void findOne() throws Exception {
        mock.perform(get("/api/v1/books/52"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", Matchers.is("Hobb...it")));
    }
}
