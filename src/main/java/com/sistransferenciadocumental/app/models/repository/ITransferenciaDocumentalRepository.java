package com.sistransferenciadocumental.app.models.repository;

import com.sistransferenciadocumental.app.models.entities.TransferenciaDocumental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransferenciaDocumentalRepository extends JpaRepository<TransferenciaDocumental, Long> {
}