package de.szut.springboot_db02_service_demo.controller;

import de.szut.springboot_db02_service_demo.dto.ArticleResponse;
import de.szut.springboot_db02_service_demo.dto.SupplierRequest;
import de.szut.springboot_db02_service_demo.dto.SupplierResponse;
import de.szut.springboot_db02_service_demo.mapper.ArticleMapper;
import de.szut.springboot_db02_service_demo.mapper.SupplierMapper;
import de.szut.springboot_db02_service_demo.model.Article;
import de.szut.springboot_db02_service_demo.model.Supplier;
import de.szut.springboot_db02_service_demo.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    @Autowired
    private final SupplierService supplierService;
    @Autowired
    private final SupplierMapper supplierMapper;
    @Autowired
    private final ArticleMapper articleMapper;

    @PostMapping
    public SupplierResponse addSupplier(@Valid @RequestBody SupplierRequest supplierRequest) {
        Supplier supplier = supplierMapper.mapRequestToModel(supplierRequest);
        Supplier supplierResponse = supplierService.create(supplier);
        return supplierMapper.mapModelToResponse(supplierResponse);
    }

    @GetMapping
    public List<SupplierResponse> getAllSuppliers() {
        List<Supplier> suppliers = supplierService.readAll();
        return supplierMapper.mapAllModelsToResponses(suppliers);
    }

    @GetMapping("/{id}")
    public SupplierResponse getSupplier(@PathVariable long id) {
        Supplier supplier = supplierService.readById(id);
        return supplierMapper.mapModelToResponse(supplier);
    }

    @DeleteMapping("/{id}")
    public void deleteSupplier(@PathVariable long id) {
        supplierService.delete(id);
    }

    @PutMapping("/{id}")
    public SupplierResponse updateSupplier(@PathVariable long id, @Valid @RequestBody SupplierRequest supplierRequest) {
        Supplier supplier = supplierMapper.mapRequestToModel(id, supplierRequest);
        Supplier supplierResponse = supplierService.update(supplier);
        return supplierMapper.mapModelToResponse(supplierResponse);
    }

    @GetMapping("/{id}/articles")
    public List<ArticleResponse> getForOneSupplierAllArticles(@PathVariable long id) {
        List<Article> articles = supplierService.readForOneSupplierAllArticles(id);
        return articleMapper.mapAllModelsToResponses(articles);
    }
}
