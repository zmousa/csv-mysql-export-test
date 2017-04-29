package com.zenus.exporttest

import java.nio.file.{FileSystems, Files}

import com.zenus.exporttest.dao.{ConnectionInfo, MySqlDataController}
import com.zenus.exporttest.model.{DBTable, Table1, Table2}
import com.zenus.exporttest.util.FormatValidator
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FunSuite}

import scala.collection.mutable

/**
  * Created by zmousa on 28.02.17.
  */
class ExportTest extends FunSuite with
  BeforeAndAfterEach with BeforeAndAfterAll {


  val connectionInfo = new ConnectionInfo("jdbc:mysql://<Host>:3306/<Database>", "root", "root")
  var sourceFolder:String = _
  var fileTableMap:mutable.Map[String,DBTable] = _


  override def beforeAll(): Unit = {
    sourceFolder = "~/data/csv/"

    fileTableMap += ("file1.csv" -> Table1)
    fileTableMap += ("file2.csv" -> Table2)
  }

  override def afterAll(): Unit = {

  }

  test("Test CSV files exist") {
    val defaultFS = FileSystems.getDefault()
    for (fileName <- fileTableMap.keys)
      assert(Files.exists(defaultFS.getPath(sourceFolder + fileName)))
  }

  test("Test CSV files number of columns") {
    for ((fileName, table) <- fileTableMap){
      val bufferedSource = scala.io.Source.fromFile(sourceFolder + fileName)
      assert(bufferedSource.getLines.next().split(",", -1).length == table.columns.size, s"$fileName number of columns problem")
      bufferedSource.close()
    }
  }

  test("Test columns data format") {
    for ((fileName, table) <- fileTableMap) {
      val source = scala.io.Source.fromFile(sourceFolder + fileName)
      for (line <- source.getLines) {
        val cols = line.split(",", -1).map(_.trim)
        for ((column, index) <- table.columns.zipWithIndex) {
          println(s"$column is " + cols(index))
          if (table.dateColumns.contains(column))
            assert(FormatValidator.validateDateColumn(cols(index).replace("\"", "")), cols(index) + " Date format problem")
          if (table.notNullableColumns.contains(column))
            assert(cols(index) != null, cols(index) + " Null field problem")
        }
      }
    }
  }

  test("Test data content matching") {
    for ((fileName, table) <- fileTableMap) {
      val source = scala.io.Source.fromFile(sourceFolder + fileName)
      for (line <- source.getLines) {
        val cols = line.split(",", -1).map(_.trim)
        val rowData = MySqlDataController.getRowById(table.query, cols(table.primaryKeyIndex))
        assert(rowData.mkString(",").hashCode == line.hashCode)
      }
    }
  }
}

