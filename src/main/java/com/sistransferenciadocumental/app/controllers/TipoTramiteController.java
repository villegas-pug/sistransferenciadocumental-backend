package com.sistransferenciadocumental.app.controllers;

import java.util.List;
import com.sistransferenciadocumental.app.constants.Messages;
import com.sistransferenciadocumental.app.errors.DataAccessEmptyWarning;
import com.sistransferenciadocumental.app.models.entities.TipoTramite;
import com.sistransferenciadocumental.app.services.IService;
import com.sistransferenciadocumental.app.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/tipo-tramite")
public class TipoTramiteController {

   @Autowired
   private IService<TipoTramite, Long> tipoTramiteService;

   @GetMapping(path = "/listar")
   public ResponseEntity<?> listar() throws DataAccessException {
      List<TipoTramite> response = tipoTramiteService.findAll();
      if (response.isEmpty())
         throw new DataAccessEmptyWarning();

      return ResponseEntity.ok().body(Response.builder().message(Messages.GET_MESSAGE_SUCCESS_LIST_ENTITY())
            .data(tipoTramiteService.findAll()).build());
   }
}