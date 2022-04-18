package lectures.part2.oop

object Generics {
  class MyList[+A] { // covariant collection
    // use type "A" inside the class! A generic class
    
    // covariant type A occurs in contravariant position in type A of parameter element
    // def add(element: A): MyList[A] = ???
    
    // fix: add a dog to cat list, the list become a list of animal
    def add[B >: A](element: B): MyList[B] = ???
  }
  
  // can have multiple 
  class MyMap[Key, Value] 
  
  // trait can also be parametrized
  trait Set[B]
  
  object MyList {
    // generic method
    def empty[A]: MyList[A] = new MyList[A]
  }

  // Variance problem: does list of cat extends list of animal
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1. Covariance: list of Cat extends list of Animals
  class CovariantList[+A]
  
  // 2. Invariant list
  class InvariantList[A]

  // 3. contravariant list
  class ContraVariantList[-A]
  class Trainer[-A]
  
  // bounded type 
  // A must be sub-type of Animal
  class Cage[A <: Animal](animal: A)
  class Car
  // accept super type
  class Meal[A >: Animal](animal: A)
  
  
  def main(args: Array[String]): Unit = { 
    
    val listOfIntegers = new MyList[Int]
    val listOfStrings = new MyList[String]
    
    val emptyIntList = MyList.empty[Int]
    
    // covariance example
    val animal: Animal = new Cat
    val animalList: CovariantList[Animal] = new CovariantList[Cat]
    // bad, dog will pollute cat list
    // animalList.add(new Dog)
    
    // invariance example
    val invariant: InvariantList[Animal] = new InvariantList[Animal]
    
    // contra-variance example: not intuitive
    val contraList: ContraVariantList[Cat] = new ContraVariantList[Animal]
    // want a trainer of cat, provide a trainer of any animal (more reasonable)
    val trainer: Trainer[Cat] = new Trainer[Animal]
    
    // Cage can only contain animal subclass
    val cage = new Cage(new Dog)
    // val cage = new Cage(new Car) // not allowed
    
  }

}
