package lectures.part2oop

/*
* Trait vs abstract classes
* 1. Class can have abstract and non-abstract members, so can trait
* 2. Traits do not have constructor params
* 3. Can extend only one class, but multiple traits
* 4. Traits describe "behavior"; abstract classes are type of "thing"
* */
object AbstractDataTypes {

  // abstract class CANNOT be instantiated
  abstract class Animal {
    // not supplying value
    val creatureType: String
    val ancestor: String = "unknown"

    // not supplying method body
    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"

    override def eat: Unit = println("nom nom")
  }

  // traits: have abstract fields and methods
  trait Carnivore {
    def eat(animal: Animal): Unit // abstract
    val preferred: String = "meat" // non-abstract
  }

  trait ColdBlood

  // can have as many traits as needed
  class Crocofile extends Animal with Carnivore with ColdBlood {
    val creatureType: String = "croc"
    def eat: Unit = println("waaaah")
    def eat(animal: Animal): Unit = s"I'm eating ${animal.creatureType}"
  }

  def main(args: Array[String]): Unit = {
    val dog = new Dog
    val croc = new Crocofile
    croc.eat(dog)
  }
}
