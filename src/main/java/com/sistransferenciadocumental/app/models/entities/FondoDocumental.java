package com.sistransferenciadocumental.app.models.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sim_fondo_documental")
@EqualsAndHashCode(of = { "idFondoDocumental" })
@NoArgsConstructor
public class FondoDocumental implements Serializable {

   @Id
   @Column(name = "id_fondo_documental")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private @Getter Long idFondoDocumental;

   @Column(name = "numero_expediente", nullable = false)
   private @Getter @Setter String numeroExpediente;

   @Column(name = "folio", nullable = true, columnDefinition = "TINYINT", length = 2)
   private @Getter @Setter Integer folio;

   @JsonIgnoreProperties(value = { "fondoDocumental" })
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "id_caja", nullable = false)
   private @Getter @Setter Caja caja;

   @Column(name = "paquete", nullable = true)
   private @Getter @Setter String paquete;

   public FondoDocumental(String numeroExpediente, Integer folio, String paquete) {
      this.numeroExpediente = numeroExpediente;
      this.folio = folio;
      this.paquete = paquete;
   }

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

}