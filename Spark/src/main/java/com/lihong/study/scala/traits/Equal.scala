package com.lihong.study.scala.traits

trait Equal {

  def isEqual(x: Any): Boolean

  def isNotEqual(x: Any): Boolean = !isEqual(x)
}

