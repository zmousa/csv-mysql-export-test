package com.zenus.exporttest.model

/**
  * Created by zmousa on 21.02.17.
  */
trait DBTable {
  var name: String = _
  var columns: List[String] = _
  var primaryKeyColumn: String = _
  var primaryKeyIndex: Int = _
  var dateColumns: List[String] = _
  var notNullableColumns: List[String] = _
  var query: String = _
  def getObjFromRow(row:List[String]): M_Model
}

object Table1 extends DBTable {
  name = "table1"
  primaryKeyColumn = "id"
  primaryKeyIndex = 0
  columns = "id,name,create_date".split(",").toList
  dateColumns = "create_date".split(",").toList
  notNullableColumns = "id".split(",").toList
  query = "SELECT * FROM table2 WHERE id = $pid;"

  override def getObjFromRow(row:List[String]): Model1 = {
    Model1(row(0).toInt, row(1), row(2))
  }
}

object Table2 extends DBTable {
  name = "table2"
  primaryKeyColumn = "id"
  primaryKeyIndex = 0
  columns = "id,name,create_date".split(",").toList
  dateColumns = "create_date".split(",").toList
  notNullableColumns = "id".split(",").toList
  query = "SELECT * FROM table2 WHERE id = $pid;"

  override def getObjFromRow(row:List[String]): Model2 = {
    Model2(row(0).toInt, row(1), row(2))
  }
}