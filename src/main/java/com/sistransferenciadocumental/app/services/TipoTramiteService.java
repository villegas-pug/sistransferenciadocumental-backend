package com.sistransferenciadocumental.app.services;

import java.util.List;
import java.util.Optional;
import com.sistransferenciadocumental.app.models.entities.TipoTramite;
import com.sistransferenciadocumental.app.models.repository.ITipoTramiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TipoTramiteService implements IService<TipoTramite, Long> {

   @Autowired
   private ITipoTramiteRepository tipoTramiteDao;

   @Override
   @Transactional(readOnly = true)
   public List<TipoTramite> findAll() {
      return tipoTramiteDao.findAll();
   }

   @Override
   @Transactional(readOnly = true)
   public Optional<TipoTramite> findById(Long nIdTipoTramite) {
      return tipoTramiteDao.findById(nIdTipoTramite);
   }

   @Override
   public Long count() {
      return null;
   }

   @Override
   public TipoTramite save(TipoTramite entity) {
      return null;
   }

   @Override
   public List<TipoTramite> findAllById(List<Long> ids) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public List<TipoTramite> findByNumeroExpedienteStartingWith(String numeroExpediente) {
      // TODO Auto-generated method stub
      return null;
   }

}