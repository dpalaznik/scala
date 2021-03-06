package com.my.scala.chapter17

object Sequences {

  def main(args: Array[String]) {

    // Lists:
    val colors = List("red", "blue", "green")
    println(colors)
    println(colors.head)
    println(colors.tail)

    // Arrays:
    val fiveInts = new Array[Int](5)
    println(fiveInts.mkString(" ,"))

    val fiveToOne = Array(5, 4, 3, 2, 1)
    println(fiveToOne.mkString(" ,"))

    fiveInts(0) = fiveToOne(4)
    println(fiveInts.mkString(" ,"))

    // List buffers:
    import scala.collection.mutable.ListBuffer
    val buf = new ListBuffer[Int]
    println(buf)

    buf += 1
    println(buf)
    buf += 2
    println(buf)

    3 +=: buf
    println(buf)

    println(buf.toList)

    // Array buffers:
    import scala.collection.mutable.ArrayBuffer
    val abuf = new ArrayBuffer[Int]()
    println(abuf)

    abuf += 12
    println(abuf)
    abuf += 15
    println(abuf)

    println(abuf.length)
    println(abuf(0))

    // Strings (via StringsOps)
    def hasUpperCase(s: String) = s.exists(_.isUpper) // implicit conversion to StringOps
    println(hasUpperCase("Robert Frost"))
    println(hasUpperCase("e e cummings bla bla"))
  }
}
