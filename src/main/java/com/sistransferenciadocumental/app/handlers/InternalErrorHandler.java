package com.sistransferenciadocumental.app.handlers;

import com.sistransferenciadocumental.app.constants.Messages;
import com.sistransferenciadocumental.app.utils.LogAndResponseHandler;
import com.sistransferenciadocumental.app.utils.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class InternalErrorHandler extends ResponseEntityExceptionHandler {

   @Override
   protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
         HttpStatus status, WebRequest request) {
      return new ResponseEntity<>(getResponseEntity(status, ex), status);
   }

   @Override
   protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
         HttpHeaders headers, HttpStatus status, WebRequest request) {
      return new ResponseEntity<>(getResponseEntity(status, ex), status);
   }

   private Response getResponseEntity(HttpStatus status, Exception ex) {
      String idLog = LogAndResponseHandler.getIdLog();
      String msjResponse = Messages.GET_MESSAGGE_ERROR_DATA_ACCESS(idLog);
      return LogAndResponseHandler.handleLogAndResponse(idLog, msjResponse, ex.getMessage(), status.getReasonPhrase());
   }

}