package lectures.part3.functionalprogramming

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

  // HIGHER ORDER FUNCTION
  // Accept function as params
  // or return function as result
  def map[B](transformer: A => B): MyList[B]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]
  def ++[B >: A](list: MyList[B]): MyList[B]
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

  def map[B](transformer: Nothing => B): MyList[B] = Empty
  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty
  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty
  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  def printElements: String =
    if (t.isEmpty) "" + h
    else s"$h ${t.printElements}"

  def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(head)) new Cons(h, t.filter(predicate)) // include head in result
    else t.filter(predicate) // do not include head in result

  def map[B](transformer: A => B): MyList[B] =
    new Cons(transformer(h), t.map(transformer))

  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

  override def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)
}

object FPExercises {
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

    // create an anonymous class of the transformer
    /*
    * [1, 2, 3].map(n * 2)
    * = new Cons(2, [2, 3].map(n * 2))
    * = new Cons(2, new Cons(4, [3].map(n * 2)))
    * = new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2))))
    * = new Cons(2, new Cons(4, new Cons(6, Empty)))*/
    println(listOfInt.map(elem => elem * 2))
    println(listOfInt.map(_ * 2))

    /*
    * [1, 2, 3].filter(n % 2 == 0)
    * = [2, 3].filter(n % 2 == 0)
    * = new Cons(2, [3].filter(n % 2 == 0)
    * = new Cons(2, Empty.filter(n % 2)
    * = new Cons(2, Empty)*/
    println(listOfInt.filter(elem => elem % 2 == 0))
    println(listOfInt.filter(_ % 2 == 0))

    /*
    * [1, 2] ++ [3, 4, 5]
    * = new Cons(1, [2] ++ [3, 4, 5])
    * = new Cons(1, new Cons(2, Empty ++ [3, 4, 5]))
    * = new Cons(1, new Cons(2, [3, 4, 5]))
    * = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5, Empty)))))*/
    println((listOfInt ++ listOfStrings).toString)

    /*
    * [1, 2].flatMap(n => [n, n + 1])
    * = [1, 2] ++ [2].flatMap(n => [n, n + 1])
    * = [1, 2] ++ [2, 3] ++ Empty.flatMap(n => [n, n + 1])
    * = [1, 2, 2, 3] */
    println(listOfInt.flatMap(elem => new Cons(elem, new Cons(elem + 1, Empty))).toString)
    // underscore cannot be used multiple times in func (used twice in flatMap)
  }
}