package de.szut.springboot_db02_service_demo.service;

import de.szut.springboot_db02_service_demo.model.Article;
import de.szut.springboot_db02_service_demo.model.Supplier;
import de.szut.springboot_db02_service_demo.repository.SupplierRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private String supplierNotFoundExceptionMessage = "Supplier not found.";
    private String noSupplierFoundExceptionMessage = "No suppliers found.";

    @Autowired
    private final SupplierRepository supplierRepository;

    public Supplier create(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public List<Supplier> readAll() {
        List<Supplier> optionalSupplier = supplierRepository.findAll();
        if (optionalSupplier.isEmpty()) {
            throw new EntityNotFoundException(noSupplierFoundExceptionMessage);
        }
        return optionalSupplier;
    }

    public Supplier readById(long id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isEmpty()) {
            throw new EntityNotFoundException(supplierNotFoundExceptionMessage + " id = " + id);
        }
        return optionalSupplier.get();
    }

    public void delete(long id) {
        if (!supplierRepository.existsById(id)) {
            throw new EntityNotFoundException(supplierNotFoundExceptionMessage + " id = " + id);
        }
        supplierRepository.deleteById(id);
    }

    public Supplier update(Supplier supplier) {
        Supplier toBeUpdated = readById(supplier.getId());
        toBeUpdated.setName(supplier.getName());
        toBeUpdated.setPhone(supplier.getPhone());
        toBeUpdated.setEmail(supplier.getEmail());
        return supplierRepository.save(toBeUpdated);
    }

    public List<Article> readForOneSupplierAllArticles(long id) {
        Supplier supplier = readById(id);
        return supplier.getArticleList();
    }
}
