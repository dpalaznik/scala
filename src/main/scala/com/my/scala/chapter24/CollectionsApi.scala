package com.my.scala.chapter24

import scala.collection.immutable.HashMap
import scala.collection.{BitSet, LinearSeq, SortedSet}
import scala.collection.mutable.Buffer

object CollectionsApi {

  def main(args: Array[String]) {

    // Mutable and immutable collections
    Traversable(1, 2, 3)
    Iterable("x", "y", "z")
    Map("x" -> 24, "y" -> 25, "z" -> 26)
    SortedSet("hello", "World")
    val x, y, z = 1
    Buffer(x, y, z)
    IndexedSeq(1.0, 2.0)
    val a, b, c = 'a'
    LinearSeq(a, b, c)
    // Specific implementations:
    val list = List(1, 2, 3)
    val hashmap = HashMap("x" -> 24, "y" -> 25, "z" -> 26)

    // toString:
    println(list)
    println(hashmap)

    // map:
    println(List(1, 2, 3) map (_ + 1))
    println(Set(1, 2, 3) map (_ * 2))

    // Trait Iterable
    val xs = List(1, 2, 3, 4, 5)
    val git = xs grouped 3
    println(git)
    println(git.next())
    println(git.next())

    val sit = xs sliding 3
    println(sit)
    println(sit.next())
    println(sit.next())

    val ys = List(5, 4, 3, 2, 1)
    val ys1 = List(5, 4, 3)
    println(xs zip ys)
    println(xs zipAll (ys1, 1, 10))
    println(xs.zipWithIndex)

    // apply:
    println(Seq(1, 2, 3)(1))
    println(Set('a', 'b', 'c')('b'))
    println(Set()('b'))
    println(Map('a' -> 1, 'b' -> 10, 'c' -> 100)('b'))

    // The sequence traits Seq, indexedSeq, LinearSeq
    val seq = Seq(1, 2, 3, 4, 5)
    println(seq(1))
    println(seq isDefinedAt 7)
    println(seq indices)
    // index search
    println(seq indexOf 2)
    println(seq indexOfSlice Seq(3, 4))
    println(seq indexWhere(_ > 3))
    println(seq segmentLength (_ > 3, 1))
    println(0 +: seq)
    // addition
    println(seq :+ 0)
    println(seq padTo (10, 0))
    // updates
    println(seq patch (2, Seq(8, 9, 10), 3))
    println(seq updated(2, 0))
    // sorting
    println(Seq(5,1,3,10,-1).sorted)
    // reversals
    println(seq.reverse)
    // comparisons
    // ...
    // Multiset:
    println(seq intersect Seq(1, 3, 4, 10, 20))
    println(seq diff Seq(1, 3, 4, 10, 20))
    println(seq union Seq(1, 3, 4, 10, 20))
    println((seq union Seq(1, 3, 4, 10, 20)).distinct)

    // Buffers
    val buf = Buffer(1, 2, 3)
    println(buf += 5)
    println(buf += (1, 2, 1))
    println(buf ++= Seq(0, 0, 1, 11, 1))
    println(-1 +=: buf)
    println(Seq(-3, -2) ++=: buf)
    println(buf insert(4, 1))
    println(buf insertAll(5, Seq(1, 0, -1)))
    // removals
    println(buf -= 0)
    // cloning
    println(buf.clone)

    // Sets
    val fruit = Set("apple", "orange", "peach", "banana")
    println(fruit("peach"))
    println(fruit("potato"))
    // additions:
    println(fruit + "pinapple")
    println(fruit + ("kiwi", "strawberry"))
    println(fruit ++ Set("kiwi", "strawberry"))
    // removals:
    println(fruit - "apple")
    println(fruit - ("apple", "orange"))
    println(fruit -- Set("apple", "orange"))
    println(fruit.empty)
    // binary:
    println(fruit & Set("orange", "banana", "kiwi"))
    println(fruit intersect Set("orange", "banana", "kiwi"))
    println(fruit | Set("orange", "banana", "kiwi"))
    println(fruit union Set("orange", "banana", "kiwi"))
    println(fruit &~ Set("orange", "banana", "kiwi"))
    println(fruit diff Set("orange", "banana", "kiwi"))

    var s = Set(1, 2, 3)
    println(s)
    s += 4; s -= 2
    println(s)

    val sm = collection.mutable.Set(1, 2, 3)
    println(sm)
    sm += 4
    println(sm)
    sm -= 2
    println(sm)

    val myOrdering = Ordering.fromLessThan[String](_ > _)
    println(myOrdering)

    import scala.collection.immutable.TreeSet
    println(TreeSet.empty(myOrdering))

    val set = TreeSet.empty[String]
    val numbers = set + ("one", "two", "three", "four")
    println(numbers)
    println(numbers range ("one", "two"))
    println(numbers from "three")

    // Maps
    println(Map("x" -> 24, "y" -> 25, "z" -> 26)) // the same as following due to implicit conversion
    println(Map(("x",24), ("y",25), ("z",26)))

    def f(x: String) = {
      println("taking my time."); Thread.sleep(100)
      x.reverse
    }

    val cache = collection.mutable.Map[String, String]()
    def cachedF(s: String) = cache.getOrElseUpdate(s, f(s))
    println(cachedF("abc"))
    println(cachedF("abc"))
    println(cachedFD("def"))
    println(cachedFD("def"))

    // the same but directly
    def cachedFD(arg: String) = cache get arg match {
      case Some(result) => result
      case None =>
        val result = f(arg)
        cache(arg) = result
        result
    }
  }

  sealed abstract class TreeT extends Traversable[Int] {
    case class Branch(left: TreeT, right: TreeT) extends TreeT
    case class Node(elem: Int) extends TreeT
    def foreach[U](f: Int => U) = this match {
      case Node(elem) => f(elem)
      case Branch(l, r) => l foreach f; r foreach f
    }
  }
  sealed abstract class TreeI extends Iterable[Int] {
    case class Branch(left: TreeI, right: TreeI) extends TreeI
    case class Node(elem: Int) extends TreeI
    def iterator: Iterator[Int] = this match {
      case Node(elem) => Iterator.single(elem)
      case Branch(l, r) => l.iterator ++ r.iterator
    }
  }
}
