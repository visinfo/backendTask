package com.heycar.demo.config;

import com.heycar.demo.domain.service.InventoryRepository;
import com.heycar.demo.domain.service.InventoryService;
import com.heycar.demo.domain.service.InventoryServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public InventoryService getInventoryService(InventoryRepository repository){

    return  new InventoryServiceImpl(repository);
  }


}
