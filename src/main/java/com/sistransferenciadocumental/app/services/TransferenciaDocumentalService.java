package com.sistransferenciadocumental.app.services;

import java.util.List;
import java.util.Optional;
import com.sistransferenciadocumental.app.models.entities.TransferenciaDocumental;
import com.sistransferenciadocumental.app.models.repository.ITransferenciaDocumentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferenciaDocumentalService implements IService<TransferenciaDocumental, Long> {

   @Autowired
   private ITransferenciaDocumentalRepository transferenciaDocumentalDao;

   @Override
   @Transactional(readOnly = true)
   public Long count() {
      return null;
   }

   @Override
   @Transactional(readOnly = true)
   public List<TransferenciaDocumental> findAll() {
      return transferenciaDocumentalDao.findAll();
   }

   @Override
   @Transactional(readOnly = true)
   public Optional<TransferenciaDocumental> findById(Long id) {
      return transferenciaDocumentalDao.findById(id);
   }

   @Override
   @Transactional(readOnly = false)
   public TransferenciaDocumental save(TransferenciaDocumental entity) {
      return transferenciaDocumentalDao.save(entity);
   }

   @Override
   public List<TransferenciaDocumental> findAllById(List<Long> ids) {
      return null;
   }

   @Override
   public List<TransferenciaDocumental> findByNumeroExpedienteStartingWith(String numeroExpediente) {
      // TODO Auto-generated method stub
      return null;
   }

}