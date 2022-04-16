package lectures.part1basics

object DefaultArgs extends App {
  def tailRecFactorial(n: Int, acc: Int = 1): Int =
    if (n <= 1) acc
    else tailRecFactorial(n - 1, n * acc)

  // acc always starts with 1, might declare a default
  val fact10 = tailRecFactorial(10, 1)

  val fact11 = tailRecFactorial(11)

  println(fact10)
  println(fact11)

  def savePicture(format: String = "jpg", width: Int, height: Int): Unit = println("Save picture")
  savePicture("jpg", 1024, 1024)

  // caveat: default arg params cannot come before (same as Python)
  // savePicture(1024, 1024)

  // solution 1: define default value args afterward
  // solution 2: always pass in argument (defeat purpose of default arg)
  // solution 3: name argument when calling function
  savePicture(width = 1024, height = 1024)
}
