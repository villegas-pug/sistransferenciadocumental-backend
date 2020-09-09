package com.sistransferenciadocumental.app.models.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sim_tipo_tramite")
public class TipoTramite implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id_tipo_tramite", nullable = false)
   private @Getter Long idTipoTramite;

   @Column(name = "descripcion", nullable = false)
   private @Setter String descripcion;

   @Column(name = "sigla", nullable = false)
   private @Getter @Setter String sigla;

   @Column(name = "activo", nullable = false)
   private @Getter String activo;

   public String getDescripcion() {
      return this.descripcion.toUpperCase();
   }

}