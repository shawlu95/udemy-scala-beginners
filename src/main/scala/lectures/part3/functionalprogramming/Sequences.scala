package lectures.part3.functionalprogramming

import scala.util.Random

object Sequences extends App {

  // Seq has factory method that constructs seq of subclass
  val aSequence = Seq(2, 4, 3, 1)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2)) // return 3
  println(aSequence ++ Seq(5, 6, 7)) // concatenation
  println(aSequence.sorted)

  // Ranges are also sequences
  val aRange: Seq[Int] = 1 to 10
  aRange.foreach(println)

  // instead of recursion, do this 10 times
  (1 to 10).foreach(x => print("ha! "))

  // List - immutable
  val aList = List(1, 2, 3)
  val prepended = 0 :: aList
  val alsoPrepend = 0 +: aList
  val appended = aList :+ 5
  println(prepended)
  println(alsoPrepend)
  println(appended)

  // a list of 5 element with same value
  val apple = List.fill(5)("apple")
  println(apple)
  println(apple.mkString(","))

  // Array
  val numbers = Array(1, 2, 3, 4)
  val threeElem = Array.ofDim[Int](3) // allocate memory
  println(threeElem) // not readable
  threeElem.foreach(println) // all 0

  // Array mutation
  numbers(2) = 0 // syntax sugar: numbers.update(2, 0)
  println(numbers.mkString(" "))

  // arrays and seq: array (java-native) can be converted to seq!
  val numbersSeq: Seq[Int] = numbers // implicit conversion, very advanced
  println(numbersSeq) // WrappedArray(1, 2, 0, 4)

  // Vectors
  val vector: Vector[Int] = Vector(1, 2, 3)
  println(vector)

  // performance: vector vs list, return wall time
  val maxRuns = 1000
  val cap = 1000000
  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val start = System.nanoTime()
      collection.updated(r.nextInt(cap), r.nextInt())
      System.nanoTime() - start
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to cap).toList
  val numbersVec = (1 to cap).toVector

  // update first element is very quick
  // update middle element very slow
  println(getWriteTime(numbersList))

  // need to traverse tree to update (small depth)
  // cons: need to replace 32-element chunk
  println(getWriteTime(numbersVec))

}
