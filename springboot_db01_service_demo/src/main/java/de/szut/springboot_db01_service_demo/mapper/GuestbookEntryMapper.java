package de.szut.springboot_db01_service_demo.mapper;

import de.szut.springboot_db01_service_demo.model.GuestbookEntry;
import de.szut.springboot_db01_service_demo.request.GuestbookEntryRequest;
import de.szut.springboot_db01_service_demo.response.GuestbookEntryResponse;
import org.springframework.stereotype.Service;

@Service
public class GuestbookEntryMapper {

    public GuestbookEntry mapRequestToModel(GuestbookEntryRequest request) {
        return mapRequestWithoutIdToModel(request);
    }

    public GuestbookEntry mapRequestToModel(long id, GuestbookEntryRequest request) {
        GuestbookEntry model = mapRequestWithoutIdToModel(request);
        model.setId(id);
        return model;
    }

    private GuestbookEntry mapRequestWithoutIdToModel(GuestbookEntryRequest request) {
        GuestbookEntry model = new GuestbookEntry();
        model.setTitle(request.getTitle());
        model.setComment(request.getComment());
        model.setCommenter(request.getCommenter());
        return model;
    }

    public GuestbookEntryResponse mapModelToResponse(GuestbookEntry model) {
        GuestbookEntryResponse response = new GuestbookEntryResponse();
        response.setTitle(model.getTitle());
        response.setComment(model.getComment());
        response.setCommenter(model.getCommenter());
        response.setDate(model.getDate());
        return response;
    }
}
