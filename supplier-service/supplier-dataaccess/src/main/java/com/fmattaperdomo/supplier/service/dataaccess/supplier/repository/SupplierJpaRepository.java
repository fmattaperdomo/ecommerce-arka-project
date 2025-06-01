package com.fmattaperdomo.supplier.service.dataaccess.supplier.repository;

import com.fmattaperdomo.supplier.service.dataaccess.supplier.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SupplierJpaRepository extends JpaRepository<SupplierEntity, UUID> {


}
