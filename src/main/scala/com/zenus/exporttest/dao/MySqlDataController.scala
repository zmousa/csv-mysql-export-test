package com.zenus.exporttest.dao

import java.sql.{Connection, DriverManager, SQLException}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by zmousa on 24.02.17.
  */
object MySqlDataController {
  var connection:Connection = null

  def openConnection(connectionInfo: ConnectionInfo): Connection ={
    val driver = "com.mysql.jdbc.Driver"
    try {
      Class.forName(driver)
    } catch {
      case e: ClassNotFoundException => e.printStackTrace()
    }

    try {
      connection = DriverManager.getConnection(connectionInfo.url, connectionInfo.username, connectionInfo.password)
    } catch {
      case e: SQLException => e.printStackTrace
    }
    connection
  }

  def getRowById(query:String, rowId: String): List[String] ={
    val rawData:List[String] = List[String]()
    if (connection != null) {
      try {
        val statement = connection.createStatement()
        val resultSet = statement.executeQuery(query.replaceAll("$pid", rowId))
        val columnCount = resultSet.getMetaData().getColumnCount();
        while (resultSet.next)
          for (i <- 0 until columnCount)
            rawData :+ resultSet.getString(i)

        resultSet.close
        statement.close
      } catch {
        case e: SQLException => e.printStackTrace
      }
    }
    rawData
  }

  def getQueryRows(query:String): ArrayBuffer[Entity] ={
    var listEntities = ArrayBuffer[Entity]()
    if (connection != null) {
      try {
        val statement = connection.createStatement()
        val resultSet = statement.executeQuery(query)
        while (resultSet.next) {
          val entity = new Entity
          entity.id = resultSet.getInt("id")
          entity.name = resultSet.getString("name")
          listEntities += entity
        }
        resultSet.close
        statement.close
      } catch {
        case e: SQLException => e.printStackTrace
      }
    }
    listEntities
  }

  // Entity
  class Entity {
    var id: Long = _
    var name: String = _
  }

  def closeConnection: Unit ={
    if (connection != null)
      connection.close
  }
}
