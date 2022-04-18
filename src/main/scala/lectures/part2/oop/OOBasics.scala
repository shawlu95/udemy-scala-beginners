package lectures.part2.oop

object OOBasics extends App {
  val person = new Person(name = "Shaw", age = 27)
  println(person)

  // they are clas params, which are NOT fields (member)
  // println(person.age)
  println(person.x)

  person.greet("Ava")
  person.greet()

  // prepend "val" to make param a field
  val dog = new Dog(name = "Bailey")
  println(dog.name)
}

// constructor requires two params
class Person(name: String, age: Int = 0) {
  // this is a member field
  val x = 2

  // execute when instantiation (entire block of code is executed)
  println(1 + 3)

  // use "this" to access this instance's field
  def greet(name: String): Unit = println(s"${this.name} says hi $name")

  // "this" is implied if there's no ambiguity
  // overloading: methods with same name but different signatures
  def greet(): Unit = println(s"Hi I am $name")

  // not allowed if only difference is return type
  // def greet(): Int = 1

  // overloading constructor, call primary constructor
  // not recommended, better to define a default param in primary constructor
  def this(name: String) = this(name, 0)
  def this() = this("Shaw")
}

class Dog(val name: String)

