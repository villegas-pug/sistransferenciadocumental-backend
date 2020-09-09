package com.sistransferenciadocumental.app.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.sistransferenciadocumental.app.constants.Messages;
import com.sistransferenciadocumental.app.errors.DataAccessEmptyWarning;
import com.sistransferenciadocumental.app.errors.EntityFindByIdWarning;
import com.sistransferenciadocumental.app.models.entities.Caja;
import com.sistransferenciadocumental.app.models.entities.TransferenciaDocumental;
import com.sistransferenciadocumental.app.services.IService;
import com.sistransferenciadocumental.app.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/transferencia-documental")
public class TransferenciaDocumentalController {

   @Autowired
   private IService<TransferenciaDocumental, Long> transferenciaService;

   @Autowired
   private IService<Caja, Long> cajaService;

   @PostMapping(path = "/transferir-cajas", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
   public ResponseEntity<?> transferir(@RequestPart(required = false) MultipartFile anexo,
         @RequestPart TransferenciaDocumental transferencia) throws IOException {

      TransferenciaDocumental transferenciaDocumentalDb = new TransferenciaDocumental();
      transferenciaDocumentalDb.setIdEstadoTransferencia(1L);
      transferenciaDocumentalDb.setEncargadoTransferencia(transferencia.getEncargadoTransferencia());
      /*-> Validación... */
      if (anexo != null)
         transferenciaDocumentalDb.setAnexo(anexo.getBytes());

      List<Caja> cajasDb = cajaService
            .findAllById(transferencia.getCajas().stream().map(caja -> caja.getIdCaja()).collect(Collectors.toList()));
      transferenciaDocumentalDb.setCajas(cajasDb);
      transferenciaService.save(transferenciaDocumentalDb);
      return ResponseEntity.ok().body(Response.builder().message(Messages.GET_MESSAGE_SUCCESS_CREATE())
            .data(transferenciaService.findAll()).build());
   }

   @PutMapping(path = "/actualizar")
   public ResponseEntity<?> actualizar(@RequestPart(required = false) MultipartFile anexo,
         @RequestPart TransferenciaDocumental transferencia) throws IOException {

      Optional<TransferenciaDocumental> transferenciaDb = transferenciaService
            .findById(transferencia.getIdTransferencia());

      if (!transferenciaDb.isPresent())
         throw new EntityFindByIdWarning(transferencia.getIdTransferencia().toString());

      /*-> Validación: Suministramos el anexo... */
      if (anexo != null)
         transferenciaDb.get().setAnexo(anexo.getBytes());

      /*-> Reset transferencia... */
      transferenciaDb.get().removeAllCajas(transferenciaDb.get().getCajas());
      /*-> Update transferencia */
      transferenciaDb.get().setCajas(cajaService
            .findAllById(transferencia.getCajas().stream().map(caja -> caja.getIdCaja()).collect(Collectors.toList())));
      /*-> Confirmar... */
      transferenciaService.save(transferenciaDb.get());

      return ResponseEntity.ok().body(Response.builder().message(Messages.GET_MESSAGE_SUCCESS_UPDATE())
            .data(transferenciaService.findAll()).build());
   }

   @DeleteMapping(path = "/eliminar-cajas")
   public ResponseEntity<?> eliminarCajas(@RequestBody TransferenciaDocumental transferencia) {
      transferencia.getCajas().forEach(caja -> {
         Optional<Caja> cajaDb = cajaService.findById(caja.getIdCaja());
         if (cajaDb.isEmpty())/*-> Validación... */
            throw new DataAccessEmptyWarning();

         Optional<TransferenciaDocumental> transferenciaDocumentalDb = transferenciaService
               .findById(cajaDb.get().getTransferencia().getIdTransferencia());
         if (!transferenciaDocumentalDb.isPresent())/*-> Validación... */
            throw new DataAccessEmptyWarning();

         transferenciaDocumentalDb.get().removeCaja(cajaDb.get());
         transferenciaService.save(transferenciaDocumentalDb.get());
      });

      return ResponseEntity.ok().body(Response.builder().message(Messages.GET_MESSAGE_SUCCESS_UPDATE())
            .data(transferenciaService.findAll()).build());
   }

   @GetMapping(path = "/listar")
   public ResponseEntity<?> listar() {
      List<TransferenciaDocumental> transferenciaDb = transferenciaService.findAll();
      if (transferenciaDb.isEmpty())
         throw new DataAccessEmptyWarning();
      return ResponseEntity.ok()
            .body(Response.builder().message(Messages.GET_MESSAGE_SUCCESS_LIST_ENTITY()).data(transferenciaDb).build());
   }

   @GetMapping(path = "/downloadAnexo/{idTransferencia}")
   public ResponseEntity<?> downloadDoc(@PathVariable(name = "idTransferencia") Long id) {
      Optional<TransferenciaDocumental> transferencia = transferenciaService.findById(id);
      if (transferencia.isEmpty() || transferencia.get().getAnexo() == null)
         throw new DataAccessEmptyWarning();
      Resource anexo = new ByteArrayResource(transferencia.get().getAnexo());
      return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(anexo);
   }

}