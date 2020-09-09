package com.sistransferenciadocumental.app.models.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sim_cajas")
@EqualsAndHashCode(of = { "idCaja" })
public class Caja implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "id_caja")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private @Getter Long idCaja;

   @JsonIgnoreProperties(value = { "cajas" })
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "id_transferencia", nullable = true)
   private @Getter @Setter TransferenciaDocumental transferencia;

   @ManyToOne
   @JoinColumn(name = "id_operador", nullable = true)
   private @Getter @Setter Usuario operador;

   @ManyToOne
   @JoinColumn(name = "id_evaluador", nullable = true)
   private @Getter @Setter Usuario evaluador;

   @Column(name = "fecha_caja", nullable = false)
   @Temporal(TemporalType.DATE)
   private @Getter Date fechaCaja;

   @Column(name = "estado_caja", nullable = false, columnDefinition = "TINYINT", length = 1)
   private @Getter boolean estadoCaja;

   @JsonIgnoreProperties(value = { "caja" }, allowSetters = true)
   @OneToMany(mappedBy = "caja", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private @Getter List<FondoDocumental> fondoDocumental = new ArrayList<>();

   @JsonIgnoreProperties(value = { "cajas" }, allowSetters = true)
   @ManyToOne
   @JoinColumn(name = "id_tipo_tramite", nullable = false)
   private @Getter @Setter TipoTramite tipoTramite;

   @PrePersist
   private void prePersist() {
      this.fechaCaja = new Date();
      this.estadoCaja = true;
   }

   public void setFondoDocumental(List<FondoDocumental> lstFondoDocumental) {
      lstFondoDocumental.forEach(this::addFondoDocumental);
   }

   public void addFondoDocumental(FondoDocumental fondoDocumental) {
      this.fondoDocumental.add(fondoDocumental);
      fondoDocumental.setCaja(this);
   }

   public void removeAllFondoDocumental() {
      this.fondoDocumental.clear();
   }

}
