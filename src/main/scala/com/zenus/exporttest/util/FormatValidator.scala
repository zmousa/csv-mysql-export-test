package com.zenus.exporttest.util

import java.text.{ParseException, SimpleDateFormat}
import java.util.TimeZone

/**
  * Created by zmousa on 21.02.17.
  */
object FormatValidator {
  def validateDateColumn(col: String): Boolean ={
    if (col == null || col.isEmpty)
      return true
    try {
      val df = new SimpleDateFormat("YYYY-MM-DD'T'hh:mm:ssX")
      df.setTimeZone(TimeZone.getTimeZone("UTC"));
      df.parse(col)
    } catch {
      case e: ParseException => return false
    }
    return true
  }

  def validateNumberColumn(col: String): Boolean ={
    if (col == null || col.isEmpty)
      return true
    try {
      Integer.parseInt(col)
    } catch {
      case e: NumberFormatException => return false
    }
    return true
  }
}
