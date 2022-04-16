package lectures.part1basics

object StringOps extends App {
  val str: String = "Yo, I'm so fucking bored"

  println(str.charAt(0))

  // begin index inclusive, end index exclusive (like Python)
  println(str.substring(4))
  println(str.substring(4, 7))

  println(str.split(" ").toList)

  // return true
  println(str.startsWith("Yo"))

  println(str.replaceFirst("Yo", "Hi"))
  println(str.replaceAll(" ", "!"))

  println(str.toLowerCase())
  println(str.length)

  // scala runs on java virtual machine
  // all string funcs are available in java
  val aStrNumber = "45"
  val aNumber = aStrNumber.toInt

  // prepending. return "a45"
  println('a' +: aStrNumber)

  // appending. return "45z"
  println(aStrNumber :+ 'z')
  // println(aStrNumber +: 'z') // error!

  println(str.reverse)
  println(str.take(2)) // first two character

  // scala-specific: string interpolators
  // s-interpolator, like f"hello I'm {name}" in python
  // if passing param, no need for curly braces
  // if using braces, can write any expression
  val name  = "Shaw"
  val age = 23
  val greeting = s"I'm $name, ${age + 1} years old"
  println(greeting)

  // f-interpolator
  // for example, print 2-decimal precision
  val speed = 1.2f
  val myth = f"$name%s can shit $speed%2.2f per minute"
  println(myth)

  // type check in f-interpolator (compile fail)
  val x = 1.1f
  // val str = f"$x%3d"

  // raw-interpolator, no escape (e.g. new-line character)
  println(raw"This is a \nnew line")

  // injected variables DO get escaped
  val escaped = "This is a \nnew line"
  println(raw"$escaped")
}
