package lectures.part1.basics

object CBNvsCBV extends App {
  def calledByValue(x: Long): Unit = {
    println("by value " + x)
    println("by value " + x)
  }

  // =>: tell compiler it is called by name
  def calledByName(x: => Long): Unit = {
    println("by name " + x)
    println("by name " + x)
  }

  // param is computed before, the value is passed in
  calledByValue(System.nanoTime())

  // expression is passed literally into function
  // and evaluated every time inside function when referenced
  calledByName(System.nanoTime())

  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)

  // crashes with stack overflow
  // printFirst(infinite(), 34)

  // y is not used, so y is never evaluated. No crash
  printFirst(34, infinite())
}
