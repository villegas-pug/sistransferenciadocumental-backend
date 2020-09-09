package com.sistransferenciadocumental.app.models.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sim_usuarios")
@EqualsAndHashCode(of = { "login" })
@NoArgsConstructor
public class Usuario implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "user_login", nullable = false, length = 30)
   private @Getter @Setter String login;

   @Column(name = "user_password", nullable = false)
   private @Getter @Setter String password;

   @Column(name = "user_nombre", nullable = false)
   private @Setter String nombre;

   @Column(name = "user_dni", nullable = false)
   private @Getter @Setter String dni;

   @Column(name = "user_activo", nullable = true)
   private @Getter boolean activo;

   public String getNombre() {
      return nombre.toUpperCase();
   }

   @PrePersist
   private void prePersist() {
      this.activo = true;
   }

}