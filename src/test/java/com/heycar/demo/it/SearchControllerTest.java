package com.heycar.demo.it;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class SearchControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public  void search_CheckIfListingReturnedSuccessfully() throws Exception {
    String data = "[ { \"code\": \"abs\", \"color\": \"red\", \"enginePower\": \"182\", \"maker\": \"toyota\", \"model\": \"abs\", \"modelYear\": \"2014\", \"price\": 1289 } ]";
    mockMvc.perform(post("/api/1.0//vehicles/1212/listings").content(data).contentType("application/json")).andExpect(status()  .isOk());

    mockMvc.perform(get("/api/1.0/search?color=red"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[*].color").value("red"));

  }
}
