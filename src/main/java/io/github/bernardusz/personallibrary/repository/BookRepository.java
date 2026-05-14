package io.github.bernardusz.personallibrary.repository;

import io.github.bernardusz.personallibrary.model.Book;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {
  private final JdbcClient jdbcClient;

  public  BookRepository(JdbcClient jdbcClient) {
    this.jdbcClient = jdbcClient;
  }

  public List<Book> findAll() {
    return jdbcClient.sql("SELECT * FROM books")
      .query(Book.class).list();
  }

  public List<Book> findByTitle(String title) {
    return jdbcClient.sql("SELECT * FROM books WHERE title ILIKE ?")
      .param("%" + title + "%").query(Book.class).list();
  }

  public Book findById(int id) {
    return jdbcClient.sql("SELECT * FROM books WHERE id = ?")
      .param(id).query(Book.class).single();
  }

  public void save(Book book) {
    jdbcClient.sql("INSERT INTO books (title, author, isbn) VALUES (?, ?, ?)")
      .params(book.title(), book.author(), book.isbn())
      .update();
  }

  public void delete(int id) {
    jdbcClient.sql("DELETE FROM books WHERE id = ?")
      .params(id)
      .update();
  }

  public void update(Book book) {
    jdbcClient.sql("UPDATE books SET title = ?, author = ?, isbn = ? WHERE id = ?")
      .params(book.title(), book.author(), book.isbn(), book.id()).update();
  }

}
