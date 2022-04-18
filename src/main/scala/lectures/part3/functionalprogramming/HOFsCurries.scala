package lectures.part3.functionalprogramming

object HOFsCurries {
  // returns another function which takes an int and returns an int
  // takes an int and a function which takes a string an a function as input, and returns an int
  val superFunc: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null

  // a HOF example: function that applies another function N times
  // nTimes(f, 3, x) = f(f(f(x)))
  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))

  // do not apply f(x), returns a lambda function to be used later
  // ntb(f, n) = x => f(f(...f(x)))
  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n - 1)(f(x))

  def toCurry[A, B, C](f: (A, B) => C): (A => B => C) =
    x => y => f(x, y)

  def fromCurry[A, B, C](f: (A => B => C)): (A, B) => C =
    (x, y) => f(x)(y)

  def compose[A, B, T](f: A => B, g: T => A): T => B =
    x => f(g(x))

  def andThen[A, B, C](f: A => B, g: B => C): A => C =
    x => g(f(x))

  def superAdder: (Int => Int => Int) = toCurry(_ + _)
  def add4 = superAdder(4)
  def simpleAdder = fromCurry(superAdder)

  def main(args: Array[String]): Unit = {
    val plus1 = (x: Int) => x + 1
    println(nTimes(plus1, 10, 1)) // returns 11
    println(nTimes(x => x * 2, 3, 1)) // returns 8

    val plus10 = nTimesBetter(plus1, 10)
    println(plus10(1)) // returns 11

    val power = nTimesBetter(x => x * 2, 10)
    println(power(1)) // returns 1024

    // curried functions,
    // parenthesis are "right associative"
    // "Int => Int => Int" means "Int => (Int => Int)"
    val superAdder: Int => Int => Int = (x: Int) => (y: Int) => x + y
    val add3 = superAdder(3) // y => y + 3
    println(add3(10)) // returns 13
    println(superAdder(3)(10)) // equivalent

    // functions with multiple parameter lists
    def curriedFormatter(c: String)(x: Double): String = c.format(x)

    // must specify type for smaller function (e.g. formats a double and returns a string)
    val standardFormatter: (Double => String) = curriedFormatter("%4.2f")
    val preciseFormatter: (Double => String) = curriedFormatter("%10.8f")
    println(standardFormatter(Math.PI))
    println(preciseFormatter(Math.PI))

    println("curried adder")
    println(add4(17))

    println("fromCurry")
    println(simpleAdder(4, 17))

    val add2 = (x: Int) => x + 2
    val times3 = (x: Int) => x * 3
    val composed = compose(add2, times3)
    val ordered = andThen(add2, times3)

    println("composed: 4 * 3 + 2 = 14")
    println(composed(4))

    println("ordered: (4 + 2) * 3 = 18")
    println(ordered(4))
  }

}
