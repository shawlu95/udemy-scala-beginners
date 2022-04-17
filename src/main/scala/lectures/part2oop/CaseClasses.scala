package lectures.part2oop

object CaseClasses {
  case class Person(name: String, age: Int)

  // case object is like case class except it does not have
  // companion object (itself is an object)
  case object China {
    def name: String = "People's Republic of China"
  }

  def main(args: Array[String]): Unit = {
    val shaw = new Person("Shaw", 27)

    // case class promotes all params to field
    println(shaw.name)

    // case class makes sensible string
    println(shaw.toString)
    println(shaw) // delegate to toString

    // equal and hashcode implemented!
    val dup = new Person("Shaw", 27)
    println(shaw == dup) // returns true

    // easy to copy
    val copy = shaw.copy()

    // can overwrite field when copy
    val older = shaw.copy(age = 28)

    // have companion object
    val person = Person

    // have factory method (e.g. apply method)
    // in practice, don't use "new" when instantiate case class
    val ava = Person("Ava", 26)

    // serializable! suitable for network communication

    // extractor patterns. can be used in pattern matching
  }
}
