package lectures.part1.basics

object ValuesVariablesTypes extends App {
  // val cannot be reassigned (immutable)
  val x: Int = 42
  println(x)

  // type can be inferred
  val y = 24
  println(y)

  // semi-colon is required when writing multiple
  // statements on the same line but not encouraged
  val aString: String = "piece of text"; val bString: String = "another piece of text";
  println(aString)

  val aBoolean: Boolean = true
  val bBoolean: Boolean = false

  // Single character in single quote
  val aChar: Char = 'A'

  // 4 bytes
  val anInt: Int = x

  // 2 bytes
  val aShort: Short = 6511

  // 8 bytes
  val aLong: Long = 6666666666L

  // decimal float must have suffix 'f'
  val aFloat: Float = 2.0f

  // double has no suffix 'f'
  val aDouble: Double = 3.1415926

  // variables
  var aVariable: Int = 4
  aVariable = 50 // side effect
  println(aVariable)
}
