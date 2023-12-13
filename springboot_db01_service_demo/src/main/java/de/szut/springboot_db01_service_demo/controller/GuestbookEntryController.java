package de.szut.springboot_db01_service_demo.controller;

import de.szut.springboot_db01_service_demo.mapper.GuestbookEntryMapper;
import de.szut.springboot_db01_service_demo.model.GuestbookEntry;
import de.szut.springboot_db01_service_demo.request.GuestbookEntryRequest;
import de.szut.springboot_db01_service_demo.response.GuestbookEntryResponse;
import de.szut.springboot_db01_service_demo.service.GuestbookEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/guestbook/entries")
public class GuestbookEntryController {

    @Autowired
    private GuestbookEntryService service;

    @Autowired
    private GuestbookEntryMapper mapper;

    public GuestbookEntryController(GuestbookEntryService service, GuestbookEntryMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<GuestbookEntryResponse> addGuestbookEntry(@RequestBody GuestbookEntryRequest request) {
        GuestbookEntry guestbookEntryRequest = mapper.mapRequestToModel(request);
        GuestbookEntry guestbookEntryResponse = service.create(guestbookEntryRequest);
        return new ResponseEntity<>(mapper.mapModelToResponse(guestbookEntryResponse), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GuestbookEntryResponse> getGuestbookEntry(@PathVariable long id) {
        GuestbookEntry guestbookEntryResponse = service.readById(id);
        return new ResponseEntity<>(mapper.mapModelToResponse(guestbookEntryResponse), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<GuestbookEntryResponse>> getAllGuestbookEntries() {
        List<GuestbookEntry> guestbookEntryList = service.readAllByOrderByIdDesc();
        List<GuestbookEntryResponse> responseList = new ArrayList<>();
        for (GuestbookEntry guestbookEntry : guestbookEntryList) {
            responseList.add(mapper.mapModelToResponse(guestbookEntry));
        }
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteGuestbookEntryByUrl(@PathVariable long id) {
        service.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GuestbookEntryResponse> updateGuestbookEntry(
            @PathVariable long id,
            @RequestBody GuestbookEntryRequest request) {
        GuestbookEntry guestbookEntry = mapper.mapRequestToModel(id, request);
        service.update(guestbookEntry);
        GuestbookEntryResponse guestbookEntryResponse = mapper.mapModelToResponse(guestbookEntry);
        return new ResponseEntity<>(guestbookEntryResponse, HttpStatus.OK);
    }
}
