package lectures.part3.functionalprogramming

object MapFlatmapFilterFor extends App {
  val list = List(1, 2, 3)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  val toPair = (x: Int) => List(x, x + 1)
  // List(1, 2, 2, 3, 3, 4)
  println(list.flatMap(toPair))

  // print all combinations between two lists
  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List('R', 'G')

  val combinations = numbers.flatMap(n => chars.flatMap(c => colors.map(color => "" + c + n + color)))
  println(combinations)

  // for each
  list.foreach(println)

  // for -comprehension
  val forCombinations = for {
    n <- numbers if n % 2 == 0 // only keep even numbers
    c <- chars
    color <- colors
  } yield "" + c + n + color
  println(forCombinations)

  for {
    n <- numbers
  } println(n)
}
