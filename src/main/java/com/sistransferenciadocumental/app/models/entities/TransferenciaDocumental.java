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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sim_transferencia_documental")
@EqualsAndHashCode(of = { "idTransferencia" })
@NoArgsConstructor
public class TransferenciaDocumental implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id_transferencia")
   private @Getter @Setter Long idTransferencia;

   @Column(name = "id_estado_transferencia")
   private @Getter @Setter Long idEstadoTransferencia;

   @Column(name = "fecha_transferencia")
   @Temporal(TemporalType.DATE)
   private @Getter Date fechaTransferencia;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "id_encargado_transferencia", nullable = true)
   private @Getter @Setter Usuario encargadoTransferencia;

   @JsonIgnore
   @Lob
   @Column(nullable = true)
   private @Getter @Setter byte[] anexo;

   @JsonIgnoreProperties(value = { "transferencia" }, allowSetters = true)
   @OneToMany(mappedBy = "transferencia", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private @Getter List<Caja> cajas = new ArrayList<>();

   @PrePersist
   public void prePersist() {
      this.fechaTransferencia = new Date();
   }

   public void setCajas(List<Caja> cajas) {
      cajas.forEach(this::addCaja);
   }

   public void addCaja(Caja caja) {
      cajas.add(caja);
      caja.setTransferencia(this);
   }

   public void removeCaja(Caja caja) {
      caja.setTransferencia(null);
   }

   public void removeAllCajas(List<Caja> cajas) {
      cajas.forEach(caja -> caja.setTransferencia(null));
   }

   /**
   *
   */
   private static final long serialVersionUID = 1L;

}