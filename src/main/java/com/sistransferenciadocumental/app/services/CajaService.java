package com.sistransferenciadocumental.app.services;

import java.util.List;
import java.util.Optional;
import com.sistransferenciadocumental.app.models.entities.Caja;
import com.sistransferenciadocumental.app.models.repository.ICajaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CajaService implements IService<Caja, Long> {

   @Autowired
   private ICajaRepository cajaDao;

   @Override
   @Transactional(readOnly = true)
   public Long count() {
      return cajaDao.count();
   }

   @Override
   @Transactional(readOnly = false)
   public Caja save(Caja caja) {
      return cajaDao.save(caja);
   }

   @Override
   @Transactional(readOnly = true)
   public List<Caja> findAll() {
      return cajaDao.findAll();
   }

   @Override
   @Transactional(readOnly = true)
   public Optional<Caja> findById(Long id) {
      return cajaDao.findById(id);
   }

   @Override
   public List<Caja> findAllById(List<Long> ids) {
      return cajaDao.findAllById(ids);
   }

   @Override
   public List<Caja> findByNumeroExpedienteStartingWith(String numeroExpediente) {
      // TODO Auto-generated method stub
      return null;
   }

}