package com.heycar.demo.repository.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Version;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Data
public class Inventory implements Serializable {

  @EmbeddedId
  private ItemId itemId;

  @Column
  private String maker ;

  @Column
  private String model ;

  @Column
  private String enginePower;

  @Column
  private String modelYear;

  @Column
  private String color ;

  @Column
  private BigDecimal price ;

  // Audit Field
  @CreationTimestamp
  @Column(name = "created_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
  private LocalDateTime createdAt;

  // Audit Field
  @UpdateTimestamp
  @Column(name = "updated_at", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
  private LocalDateTime updatedAt;

  @Version
  private Integer version;

}
