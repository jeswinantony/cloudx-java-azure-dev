package com.chtrembl.petstore.pet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tag")
public class Tag {
    @Id
    private Long id;
    @Column(name = "name", nullable = false, unique = true, length = 64)
    private String name;

  public Tag name(String name) {
    this.name = name;
    return this;
  }
}