package com.notzed.linkedin_App.clients;

import com.notzed.linkedin_App.dto.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "connections-service", path = "/connections")
public interface ConnectionsClient {

    @GetMapping("/core/first-degree")
    List<PersonDto> getFirstConnections();

    @GetMapping("/core/second-degree")
    List<PersonDto> getSecondConnections();

    @GetMapping("/core/third-degree")
    List<PersonDto> getThirdConnections();
}
