package com.notzed.linkedIn.connections_service.repository;

import com.notzed.linkedIn.connections_service.entity.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends Neo4jRepository<Person, Long> {

    Optional<Person> getByName(String name);

    @Query("MATCH (personA:Person) -[:CONNECTED_TO]- (personB:Person) " +
            "WHERE personA.userId = $userId " +
            "RETURN DISTINCT personB")
    List<Person> getFirstDegreeConnections(Long userId);

    @Query("MATCH (personA:Person) -[:CONNECTED_TO]- (personB:Person) -[:CONNECTED_TO]- (personC:Person) " +
            "WHERE personA.userId = $userId AND NOT (personA)- [:CONNECTED_TO] -(personC) " +
            "AND personA <> personCRETURN DISTINCT personC")
    List<Person> getSecondDegreeConnections(Long userId);

    @Query("MATCH (personA:Person) -[:CONNECTED_TO]- (personB:Person)" +
            " -[:CONNECTED_TO]- (personC:Person) -[:CONNECTED_TO] -(personD:Person) " +
            "WHERE personA.userId = $userId" +
            "AND personA <> personD AND NOT (personA)- [:CONNECTED_TO] -(personD)RETURN DISTINCT personD")
    List<Person> getThirdDegreeConnections(Long userId);
}
