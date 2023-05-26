package de.ckle.shoppinglist.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("classpath:setup-testdata.sql")
@Transactional
public class EntriesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testEntriesGetIsAvailable() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/shoppinglist/entries"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testEntriesGetIsAvailableAndReturnsData() throws Exception {
        mockMvc.perform(get("/shoppinglist/entries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$[0].title", is("Butter")))
                .andExpect(jsonPath("$[0].amount", is(2)))
                .andExpect(jsonPath("$[0].category", is("dairy")))

                .andExpect(jsonPath("$[1].title", is("Potatoes")))
                .andExpect(jsonPath("$[1].amount", is(5)))
                .andExpect(jsonPath("$[1].category", is("vegetables")));
    }
}
