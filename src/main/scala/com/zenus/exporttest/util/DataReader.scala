package com.zenus.exporttest.util

import java.io.File

import scala.util.control.Breaks._

/**
  * Created by zmousa on 21.02.17.
  */
class DataReader {
  def getListOfFiles(dir: String):List[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(_.isFile).toList
    } else {
      List[File]()
    }
  }

  def readFile(file: File): Unit ={
    println(file.getName)
    var counter: Int = 0
    val bufferedSource = scala.io.Source.fromFile(file)
    breakable {
      for (line <- bufferedSource.getLines) {
        val cols = line.split(",").map(_.trim)
        for (col <- cols)
          print(col + "|")
        println()
        counter += 1
        if (counter > 10)
          break
      }
    }
    bufferedSource.close
  }

  def readAllFiles(dir: String): Unit ={
    for (file <- getListOfFiles(dir)){
      readFile(file)
    }
  }
}

object DataReader extends App {
  new DataReader().readAllFiles("~/data/csv")
}
