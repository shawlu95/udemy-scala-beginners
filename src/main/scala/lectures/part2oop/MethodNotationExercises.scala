package lectures.part2oop

import scala.language.postfixOps

object MethodNotationExercises extends App {
  /*
  1. overload + operator
  2. add age to Person class, with unary operator to increment age
  3. add a "learns" method, and "learnsScala" method
  4. overload apply method
  */
  class Person(name: String, val age: Int = 0, favMovie: String) {
    def unary_+ : Person = new Person(name, age + 1, favMovie)
    def +(nickname: String): Person = new Person(s"$name ($nickname)", age, favMovie)
    def apply(): String = s"Hi, my name is $name and I like $favMovie"
    def apply(n: Int): String = s"$name watched $favMovie $n times"
    def learns(thing: String) = s"$name is learning $thing"
    def learnsScala = this.learns("Scala")
  }

  val shaw = new Person("Shaw", 27, "Pulp Fiction")
  println((shaw + "DJ")())

  println(+shaw) // means shaw.unary_+()
  println(shaw.unary_+)
  println((+shaw).age)

  println(shaw learns "Cooking")
  println(shaw learnsScala)

  println(shaw(3))
}
