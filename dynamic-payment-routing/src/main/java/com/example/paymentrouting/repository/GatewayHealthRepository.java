package com.example.paymentrouting.repository;

import com.example.paymentrouting.domain.GatewayHealth;
import com.example.paymentrouting.domain.GatewayName;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GatewayHealthRepository extends JpaRepository<GatewayHealth, GatewayName> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select g from GatewayHealth g where g.gateway = :gateway")
    Optional<GatewayHealth> findByGatewayForUpdate(@Param("gateway") GatewayName gateway);
}
