package com.sistransferenciadocumental.app.utils;

import java.util.UUID;
import com.sistransferenciadocumental.app.constants.LevelLog;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class LogAndResponseHandler {

   public static Response handleLogAndResponse(String idLog, String msjResponse, String msjLog, String error) {
      String uri = System.getProperty("uri");
      String pack = System.getProperty("package");
      String entity = System.getProperty("entity");
      String method = System.getProperty("method");
      String machineName = System.getProperty("machineName");
      String levelLog = System.getProperty("levelLog");
      String logMsj = String.format(
            "%s, package: %s, machineName: %s, uri: %s, entity: %s, method: %s(), message: %s, error: %s", idLog, pack,
            machineName, uri, entity, method, msjLog, error);
      switch (levelLog) {
         case LevelLog.INFO:
            log.info(logMsj);
            break;
         case LevelLog.WARNING:
            log.warn(logMsj);
            break;
         case LevelLog.ERROR:
            log.error(logMsj);
            break;
         default:
            log.error(logMsj);
            break;
      }
      return Response.builder().levelLog(levelLog).message(msjResponse).build();
   }

   public static String getIdLog() {
      return UUID.randomUUID().toString();
   }
}