package com.chtrembl.petstore.pet.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "category")
public class Category {
    @Id
    private Long id;
    @Column(name = "name", nullable = false, unique = true, length = 64)
    private String name;

  public Category name(String name) {
    this.name = name;
    return this;
  }
}