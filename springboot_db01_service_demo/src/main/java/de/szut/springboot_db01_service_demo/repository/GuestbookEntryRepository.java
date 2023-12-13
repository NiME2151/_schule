package de.szut.springboot_db01_service_demo.repository;

import de.szut.springboot_db01_service_demo.model.GuestbookEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestbookEntryRepository extends JpaRepository<GuestbookEntry, Long> {

    List<GuestbookEntry> findAllByOrderByIdDesc();
}
