package de.szut.springboot_auth_service_demo.controller;

import de.szut.springboot_auth_service_demo.converter.ResourceConverter;
import de.szut.springboot_auth_service_demo.dto.ResourceResponse;
import de.szut.springboot_auth_service_demo.service.ResourceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/resources")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ResourceConverter resourceConverter;

    @GetMapping
    public ResponseEntity<ResourceResponse> getResource() {
        ResourceResponse response = resourceConverter.convertModelToResponse(resourceService.getDummyResource());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
