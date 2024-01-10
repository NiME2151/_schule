package de.szut.springboot_auth_service_demo.converter;

import de.szut.springboot_auth_service_demo.dto.ResourceResponse;
import de.szut.springboot_auth_service_demo.model.Resource;
import org.springframework.stereotype.Service;

@Service
public class ResourceConverter {

    public ResourceResponse convertModelToResponse(Resource resource) {
        ResourceResponse response = new ResourceResponse();
        response.setMessage(resource.getText());
        return response;
    }
}
