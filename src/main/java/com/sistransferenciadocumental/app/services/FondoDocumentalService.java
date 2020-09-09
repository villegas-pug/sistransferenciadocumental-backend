package com.sistransferenciadocumental.app.services;

import java.util.List;
import java.util.Optional;
import com.sistransferenciadocumental.app.models.entities.FondoDocumental;
import com.sistransferenciadocumental.app.models.repository.IFondoDocumentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FondoDocumentalService implements IService<FondoDocumental, Long> {

   @Autowired
   private IFondoDocumentalRepository repository;

   @Override
   public Long count() {
      return null;
   }

   @Override
   public List<FondoDocumental> findAll() {
      return null;
   }

   @Override
   public Optional<FondoDocumental> findById(Long id) {
      return null;
   }

   @Override
   public List<FondoDocumental> findAllById(List<Long> ids) {
      return null;
   }

   @Override
   public FondoDocumental save(FondoDocumental entity) {
      return null;
   }

   @Override
   public List<FondoDocumental> findByNumeroExpedienteStartingWith(String numeroExpediente) {
      return repository.findByNumeroExpedienteStartingWith(numeroExpediente);
   }

}
