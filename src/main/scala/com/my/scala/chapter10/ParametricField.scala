package com.my.scala.chapter10

object ParametricField {

  def main(args: Array[String]) {
    val cat = new Cat
    val tiger = new Tiger(false, 10)
  }
}

class Cat {
  val dangerous = false
}

class Tiger (
  override val dangerous: Boolean,
  private var age: Int
) extends Cat
