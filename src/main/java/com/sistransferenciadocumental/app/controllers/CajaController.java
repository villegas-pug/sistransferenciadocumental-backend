package com.sistransferenciadocumental.app.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import com.sistransferenciadocumental.app.constants.Messages;
import com.sistransferenciadocumental.app.errors.DataAccessEmptyWarning;
import com.sistransferenciadocumental.app.errors.EntityFindByIdWarning;
import com.sistransferenciadocumental.app.models.entities.Caja;
import com.sistransferenciadocumental.app.models.entities.FondoDocumental;
import com.sistransferenciadocumental.app.models.entities.TipoTramite;
import com.sistransferenciadocumental.app.models.entities.Usuario;
import com.sistransferenciadocumental.app.services.IService;
import com.sistransferenciadocumental.app.utils.Response;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/caja")
@Log4j2
public class CajaController {
   @Autowired
   private IService<Caja, Long> cajaService;

   @Autowired
   private IService<Usuario, String> usuarioService;

   @Autowired
   private IService<TipoTramite, Long> tipoTramiteService;

   @GetMapping(path = "/generarId")
   public ResponseEntity<?> generarId() {
      Long newIdCaja = cajaService.count() + 1;
      return ResponseEntity.ok().body(Response.builder().message(Messages.GET_MESSAGE_SUCCESS_NEW_ID(newIdCaja))
            .data(Arrays.asList(newIdCaja)).build());
   }

   @GetMapping(path = "/listar")
   public ResponseEntity<?> listar() {
      List<Caja> cajaDb = cajaService.findAll();
      if (cajaDb.size() == 0)
         throw new DataAccessEmptyWarning();
      return ResponseEntity.ok()
            .body(Response.builder().message(Messages.GET_MESSAGE_SUCCESS_LIST_ENTITY()).data(cajaDb).build());
   }

   @PostMapping(path = "/generar", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
   public ResponseEntity<?> generar(@RequestPart(required = true) MultipartFile file, @RequestPart Caja caja) {
      Caja cajaDb = new Caja();
      Optional<Usuario> operador = usuarioService.findById(caja.getOperador().getLogin());

      /*-> Validación... */
      if (!operador.isPresent())
         throw new EntityFindByIdWarning(caja.getOperador().getNombre());

      cajaDb.setOperador(operador.get());
      cajaDb.setTipoTramite(tipoTramiteService.findById(caja.getTipoTramite().getIdTipoTramite()).get());
      cajaDb.setFondoDocumental(this.getObjXlsx(file));
      cajaService.save(cajaDb);
      return ResponseEntity.ok().body(
            Response.builder().message(Messages.GET_MESSAGE_SUCCESS_CREATE()).data(cajaService.findAll()).build());
   }

   @PutMapping(path = "/actualizar", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
   public ResponseEntity<?> actualizar(@RequestPart(required = false) MultipartFile file, @RequestPart Caja caja) {
      log.warn(file);
      log.warn(caja);
      Optional<Caja> cajaDb = cajaService.findById(caja.getIdCaja());
      if (!cajaDb.isPresent())
         throw new EntityFindByIdWarning(caja.getIdCaja().toString());

      if (file != null)
         cajaDb.get().setFondoDocumental(this.getObjXlsx(file));

      cajaDb.get().setEvaluador(usuarioService.findById(caja.getEvaluador().getLogin()).get());
      cajaDb.get().setTipoTramite(tipoTramiteService.findById(caja.getTipoTramite().getIdTipoTramite()).get());
      cajaDb.get().removeAllFondoDocumental();
      cajaService.save(cajaDb.get());
      return ResponseEntity.ok().body(
            Response.builder().message(Messages.GET_MESSAGE_SUCCESS_UPDATE()).data(cajaService.findAll()).build());

   }

   private List<FondoDocumental> getObjXlsx(MultipartFile file) {
      try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {
         XSSFSheet sheet = workbook.getSheetAt(0);
         List<FondoDocumental> fondoDocumentalDb = new ArrayList<>();
         for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = sheet.getRow(i);
            fondoDocumentalDb.add(new FondoDocumental(row.getCell(1).getStringCellValue(),
                  (int) row.getCell(2).getNumericCellValue(), row.getCell(3).getStringCellValue()));
         }
         return fondoDocumentalDb;
      } catch (IOException e) {
         return null;
      }
   }

}