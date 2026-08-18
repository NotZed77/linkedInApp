package com.notzed.linkedIn.connections_service.controller;

import com.notzed.linkedIn.connections_service.entity.Person;
import com.notzed.linkedIn.connections_service.service.ConnectionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class ConnectionsController {

    private final ConnectionsService connectionsService;

    @GetMapping("/first-degree")
    public ResponseEntity<List<Person>> getMyFirstConnections(){
        return ResponseEntity.ok(connectionsService.getMyFirstDegreeConnections());
    }

    @GetMapping("/second-degree")
    public ResponseEntity<List<Person>> getMySecondConnections(){
        return ResponseEntity.ok(connectionsService.getMySecondDegreeConnections());
    }

    @GetMapping("/third-degree")
    public ResponseEntity<List<Person>> getMyThirdConnections(){
        return ResponseEntity.ok(connectionsService.getMyThirdDegreeConnections());
    }
}
