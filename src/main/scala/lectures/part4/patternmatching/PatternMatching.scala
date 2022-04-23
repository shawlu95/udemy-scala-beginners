package lectures.part4.patternmatching

import scala.util.Random

object PatternMatching extends App {
  val random = new Random
  val x = random.nextInt(10)

  val description = x match {
    case 1 => "the ONE"
    case 2 => "double or nothing"
    case 3 => "third"
    case _ => "others" // wild card
  }

  println(x)
  println(description)

  // 1. decompose value
  case class Person(name: String, age: Int)
  val shaw = Person("Shaw", 27)

  // pattern match against case class instance and extract values
  val greeting = shaw match {
    // case can have guard (if condition)
    case Person(n, a) if a < 21 => s"Hi my name is $n and I can't drink"
    case Person(n, a) => s"Hi my name is $n and I am $a years old"
    case _ => "No idea"
  }
  println(greeting)

  /*
  * 1. cases are matched in order
  * 2. If no match: throws MatchError
  * 3. type of PM expression: nearest ancestor class of all cases
  * 4. works great with case classes which come with extractor patterns */

  // PM on sealed hierarchies
  // sealed class helps cover your ass, all cases must be listed
  // or compiler throws warning
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Golden")
  animal match {
    case Dog(someBreed) => println(s"This is a dog $someBreed")
  }

  // anti-pattern: match everything
  val isEven = x match {
    case n if n % 2 == 0 => true
    case _ => false
  }

  // over-complicate things
  val isEvenCond = if (x % 2 == 0) true else false
  val isEvenNormal = x % 2 == 0

  /* Exercise
  * Simple function uses PM
  * takes an Expr => human readable form
  *
  * Sum(Number(2), Number(3)) => 2 + 3
  * Sum(Number(2), Number(3), Number(4)) = 2 + 3 + 4
  * Prod(Sum(Number(2), Number(1)), Number(3)) = (2 + 1) * 3
  * Sum(Prod(Number(2), Number(1)), Number(3)) = 2 * 2 + 3 */
  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, ee2: Expr) extends Expr

  def maybeParentheses(exp: Expr) = exp match {
    case Prod(_, _) => show(exp)
    case Number(_) => show(exp)
    case _ => "(" + show(exp) + ")" // Sum
  }

  def show(e: Expr): String = e match {
    case Number(n) => s"$n"
    case Sum(e1, e2) => show(e1) + " + " + show(e2)
    case Prod(e1, e2) => maybeParentheses(e1) + " * " + maybeParentheses(e2)
  }

  println(show(Sum(Number(2), Number(3))))
  println(show(Sum(Sum(Number(2), Number(3)), Number(4))))
  println(show(Prod(Sum(Number(2), Number(1)), Number(3))))
  println(show(Sum(Prod(Number(2), Number(1)), Number(3))))

}
