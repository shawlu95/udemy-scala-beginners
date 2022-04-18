package lectures.part3.functionalprogramming

object WhatsFunction {
  // Goal: use functions as first class element
  // Reality: OOP, all scala functions are objects
  // JVM is designed with OOP in mind, not FP

  // doubler is an instance of a function-like class
  // and can be used like a function
  val doubler: Int = new MyFunc[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  // default function types: Function1, Function2... up to 22 params
  // Function1 accepts one param, returns on param
  val string2Int: String => Int = new Function[String, Int] {
    override def apply(v1: String): Int = v1.toInt
  }

  // syntactic sugar: ((Int, Int) => Int)
  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }
  def main(args: Array[String]): Unit = {
    println(doubler(100)) // returns 200
    println(string2Int("666"))
  }
}

trait MyFunc[A, B] {
  def apply(element: A): B
}