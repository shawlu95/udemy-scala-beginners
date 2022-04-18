package lectures.part1.basics

import scala.annotation.tailrec

object Recursion extends App {
  // recursion too deep causes stack overflow (try factorial(5000))
  def factorial(n: Int): Int = {
    if (n == 1) n
    else {
      println("Computing factorial of " + n + " I first need factorial of " + (n + 1))
      val result = n * factorial(n - 1)
      println("Computed factorial of " + n)
      result
    }
  }
  print(factorial(10))
  // print(factorial(5000)) // crashes

  // Works with big number!
  // TAIL RECURSION: Doesn't need to cache intermediary result
  def smartFactorial(n: Int): BigInt = {
    // emulate a loop
    @tailrec
    def factorialHelper(x: Int, accumulator: BigInt): BigInt = {
      if (x <= 1) {
        println("base case: " + x)
        accumulator
      } else {
        println("calling factorialHelper(" + (x - 1) + ", " + (x * accumulator) + ")")
        factorialHelper(x - 1, x * accumulator)
      }
    }
    // Tail recursion: use recursion as the last expression
    factorialHelper(n, 1)
  }
  println(smartFactorial(10))
  // println(smartFactorial(6000))

  @tailrec
  def concatTailRec(aString: String, n: Int, accumulator: String): String = {
    if (n <= 0) accumulator
    else concatTailRec(aString, n - 1, aString + accumulator)
  }
  println(concatTailRec("yoo ", 3, ""))

  def isPrime(n: Int): Boolean = {
    @tailrec
    def helper(t: Int, isStillPrime: Boolean): Boolean = {
      if  (!isStillPrime) false
      else if (t <= 1) true
      else helper(t - 1, n % t != 0 && isStillPrime)
    }
    helper(n / 2, true)
  }
  println(isPrime(2003)) // true
  println(isPrime(629)) // false

  def fibonacci(n: Int): Int = {
    // calling two recursions, so two accumulators
    @tailrec
    def fiboTailrec(i: Int, last: Int, nextLast: Int): Int =
      println(i + " " + last + " " + nextLast )
      if (i >= n) last
      else fiboTailrec(i + 1, last + nextLast, last)
    if (n <= 2) 1
    else fiboTailrec(2, 1, 1 )
  }
  println(fibonacci(8))
}
