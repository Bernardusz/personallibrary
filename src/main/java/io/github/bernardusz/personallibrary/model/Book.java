package io.github.bernardusz.personallibrary.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record Book(
  Integer id,

  @NotBlank(message = "Title is required")
  String title,

  @NotBlank(message = "Author is required")
  String author,

  @NotEmpty(message = "ISBN is required")
  String isbn) {
}