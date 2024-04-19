package com.hendisantika.springbootrestapipostgresql.controller;

import com.hendisantika.springbootrestapipostgresql.entity.Author;
import com.hendisantika.springbootrestapipostgresql.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Collection;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-api-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-01-18
 * Time: 22:07
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/api/author")
public class AuthorRestController {

    private static final Logger logger = LogManager.getLogger(AuthorRestController.class);

    @Autowired
    private AuthorRepository repository;

    @PostMapping
    public ResponseEntity<?> addAuthor(@RequestBody Author author) {
        logger.info("User requested to add author: {}", author);
        Author savedAuthor = repository.save(author);
        logger.info("Author added: {}", savedAuthor);
        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Collection<Author>> getAllAuthor() {
        logger.info("User requested to fetch all authors");
        Collection<Author> authors = repository.findAll();
        logger.info("Fetched {} authors", authors.size());
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorWithId(@PathVariable Long id) {
        logger.info("User requested to fetch author with id: {}", id);
        Optional<Author> author = repository.findById(id);
        if (author.isPresent()) {
            logger.info("Author found: {}", author.get());
            return new ResponseEntity<>(author.get(), HttpStatus.OK);
        } else {
            logger.warn("Author not found with id: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(params = {"name"})
    public ResponseEntity<Collection<Author>> findAuthorWithName(@RequestParam String name) {
        logger.info("User requested to search authors with name: {}", name);
        Collection<Author> authors = repository.findByName(name);
        logger.info("Found {} authors with name: {}", authors.size(), name);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthorFromDB(@PathVariable long id, @RequestBody Author author) {
        logger.info("User requested to update author with id: {}", id);
        Optional<Author> currentAuthorOpt = repository.findById(id);
        if (currentAuthorOpt.isPresent()) {
            Author currentAuthor = currentAuthorOpt.get();
            currentAuthor.setName(author.getName());
            currentAuthor.setIsbn(author.getIsbn());
            currentAuthor.setMbiemer(author.getMbiemer());
            Author updatedAuthor = repository.save(currentAuthor);
            logger.info("Author updated: {}", updatedAuthor);
            return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
        } else {
            logger.warn("Author not found with id: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthorWithId(@PathVariable Long id) {
        logger.info("User requested to delete author with id: {}", id);
        repository.deleteById(id);
        logger.info("Author deleted with id: {}", id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllAuthors() {
        logger.info("User requested to delete all authors");
        repository.deleteAll();
        logger.info("All authors deleted");
        return ResponseEntity.noContent().build();
    }
}