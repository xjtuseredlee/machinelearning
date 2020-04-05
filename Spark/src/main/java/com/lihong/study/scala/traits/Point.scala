package com.lihong.study.scala.traits

/**
  *
  * @param cx
  * @param cy
  */
class Point(cx: Int, cy: Int) extends Equal {
  var x: Int = cx
  var y: Int = cy

  override def isEqual(obj: Any): Boolean = {
    obj.isInstanceOf[Point] &&
      obj.asInstanceOf[Point].x == x
  }
}


object Point {

  def main(args: Array[String]): Unit = {

    val p1 = new Point(2,3)
    val p2 = new Point(2,3)
    val p3 = new Point(3,3)

    println(p1.isNotEqual(p2))
    println(p2.isNotEqual(p3))
    println(p1.isNotEqual(2))

  }
}
