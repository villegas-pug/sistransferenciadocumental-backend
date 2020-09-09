package com.sistransferenciadocumental.app.controllers;

import java.util.List;

import com.sistransferenciadocumental.app.constants.Messages;
import com.sistransferenciadocumental.app.errors.EntityFindByIdWarning;
import com.sistransferenciadocumental.app.models.entities.FondoDocumental;
import com.sistransferenciadocumental.app.services.IService;
import com.sistransferenciadocumental.app.utils.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = { "/fondo-documental" })
public class FondoDocumentalController {

   @Autowired
   private IService<FondoDocumental, Long> fondoDocumentalService;

   @PostMapping(path = { "/buscar" })
   public ResponseEntity<?> buscar(@RequestBody FondoDocumental fondoDocumental) {
      String numeroExpediente = fondoDocumental.getNumeroExpediente();
      List<FondoDocumental> fondoDocumentalDb = fondoDocumentalService
            .findByNumeroExpedienteStartingWith(numeroExpediente);

      /*-> Validación... */
      if (fondoDocumentalDb.size() == 0)
         throw new EntityFindByIdWarning(numeroExpediente);

      return ResponseEntity.ok().body(Response.builder()
            .message(Messages.GET_MESSAGE_SUCCES_SEARCH_RESULT(numeroExpediente)).data(fondoDocumentalDb).build());
   }

}
