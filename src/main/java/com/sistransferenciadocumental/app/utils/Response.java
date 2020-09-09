package com.sistransferenciadocumental.app.utils;

import java.util.List;

import com.sistransferenciadocumental.app.constants.LevelLog;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Response {
   private @Builder.Default String levelLog = LevelLog.SUCCESS;
   private String message;
   private @Builder.Default List<?> data = null;
}