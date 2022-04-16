package lectures.part2oop

import math.Fractional.Implicits.infixFractionalOps
import math.Integral.Implicits.infixIntegralOps
import math.Numeric.Implicits.infixNumericOps

abstract class MyList[+A] {
  /*
  * head = first element of the list
  * tail = remainder of the list
  * isEmpty = is this list empty
  * add(int) => new list with the element added
  * toString => a string representation */

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String // will call subclass's (MyList) printElements method
  override def toString: String = "[" + printElements + "]"
}

// object can extends classes!
// Nothing is a proper substitute for any type
object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true

  // B is a supertype of nothing, always true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
  def printElements: String = ""
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)

    def printElements: String =
      if (t.isEmpty) "" + h
      else s"$h ${t.printElements}"
}

object ListTest {
  def main(args: Array[String]): Unit = {
    val list = new Cons(1, Empty)
    println(list.head)
    println(list.add(10).head)
    println(list.isEmpty)

    val long = new Cons(1, new Cons(2, new Cons(3, Empty)))
    println(long.head)
    println(long.isEmpty)
    println(long.toString)

    val listOfInt: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
    val listOfStrings: MyList[String] = new Cons("a", new Cons("b", new Cons("c", Empty)))

    println(listOfInt.toString)
    println(listOfStrings.toString)
  }
}