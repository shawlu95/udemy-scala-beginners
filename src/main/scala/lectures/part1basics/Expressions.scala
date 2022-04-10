package lectures.part1basics

object Expressions extends App {
  // An expression
  val x = 1 + 2
  println(x)

  // math: + - * /
  // bit: & | ^
  // shift: << >> >>>
  // equality: ==
  // non-equal: !=
  // compare: >, >=, <, <=
  // negation: !
  // logical: && ||
  println(2 + 3 * 4)

  var aVariable = 2
  aVariable += 5 // side-effect
  println(aVariable)

  aVariable -= 5 // side-effect
  println(aVariable)

  aVariable *= 5 // side-effect
  println(aVariable)

  aVariable /= 5 // side-effect
  println(aVariable)

  // instruction vs expression
  // instruction: tell computer to do something (executed)
  // expression: assign a value (evaluated)

  // IF is an Expression, not instruction
  val aCondition = true
  val aConditionedValue = if(aCondition) 5 else 3
  println("aConditionedValue:" + aConditionedValue)
  println(if(aCondition) 5 else 3) // evaluate to 5

  // loop is discouraged in scala, only produce side effect (return Unit)
  // only works for imperative language like Java ans C
  // everything in scala is an expression
  var i = 0
  val aUnit = while (i < 3) {
    println(i)
    i += 1
  }

  // "Unit" is like void, nothing meaningful.
  // Can only hold "()"
  val aWeirdValue = (aVariable = 3)
  println(aWeirdValue)

  // side effect: println(), while, reassigning

  // Code blocks are special
  // Code block is an Expression
  // Value of block is the value of the last expression
  // Can have its own variable scope
  val aCodeBlock = {
    val y = 2
    val z = y + 1
    if (z > 2) "hello" else "bye"
  }

  // not allowed. Z is in code block
  // val anotherValue = z + 1
}
