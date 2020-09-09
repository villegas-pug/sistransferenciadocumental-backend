package com.sistransferenciadocumental.app.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import com.sistransferenciadocumental.app.constants.Messages;
import com.sistransferenciadocumental.app.errors.DataAccessEmptyWarning;
import com.sistransferenciadocumental.app.errors.EntityUserNotFoundWarning;
import com.sistransferenciadocumental.app.models.entities.Usuario;
import com.sistransferenciadocumental.app.services.IService;
import com.sistransferenciadocumental.app.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/usuario")
public class UsuarioController {

   @Autowired
   private IService<Usuario, String> usuarioService;

   @Autowired
   private BCryptPasswordEncoder passwordEncoder;

   @GetMapping(path = "/listar")
   public ResponseEntity<?> listar() throws DataAccessException {
      List<Usuario> userDb = null;
      userDb = usuarioService.findAll();
      if (userDb.isEmpty())
         throw new DataAccessEmptyWarning();
      return ResponseEntity.ok()
            .body(Response.builder().message(Messages.GET_MESSAGE_SUCCESS_LIST_ENTITY()).data(userDb).build());
   }

   @PostMapping(path = "/auth")
   public ResponseEntity<?> auth(@RequestBody Usuario user) {
      Optional<Usuario> userDb = usuarioService.findById(user.getLogin());
      if (!userDb.isPresent())
         throw new EntityUserNotFoundWarning();

      if (!passwordEncoder.matches(user.getPassword(), userDb.get().getPassword()))
         throw new EntityUserNotFoundWarning();

      return ResponseEntity.ok()
            .body(Response.builder().message(Messages.MESSAGE_SUCCESS_AUTH).data(Arrays.asList(userDb.get())).build());
   }

   @PostMapping(path = "/crear")
   public ResponseEntity<?> getMethodName(@RequestBody Usuario newUser) {
      newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
      usuarioService.save(newUser);
      return ResponseEntity.ok().body(Response.builder()
            .message(Messages.GET_MESSAGE_SUCCES_CREATE_USER(newUser.getLogin())).data(Arrays.asList()).build());
   }
}