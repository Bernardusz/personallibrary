package io.github.bernardusz.personallibrary.controller;

import io.github.bernardusz.personallibrary.model.Book;
import io.github.bernardusz.personallibrary.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class BookController {
  private final BookRepository bookRepository;

  public BookController(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @GetMapping
  public String listBooks(Model model) {
    model.addAttribute("books", bookRepository.findAll());
    return "index";
  }

  @PostMapping("/add")
  public String addBook(@ModelAttribute Book book) {
    bookRepository.save(book);
    return "redirect:/";
  }

  @PostMapping("/delete/{id}")
  public String deleteBook(@PathVariable int id) {
    bookRepository.delete(id);
    return "redirect:/";
  }

  @GetMapping("/search")
  public String searchBooks(@RequestParam String title, Model model) {
    model.addAttribute("books", bookRepository.findByTitle(title));
    return "index";
  }

  @GetMapping("/edit/{id}")
  public String showEditForm(@PathVariable int id, Model model) {
    model.addAttribute("book", bookRepository.findById(id));
    return "edit";
  }

  @PostMapping("/update")
  public String updateBook(@ModelAttribute Book book) {
    bookRepository.update(book);
    return "redirect:/";
  }
}
