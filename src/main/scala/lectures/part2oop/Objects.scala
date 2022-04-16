package lectures.part2oop

object Objects {

  // unlike class, object does NOT receive params
  object Person {
    // static / class-level member/functions
    val N_EYES = 2
    def canFly: Boolean = false

    // factory method that can build person
    def from(mother: Person, father: Person): Person = new Person("Elon Musk")
    def apply(mother: Person, father: Person): Person = new Person("Elon Musk")
  }

  // companion pattern: declare object and class in
  // the same scope with the same name. Scala is more
  // object-oriented than any other language
  class Person(name: String) {
    // instance-level member/functions
  }

  // Scala application is a scala object with method
  // def main(args: Array[String]): Unit is required is not extending App
  def main(args: Array[String]): Unit = {
    // Some conceptual difference from Java
    println(Person.N_EYES)
    println(Person.canFly)

    // Scala object = SINGLETON INSTANCE
    // An object is its own type and its ONLY instance
    val me = Person
    val you = Person
    println(me == you) // point to the same instance!

    val he = new Person("Shaw")
    val she = new Person("Ava")
    println(he == she) // point to different instance

    // use factory method
    val elon = Person.from(he, she)

    // very popular
    val musk = Person(he, she)
  }
}
