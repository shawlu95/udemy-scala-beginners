package lectures.part4.patternmatching

import lectures.part2.oop.{MyList, Cons, Empty}


object AllThePatterns extends App {
  // 1 - constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "a number"
    case "Scala" => "THE scala"
    case true => "Truth"
    case AllThePatterns => "A singleton object"
  }

  // 2 - match anything
  // 2.1 wildcard
  val matchAnything = x match {
    case _ =>
  }

  // 2.2 variable
  val matchAVariable = x match {
    case something => s"I've found $something"
  }

  // 3 - tuples
  val aTuple = (1, 2)
  val matchTuple = aTuple match {
    case (1, 1) =>
    case (something, 2) => println(f"Use later: $something")
  }

  val nestedTuple = (1, (2, 3))
  val matchNested = nestedTuple match {
    case (_, (2, v)) => println(f"Very powerful $v")
  }

  // 4 - constructor pattern & case classes
  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
  val matchAList = aList match {
    case Empty =>
//    case Cons(head, Cons(subhead, subtail)) =>
  }

  // 5 - list pattern
  val aStandardList = List(1, 2, 3, 42)
  val standardListMatch = aStandardList match {
    case List(1, _, _, _) => // called "extractor" for list
    case List(1, _*) => // list of arbitrary length starting with 1
    case 1 :: List(_) => // infix pattern
    case List(1, 2, 3) :+ 42 => // also infix pattern
  }

  // 6 - type specifier
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => // explicit type specifier
    case _ =>
  }

  // 7 - name binding
  // error: cannot be used as an extractor in a pattern because it lacks an unapply or unapplySeq method
//  val nameBindingMatch = aList match {
//    case nonEmptyList @ Cons(_, _) => // name binding => use the name later
//    case Cons(1, rest @ Cons(2, _)) => // name binding inside nested pattern
//  }

  // 8 - multi-patterns
//  val multipattern = aList match {
//    case Empty | Cons(0, _) => // compound pattern (multi-pattern)
//  }

  // 9 - if guards
//  val secondElementSpecial = aList match {
//    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 =>
//  }

  // warning: type erasure due to backward compatibility requirement
  // IDE warn: the type test for List[String] cannot be checked at runtime
  val numbers = List(1, 2, 3)
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => "list of str"
    case listOfNumbers: List[Int] => "list of numbers"
    case _ => ""
  }
}
