package com.sistransferenciadocumental.app.errors;

import com.sistransferenciadocumental.app.constants.*;

public class DataAccessEmptyWarning extends RuntimeException {
   private static final long serialVersionUID = 1L;

   public DataAccessEmptyWarning() {
      super(Messages.GET_MESSAGGE_WARNING_EMPTY());
      System.setProperty("levelLog", LevelLog.WARNING);
   }
}