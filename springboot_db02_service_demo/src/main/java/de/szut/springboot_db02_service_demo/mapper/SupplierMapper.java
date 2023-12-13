package de.szut.springboot_db02_service_demo.mapper;

import de.szut.springboot_db02_service_demo.dto.SupplierRequest;
import de.szut.springboot_db02_service_demo.dto.SupplierResponse;
import de.szut.springboot_db02_service_demo.model.Supplier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SupplierMapper {

    public Supplier mapRequestToModel(SupplierRequest supplierRequest) {
        Supplier supplier = new Supplier();
        supplier.setName(supplierRequest.getName());
        supplier.setPhone(supplierRequest.getPhone());
        supplier.setEmail(supplierRequest.getEmail());
        return supplier;
    }

    public Supplier mapRequestToModel(long id, SupplierRequest supplierRequest) {
        Supplier supplier = new Supplier();
        supplier.setId(id);
        supplier.setName(supplierRequest.getName());
        supplier.setPhone(supplierRequest.getPhone());
        supplier.setEmail(supplierRequest.getEmail());
        return supplier;
    }

    public SupplierResponse mapModelToResponse(Supplier supplier) {
        SupplierResponse response = new SupplierResponse();
        response.setId(supplier.getId());
        response.setName(supplier.getName());
        response.setPhone(supplier.getPhone());
        response.setEmail(supplier.getEmail());
        return response;
    }

    public List<SupplierResponse> mapAllModelsToResponses(List<Supplier> suppliers) {
        List<SupplierResponse> response = new ArrayList<>();
        for (Supplier supplier : suppliers ) {
            SupplierResponse toBeAdded = new SupplierResponse();
            toBeAdded.setId(supplier.getId());
            toBeAdded.setName(supplier.getName());
            toBeAdded.setPhone(supplier.getPhone());
            toBeAdded.setEmail(supplier.getEmail());
            response.add(toBeAdded);
        }
        return response;
    }

}
