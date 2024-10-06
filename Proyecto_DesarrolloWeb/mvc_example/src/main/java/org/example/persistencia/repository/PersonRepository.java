package org.example.persistencia.repository;

import java.util.List;

import org.example.persistencia.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// @Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    // https://www.baeldung.com/spring-data-derived-queries
    List<Person> findAllByLastName(String text);

    List<Person> findAllByLastNameStartingWith(String text);

    List<Person> findAllByLastNameStartingWithIgnoreCase(String text);

    // https://www.baeldung.com/spring-data-jpa-query
    @Query("SELECT p FROM Person p WHERE p.lastName LIKE concat(:text, '%')")
    List<Person> findPersonsByLastNameStartingWith(@Param("text") String text);

    @Query("SELECT p FROM Person p WHERE LOWER(p.lastName) LIKE LOWER(concat(:text,'%'))")
    List<Person> findPersonsByLastNameStartingWithCaseInsensitive(@Param("text") String text);

}
