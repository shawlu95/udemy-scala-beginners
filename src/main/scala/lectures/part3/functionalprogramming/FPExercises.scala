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

  def map[B](transformer: MyTransformer[A, B]): MyList[B]
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
  def filter(predicate: MyPredicate[A]): MyList[A]
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

  def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty
  def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty
  def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty
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

  def filter(predicate: MyPredicate[A]): MyList[A] =
    if (predicate.test(head)) new Cons(h, t.filter(predicate)) // include head in result
    else t.filter(predicate) // do not include head in result

  def map[B](transformer: MyTransformer[A, B]): MyList[B] =
    new Cons(transformer.transform(h), t.map(transformer))

  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

  override def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
    transformer.transform(h) ++ t.flatMap(transformer)
}

trait MyPredicate[-T] { // contravariant
  def test(elem: T): Boolean
}

trait MyTransformer[-A, B] { // input is contravariant
  def transform(elem: A): B
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
    println(listOfInt.map(new MyTransformer[Int, Int] {
      override def transform(elem: Int): Int = elem * 2
    }))

    /*
    * [1, 2, 3].filter(n % 2 == 0)
    * = [2, 3].filter(n % 2 == 0)
    * = new Cons(2, [3].filter(n % 2 == 0)
    * = new Cons(2, Empty.filter(n % 2)
    * = new Cons(2, Empty)*/
    println(listOfInt.filter(new MyPredicate[Int] {
      override def test(elem: Int): Boolean = elem % 2 == 0
    }))

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
    println(listOfInt.flatMap(new MyTransformer[Int, MyList[Int]] {
      override def transform(elem: Int): MyList[Int] = new Cons(elem, new Cons(elem + 1, Empty))
    }).toString)
  }
}