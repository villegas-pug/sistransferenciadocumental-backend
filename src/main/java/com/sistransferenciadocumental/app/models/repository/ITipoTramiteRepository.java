package com.sistransferenciadocumental.app.models.repository;

import com.sistransferenciadocumental.app.models.entities.TipoTramite;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ITipoTramiteRepository extends JpaRepository<TipoTramite, Long> {

}