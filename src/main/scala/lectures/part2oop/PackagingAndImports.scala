package lectures.part2oop

import playground.JavaPlayground

// can import in group
import playground.{Bailey, Ava => Human}

// use alias in case of namespace conflict
import java.util.Date
import java.sql.{Date => sqlDate}

// packages usually map to directory structures (part2oop is subpackage of lectures)
// code inside this file belongs to the part2oop package
object PackagingAndImports {

  def main(args: Array[String]): Unit = {
    // package members are accessible by name!
    val writer = new Writer("Shaw", "Lu", 1995)

    // can import another package and access foreign member
    val play = new JavaPlayground

    // can use fully qualified name
    val another = new playground.JavaPlayground

    // Scala only: package object
    println(speedOfLight)
    drink

    val dog = new playground.Bailey
    val human = new Human()
    println(dog.name)
    println(human.name)

    // resolve naming conflict
    val date1 = new Date()
    val date2 = new java.sql.Date(2022, 4, 17)
    val date3 = new sqlDate(2022, 4, 17)

    // default import
    // java.lang (string, object, exception)
    // scala (int, nothing, function, basic stuff)
    // scala.Predef (println, ???)
  }
}
