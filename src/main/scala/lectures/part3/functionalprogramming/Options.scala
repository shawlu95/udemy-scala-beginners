package lectures.part3.functionalprogramming

// Avoid null pointer exception!
// Never do null-check manually
object Options extends App {
  val firstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None
  println(firstOption)

  // unsafe API
  def unsafeMethod(): String = null

  // Wrong: "Some" should always have valid value
  // val res = Some((unsafeMethod()))

  // correct: Option
  val res = Option(unsafeMethod()) // return Some or None
  println(res)

  // chained method
  def backupMethod(): String = "A valid result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  // design better API to handle option
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A valid result")

  val betterResult = betterUnsafeMethod() orElse betterBackupMethod()

  // Functions on options
  println(firstOption.isEmpty) // false if have value
  println(noOption.isEmpty) // true
  println(firstOption.get) // UNSAFE: can throw NPE

  // map, flatMap, filter
  println(firstOption.map(_ * 2)) // returns Some(4)
  println(firstOption.filter(x => x > 10)) // becomes None
  println(firstOption.flatMap(x => Option(x * 10))) // returns Some(40)
}
