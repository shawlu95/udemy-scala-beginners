package lectures.part4.patternmatching

// introduced in Scala 3, very Pythonic
object BracelessSyntax {
  val anIfExpression = if (2 > 3) "bigger" else "smaller"

  // java stype
  val anIfExpression2 =
    if (2 > 3) {
      "bigger"
    } else {
      "smaller"
    }

  val anIfExpression3 =
    if (2 > 3) "bigger"
    else "smaller"

  // scala3: require higher indentation!
  val anIfExpression4 =
    if 2 > 3 then
      "bigger"
    else
      "smaller"

  val anIfExpression5 =
    if 2 > 3 then
      // imaginary code block, with its own scope
      // last line is returned
      val res = "bigger"
      res
    else
      val res = "smaller"
      res

  // scala 3 one-liner
  val anIfExpression6 = if 2 > 3 then "bigger" else "smaller"

  /* For Comprehension */
  val aForComprehension = for {
    n <- List(1, 2, 3)
    s <- List("black", "white")
  } yield s"$n$s"

  // scala 3
  val aForComprehension2 =
    for
      n <- List(1, 2, 3)
      s <- List("black", "white")
    yield s"$n$s"

  // pattern matching
  val meaningOfLife = 42
  val aPatternMatch = meaningOfLife match {
    case 1 => "one"
    case 2 => "double"
    case _ => "else"
  }

  // scala 3: higher indentation than the "match" word
  val aPatternMatch2 = meaningOfLife match
    case 1 => "one"
    case 2 => "double"
    case _ => "else"

  // braceless method
  def compute(arg: Int): Int = {
    val res = 50


    res * 2
  }

  def compute2(arg: Int): Int =
    val res = 50

    // still in the method block!
    // because of indentation
    res * 2

  // define class, traits, object, enum
  class Animal {
    def eat(): Unit =
      println("I'm eating")
   }

  class Plant: // expect class body
    def eat(): Unit =
      println("I'm growing")

    def grow(): Unit =
      println("I'm growing")

    def sleep(): Unit =
      println("foo")
      println("bar")
    end sleep // end token for method recommended
  end Plant // end token for class

  // anonymous class
  val specialAnimal = new Animal:
    override def eat(): Unit = println("eat a lot")
    
  /*
  * indentation: strictly larger indentation than previous line
  * It does not mean display to the right hand side
  * 3 spaces + 2 tabs > 2 spaces + 2 tabs
  * 3 spaces + 2 tabs > 3 spaces + 1 tab
  * 3 tabs + 2 spaces ??? 2 tabs + 3 spaces, confused
  * Note: never mix spaces with tabs! */

  def main(args: Array[String]): Unit = {
    println(anIfExpression)
    println(anIfExpression2)
    println(anIfExpression3)
    println(anIfExpression4)
    println(anIfExpression5)
  }

}
