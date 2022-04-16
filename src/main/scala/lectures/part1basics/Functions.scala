package lectures.part1basics

object Functions extends App {

  // Return a string
  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("yo", 100))

  def aParamlessFunction(): Int = 100
  println(aParamlessFunction())
  // println(aParamlessFunction) // not allowed

  // The worst thing in scala is to use imperative code like LOOP
  // If need noop, always use RECURSION
  def aRecursion(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + " " + aRecursion(aString, n - 1)
  }

  println(aRecursion("Yoo", 3))

  // a function that only execute side effect returns a UNIT
  // We want a side effect when logging, not actually changing data
  def aSideEffect(aString: String): Unit = println(aString)
  aSideEffect("Haha")

  // Can define function inside func
  def aBigFunc(n: Int): Int = {
    def aSmallFunc(a: Int, b: Int): Int = a + b
    aSmallFunc(n, n * n)
  }

  println(aBigFunc(9))

  def greeting(name: String, age: Int): String = {
    "hello I am " + name + ". I am " + age + " years old"
  }
  println(greeting("shaw", 27))

  def factorial(n: Int): Int = {
    if (n == 1) n
    else n * factorial(n - 1)
  }
  println(factorial(5)) // 120

  def fibonacci(n: Int): Int = {
    if (n <= 2) 1
    else fibonacci(n - 1) + fibonacci(n - 2)
  }
  println(fibonacci(8)) // 21

  def isPrime(n: Int): Boolean = {
    // auxillary function
    def isPrimeUntil(t: Int): Boolean = {
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t - 1)
    }
    isPrimeUntil(n / 2)
  }
  println("97: " + isPrime(97))
  println("2003: " + isPrime(2003))
  println("6: " + isPrime(6))
}
