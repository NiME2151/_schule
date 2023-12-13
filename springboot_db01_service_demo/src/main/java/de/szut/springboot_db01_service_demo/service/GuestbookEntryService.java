package de.szut.springboot_db01_service_demo.service;

import de.szut.springboot_db01_service_demo.model.GuestbookEntry;
import de.szut.springboot_db01_service_demo.repository.GuestbookEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestbookEntryService {

    @Autowired
    private GuestbookEntryRepository repository;

    public GuestbookEntry create(GuestbookEntry request) {
        return repository.save(request);
    }

    public List<GuestbookEntry> readAll() {
        return repository.findAll();
    }

    public List<GuestbookEntry> readAllByOrderByIdDesc() {
        return repository.findAllByOrderByIdDesc();
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public GuestbookEntry readById(long id) {
        return repository.findById(id).get();
    }

    public GuestbookEntry update(GuestbookEntry guestbookEntry) {
        GuestbookEntry serializedGuestbookEntry = readById (guestbookEntry.getId());
        serializedGuestbookEntry.setTitle(guestbookEntry.getTitle());
        serializedGuestbookEntry.setComment(guestbookEntry.getComment());
        serializedGuestbookEntry.setCommenter(guestbookEntry.getCommenter());
        return repository.save(serializedGuestbookEntry);
    }
}
