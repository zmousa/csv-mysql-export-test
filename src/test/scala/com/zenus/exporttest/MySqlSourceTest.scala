package com.zenus.exporttest

import com.zenus.exporttest.dao.{ConnectionInfo, MySqlDataController}
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FunSuite}

/**
  * Created by zmousa on 24.02.17.
  */
class MySqlSourceTest extends FunSuite with
  BeforeAndAfterEach with BeforeAndAfterAll{

  val connectionInfo = new ConnectionInfo("jdbc:mysql://<Host>:3306/<Database>", "root", "root")

  override def beforeAll(): Unit = {

  }

  override def afterAll(): Unit = {

  }

  ignore("Test MySql connection"){
    assert(MySqlDataController.openConnection(connectionInfo) != null, "MySql Connection Problem")
  }
}
