package com.heycar.demo.it;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@SpringBootTest
@AutoConfigureMockMvc
public class ListingControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public  void createListing_CheckIfListingCreatedSuccessfully() throws Exception {
    String data = "[ { \"code\": \"red\", \"color\": \"red\", \"enginePower\": \"182\", \"maker\": \"toyota\", \"model\": \"abs\", \"modelYear\": \"2014\", \"price\": 1289 } ]";
    mockMvc.perform(post("/api/1.0//vehicles/1212/listings").content(data).contentType("application/json")).andExpect(status()  .isOk());
  }

  @Test
  public void uploadListing_CheckIfListingUploaded() throws Exception {
  String  data ="code,make/model,power-in-ps,year,color,price\n"
      + "1,mercedes/a180,123,2014,black,15950\n"
      + "2,audi/a3,111,2016,white,17210\n"
      + "3,vw/golf,86,2018,green,14980\n"
      + "4,skoda/octavia,86,2018,blue,16990";

    MockMultipartFile csvFile = new MockMultipartFile(
        "listing.json", "", "multipart/form-data", data.toString().getBytes());

    mockMvc.perform(MockMvcRequestBuilders.multipart("/api/1.0/upload_csv/12121/listings")
        .file("file", csvFile.getBytes())
        .characterEncoding("UTF-8"))
        .andExpect(status().isOk());

  }

}
