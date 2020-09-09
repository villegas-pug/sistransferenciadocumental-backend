package com.sistransferenciadocumental.app.services;

import java.util.List;
import java.util.Optional;
import com.sistransferenciadocumental.app.models.entities.Usuario;
import com.sistransferenciadocumental.app.models.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService implements IService<Usuario, String> {

   @Autowired
   private IUsuarioRepository usuarioDao;

   @Override
   public Optional<Usuario> findById(String id) {
      return usuarioDao.findByLogin(id);
   }

   @Override
   @Transactional(readOnly = true)
   public List<Usuario> findAll() {
      return usuarioDao.findAll();
   }

   @Override
   public Long count() {
      return null;
   }

   @Override
   @Transactional(readOnly = false)
   public Usuario save(Usuario entity) {
      return usuarioDao.save(entity);
   }

   @Override
   public List<Usuario> findAllById(List<String> ids) {
      return null;
   }

   @Override
   public List<Usuario> findByNumeroExpedienteStartingWith(String numeroExpediente) {
      // TODO Auto-generated method stub
      return null;
   }

}