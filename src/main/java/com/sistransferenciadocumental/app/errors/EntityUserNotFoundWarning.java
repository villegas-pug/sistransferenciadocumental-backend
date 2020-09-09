package com.sistransferenciadocumental.app.errors;

import com.sistransferenciadocumental.app.constants.LevelLog;
import com.sistransferenciadocumental.app.constants.Messages;

public class EntityUserNotFoundWarning extends RuntimeException {

   /**
    *
    */
   private static final long serialVersionUID = 1L;

   public EntityUserNotFoundWarning() {
      super(Messages.MESSAGE_WARNING_ENTITY_USER);
      System.setProperty("levelLog", LevelLog.WARNING);
   }

}
