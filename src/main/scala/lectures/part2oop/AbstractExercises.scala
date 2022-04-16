package lectures.part2oop

abstract class MyList {
  /*
  * head = first element of the list
  * tail = remainder of the list
  * isEmpty = is this list empty
  * add(int) => new list with the element added
  * toString => a string representation */

  def head: Int
  def tail: MyList
  def isEmpty: Boolean
  def add(element: Int): MyList
  def printElements: String // will call subclass's (MyList) printElements method
  override def toString: String = "[" + printElements + "]"
}

// object can extends classes!
object Empty extends MyList {
  def head: Int = throw new NoSuchElementException
  def tail: MyList = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add(element: Int): MyList = new Cons(element, Empty)
  def printElements: String = ""
}

class Cons(h: Int, t: MyList) extends MyList {
  def head: Int = h
  def tail: MyList = t
  def isEmpty: Boolean = false
  def add(element: Int): MyList = new Cons(element, this)

  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements
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
  }
}