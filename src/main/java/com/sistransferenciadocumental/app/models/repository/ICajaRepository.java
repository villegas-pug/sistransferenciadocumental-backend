package com.sistransferenciadocumental.app.models.repository;

import com.sistransferenciadocumental.app.models.entities.Caja;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICajaRepository extends JpaRepository<Caja, Long> {

}