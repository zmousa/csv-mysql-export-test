package com.zenus.exporttest.model

/**
  * Created by zmousa on 01.03.17.
  */
trait M_Model

case class Model1(id:Int, name:String, createDate: String) extends M_Model {
  override def equals(that: Any): Boolean =
    that match {
      case that: Model1 =>
        this.id == that.id &&
          this.name.equals(that.name)
      case _ => false
    }
}

case class Model2(id:Int, name:String, createDate: String) extends M_Model {
  override def equals(that: Any): Boolean =
    that match {
      case that: Model2 =>
        this.id == that.id &&
        this.name.equals(that.name)
      case _ => false
    }
}