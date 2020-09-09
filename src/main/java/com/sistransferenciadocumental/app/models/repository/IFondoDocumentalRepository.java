package com.sistransferenciadocumental.app.models.repository;

import java.util.List;
import com.sistransferenciadocumental.app.models.entities.FondoDocumental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFondoDocumentalRepository extends JpaRepository<FondoDocumental, Long> {
   public List<FondoDocumental> findByNumeroExpedienteStartingWith(String numeroExpediente);
}
