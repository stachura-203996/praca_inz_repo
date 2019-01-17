package com.stachura.praca_inz.backend.repository;

import com.stachura.praca_inz.backend.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfer,Long> {

}
