package com.sistransferenciadocumental.app.handlers;

import com.sistransferenciadocumental.app.errors.EntityFindByIdWarning;
import com.sistransferenciadocumental.app.errors.EntityUserNotFoundWarning;
import com.sistransferenciadocumental.app.utils.LogAndResponseHandler;
import com.sistransferenciadocumental.app.utils.Response;
import com.sistransferenciadocumental.app.constants.Messages;
import com.sistransferenciadocumental.app.errors.DataAccessEmptyWarning;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

   @ExceptionHandler(value = { DataAccessEmptyWarning.class })
   @ResponseStatus(HttpStatus.OK)
   public Response handlerNotFound(DataAccessEmptyWarning e) {
      String idLog = LogAndResponseHandler.getIdLog();
      String msjResponse = e.getMessage();
      return LogAndResponseHandler.handleLogAndResponse(idLog, msjResponse, msjResponse, null);
   }

   @ExceptionHandler({ EntityFindByIdWarning.class, EntityUserNotFoundWarning.class })
   @ResponseStatus(HttpStatus.OK)
   public Response handleDataAccessError(Exception e) {
      String idLog = LogAndResponseHandler.getIdLog();
      String msjResponse = e.getMessage();
      return LogAndResponseHandler.handleLogAndResponse(idLog, msjResponse, msjResponse, null);
   }

   @ExceptionHandler(Exception.class)
   @ResponseStatus(HttpStatus.OK)
   public Response handleDataAccessException(Exception e) {
      String idLog = LogAndResponseHandler.getIdLog();
      String msjResponse = Messages.GET_MESSAGGE_ERROR_DATA_ACCESS(idLog);
      return LogAndResponseHandler.handleLogAndResponse(idLog, msjResponse, e.getMessage(), e.toString());
   }

}