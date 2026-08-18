package com.notzed.linkedIn.connections_service.service;

import com.notzed.linkedIn.connections_service.auth.UserContextHolder;
import com.notzed.linkedIn.connections_service.entity.Person;
import com.notzed.linkedIn.connections_service.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConnectionsService {

    private final PersonRepository personRepository;

    // TODO Use UserContextHolder for AOP Implementation and get the user ID to call the DB and
    //  get the roles of the user and its permissions

    public List<Person> getMyFirstDegreeConnections(){
        Long userId = UserContextHolder.getCurrentUserId();
        log.info("Getting first degree connections for user with id: {}", userId);

        return personRepository.getFirstDegreeConnections(userId);
    }

    public List<Person> getMySecondDegreeConnections(){
        Long userId = UserContextHolder.getCurrentUserId();
        log.info("Getting second degree connections for user with id: {}", userId);

        return personRepository.getSecondDegreeConnections(userId);
    }

    public List<Person> getMyThirdDegreeConnections(){
        Long userId = UserContextHolder.getCurrentUserId();
        log.info("Getting third degree connections for user with id: {}", userId);

        return personRepository.getThirdDegreeConnections(userId);
    }
}
