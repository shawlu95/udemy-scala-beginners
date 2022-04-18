package lectures.part2.oop

/* Works for class and trait, abstract and concrete */
object AnonymousClass extends App {
  abstract class Animal {
    def eat: Unit
  }

  // declare an anonymous class and instantiate
  val funny: Animal = new Animal {
    override def eat: Unit = println("yoo")
  }
  // class lectures.part2oop.AnonymousClass$$anon$1
  println(funny.getClass)

  // doesn't have to be abstract
  class Person(name: String) {
    def greet: Unit = println(s"Hi my name is $name")
  }

  // pass in required constructor params
  val shaw = new Person(name = "shaw") {
    override def greet: Unit = println("works fine")
  }
  // class lectures.part2oop.AnonymousClass$$anon$2
  println(shaw.getClass)

}
