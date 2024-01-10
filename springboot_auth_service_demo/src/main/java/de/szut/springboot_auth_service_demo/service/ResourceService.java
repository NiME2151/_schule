package de.szut.springboot_auth_service_demo.service;

import de.szut.springboot_auth_service_demo.model.Resource;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {

    public Resource getDummyResource() {
        return new Resource("Eine Ressource");
    }
}
