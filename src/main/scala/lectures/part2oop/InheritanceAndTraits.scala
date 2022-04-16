package lectures.part2oop

object InheritanceAndTraits {
  // prevent extension:
  // 1. make method final
  // 2. make class final
  // 3. "sealed" the class, can only be extended in current file
  //    Cat and Dog are the only subclasses allowed
  
  // final class cannot be extended
  final class Plant {}
  sealed class Animal {
    val creatureType = "wild"
    def eat = println("nomnom")

    // only usable in this Animal class
    private def poop = println("poop")

    // allow subclass to call
    protected def sleep = println("sleep")

    final def die = print("died")
  }

  class Cat extends Animal {
    def wakeup = {
      sleep
      println("wake up")
    }
  }

  class Dog extends Animal {
    // override field
    override val creatureType: String = "Pet"

    // override method
    override def eat: Unit = {
      // call super class
      super.eat
      println("drink drink")
    }

    // cannot override final method/class
    // override def die
  }

  // override directly in constructor
  // or accept a param to override field
  class Golden(override val creatureType: String) extends Animal
  class Husky(dogType: String) extends Animal {
    override val creatureType: String = dogType
  }

  // constructor: must call superclass constructor first
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  // can call superclass' alternative constructor
  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  // Scala offers SINGLE class inheritance
  def main(args: Array[String]): Unit = {
    val cat = new Cat
    cat.eat
    cat.wakeup
    println(cat.creatureType)

    val dog = new Dog
    dog.eat
    println(dog.creatureType)

    val golden = new Golden("Retriever")
    println(golden.creatureType)

    // type substitution/polymorphism: instances are derived instance of animal
    // instance knows the derived subclass method/field to call
    val unknownAnimal: Animal = new Husky("K9")
    unknownAnimal.eat
  }

}
