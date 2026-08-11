package com.notzed.linkedIn.connections_service.controller;

import com.notzed.linkedIn.connections_service.entity.Person;
import com.notzed.linkedIn.connections_service.service.ConnectionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class ConnectionsController {

    private final ConnectionsService connectionsService;

    @GetMapping("/{userId}/first-degree")
    public ResponseEntity<List<Person>> getFirstConnections(Long userId){
        return ResponseEntity.ok(connectionsService.getFirstDegreeConnections(userId));
    }

    @GetMapping("/{userId}/second-degree")
    public ResponseEntity<List<Person>> getSecondConnections(Long userId){
        return ResponseEntity.ok(connectionsService.getSecondDegreeConnections(userId));
    }

    @GetMapping("/{userId}/third-degree")
    public ResponseEntity<List<Person>> getThirdConnections(Long userId){
        return ResponseEntity.ok(connectionsService.getThirdDegreeConnections(userId));
    }
}
