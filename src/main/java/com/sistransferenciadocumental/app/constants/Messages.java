package com.sistransferenciadocumental.app.constants;

public class Messages {
   /*-> SUCCESS: */
   public static final String MESSAGE_SUCCESS_NEW_ID = "¡El código %d, ha sido generado con exito!";
   public static final String MESSAGE_SUCCESS_LIST_ENTITY = "¡La entidad %s, ha sido listada con exito!";
   public static final String MESSAGE_SUCCESS_CREATE = "¡Se registró con existo en la entidad %s!";
   public static final String MESSAGE_SUCCESS_UPDATE = "¡La entidad %s, fué actualizada con exito!";
   public static final String MESSAGE_SUCCESS_DOWNLOAD = "¡El anexo, se descargo exitosamente!";
   public static final String MESSAGE_SUCCESS_AUTH = "!Acceso permitido¡";
   public static final String MESSAGE_SUCCES_SEARCH_RESULT = "¡Se encontraron resultados, para su busqueda: %s!";
   public static final String MESSAGE_SUCCES_CREATE_USER = "¡El usuario %s, fué creado con exito!";

   /*-> WARNING: */
   public final static String MESSAGGE_WARNING_EMPTY = "¡No hay registros en la entidad %s, para mostrar!";
   public final static String MESSAGE_WARNING_ENTITY_FIND_BY_ID = "¡La entidad %s, con código %s no existe!";
   public final static String MESSAGE_WARNING_ENTITY_USER = "¡Usuario o clave incorrecta!";

   /*-> ERROR: */
   public final static String MESSAGGE_ERROR_DATA_ACCESS = "¡Ocurrió un error, contacte a sitemas y proporcione este código %s!";

   public static String GET_MESSAGE_SUCCESS_NEW_ID(Long newIdCaja) {
      return String.format(MESSAGE_SUCCESS_NEW_ID, newIdCaja);
   }

   public static String GET_MESSAGE_SUCCESS_LIST_ENTITY() {
      return String.format(MESSAGE_SUCCESS_LIST_ENTITY, System.getProperty("entity"));
   }

   public static String GET_MESSAGGE_ERROR_DATA_ACCESS(String idLog) {
      return String.format(MESSAGGE_ERROR_DATA_ACCESS, idLog);
   }

   public static String GET_MESSAGE_WARNING_ENTITY_FIND_BY_ID(String id) {
      return String.format(MESSAGE_WARNING_ENTITY_FIND_BY_ID, System.getProperty("entity"), id);
   }

   public static String GET_MESSAGGE_WARNING_EMPTY() {
      return String.format(MESSAGGE_WARNING_EMPTY, System.getProperty("entity"));
   }

   public static String GET_MESSAGE_SUCCESS_CREATE() {
      return String.format(MESSAGE_SUCCESS_CREATE, System.getProperty("entity"));
   }

   public static String GET_MESSAGE_SUCCESS_UPDATE() {
      return String.format(MESSAGE_SUCCESS_UPDATE, System.getProperty("entity"));
   }

   public static String GET_MESSAGE_SUCCES_SEARCH_RESULT(String value) {
      return String.format(MESSAGE_SUCCES_SEARCH_RESULT, value);
   }

   public static String GET_MESSAGE_SUCCES_CREATE_USER(String newUser) {
      return String.format(MESSAGE_SUCCES_CREATE_USER, newUser);
   }

}
