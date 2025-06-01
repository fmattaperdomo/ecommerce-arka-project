package com.fmattaperdomo.supplier.service.application.rest;

import com.fmattaperdomo.supplier.service.domain.create.CreateSupplierCommand;
import com.fmattaperdomo.supplier.service.domain.create.CreateSupplierResponse;
import com.fmattaperdomo.supplier.service.domain.ports.input.service.SupplierApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/suppliers", produces = "application/vnd.api.v1+json")
public class SupplierController {

    private final SupplierApplicationService supplierApplicationService;

    public SupplierController(SupplierApplicationService supplierApplicationService) {
        this.supplierApplicationService = supplierApplicationService;
    }

    @PostMapping
    public ResponseEntity<CreateSupplierResponse> createSupplier(@RequestBody CreateSupplierCommand
                                                                         createSupplierCommand) {
        log.info("Creating supplier with email: {}", createSupplierCommand.getEmail());
        CreateSupplierResponse response = supplierApplicationService.createSupplier(createSupplierCommand);
        return ResponseEntity.ok(response);
    }

}