package lectures.part2.oop

import scala.language.postfixOps

object MethodNotations extends App {
  // declare inside the object, or will
  // cause CONFLICT with the other Person class
  class Person(val name: String, favoriteMovie: String) {
    def likes(movie: String): Boolean = favoriteMovie == movie
    def hangOutWith(person: Person): String = s"${this.name} is hooking up with ${person.name}"

    // operator overload
    def +(person: Person): String = s"${this.name} is hooking up with ${person.name}"

    // must place a space before :
    def unary_! : String = s"$name unary!"

    // postfix, if function doesn't receive param
    def isAlive: Boolean = true

    // method signature (name, parenthesis) is important
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
  }

  val shaw = new Person("Shaw", "Interstellar")
  println(shaw.likes("Interstellar"))

  // equivalent "infix notation" (disgusting)
  // only works with method with one param
  println(shaw likes "Interstellar")

  val ava = new Person("Ava", "Breakfast Club")
  println(shaw hangOutWith ava)
  println(shaw + ava)
  println(shaw.+(ava))
  println(ava.+(shaw))

  // nothing mysterious
  println(1 + 2)
  println(1.+(2))

  // ALL OPERATORS ARE METHODS
  // including +, -, !, ?, prefix notation (unary operator)

  // prefix notation
  val x = -1 // equivalent with 1.unary_-

  // postfix notation (not good)
  val y = 1.unary_-

  println(!shaw)
  println(shaw.unary_!)

  // postfix (closer to natural language, but nobody cares)
  println(shaw.isAlive)
  println(shaw isAlive)

  // apply: extremely special in scala
  // breaks the barrier between OOP and functional programming
  println(shaw.apply())
  println(shaw()) // equivalent, call as if a function!
}
