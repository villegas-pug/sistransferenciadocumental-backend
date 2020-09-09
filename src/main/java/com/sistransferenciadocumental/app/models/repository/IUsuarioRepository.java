package com.sistransferenciadocumental.app.models.repository;

import java.util.Optional;
import com.sistransferenciadocumental.app.models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, String> {
   Optional<Usuario> findByLogin(String login);
}