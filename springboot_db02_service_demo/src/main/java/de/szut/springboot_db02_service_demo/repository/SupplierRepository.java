package de.szut.springboot_db02_service_demo.repository;

import de.szut.springboot_db02_service_demo.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
