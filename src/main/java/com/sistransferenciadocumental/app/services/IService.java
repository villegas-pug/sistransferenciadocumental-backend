package com.sistransferenciadocumental.app.services;

import java.util.List;
import java.util.Optional;

public interface IService<E, ID> {

   Long count();

   List<E> findAll();

   Optional<E> findById(ID id);

   List<E> findAllById(List<ID> ids);

   E save(E entity);

   List<E> findByNumeroExpedienteStartingWith(String numeroExpediente);

}