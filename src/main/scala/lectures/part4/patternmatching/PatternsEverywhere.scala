package lectures.part4.patternmatching

object PatternsEverywhere extends App {
  // 1. catches are actually pattern matching!
  try {
    // code
  } catch {
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => "npe"
    case _ => "other exceptions"
  }

  // equivalent to:
  /*
  * try {
    // code
  } catch {
    e match {
      case e: RuntimeException => "runtime"
      case npe: NullPointerException => "npe"
      case _ => "other exceptions"
    }
  }
  * */

  // 2. generators are also pattern matching
  val list = List(1, 2, 3, 4)
  val everyOnes = for {
    x <- list if x % 2 == 0
  } yield 10 * x

  val tuples = List((1, 2), (3, 4))
  val filterTuples = for {
    (first, second) <- tuples
  } yield first * second

  // 3. name binding is pattern matching
  val tuple = (1, 2, 3)
  val (a, b, c) = tuple
  println(b)

  val head :: tail = list
  println(head)
  println(tail)

  // 4. partial function literal (advanced)
  val mappedList = list.map {
    case v if v % 2 == 0 => v + " is even"
    case 1 => "the one"
    case _ => "others"
  }
  println(mappedList)

  val mappedList2 = list.map { x => x match {
      case v if v % 2 == 0 => v + "is even"
      case 1 => "the one"
      case _ => "others"
    }
  }
}
