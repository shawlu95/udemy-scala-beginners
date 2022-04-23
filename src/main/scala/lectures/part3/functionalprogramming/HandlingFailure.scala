package lectures.part3.functionalprogramming

import scala.util.{Try, Failure, Success, Random}

// If return None, use Option
// If throw exception, use Try

object HandlingFailure extends App {
  // create success and failure explicitly
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("FAIL!"))

  println(aSuccess)
  println(aFailure)

  // The "try" constructs success and failure automatically
  // wrap the exception in a failure object if caught
  def unsafeMethod(): String = throw new RuntimeException("NO STRING")
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  // syntax sugar
  val bFail = Try {
    // code that might throw
  }

  // utilities
  println(potentialFailure.isSuccess)
  println(potentialFailure.isFailure)

  // orElse, works like option
  // here's how to use unsafe API
  def backupMethod(): String = "Valid result"
  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))
  println(fallbackTry)

  // if designing API, wrap code inside Try
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("A valid result")
  val betterFallback = betterBackupMethod() orElse betterBackupMethod()

  // map, flatMap, filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10)) // becomes a failure: NoSuchElementException

  /*
  Exercise: connect to a unreliable API written by someone
  Goal: print the html if obtained
  */
  val hostname = "localhost"
  val port = "8080"
  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html></html>"
      else throw new RuntimeException("Failed to connect")
    }

    // wrap with Try
    def safeGet(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())
    def getConnection(host: String, port: String): Connection =
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Failed to connect")

    // wrap with Try
    def safeSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }

  val possible = HttpService
    .safeSafeConnection(hostname, port)
    .flatMap(conn => conn.safeGet("www"))
  possible.foreach(renderHTML)

  // chain version
  HttpService
    .safeSafeConnection(hostname, port)
    .flatMap(conn => conn.safeGet("www"))
    .foreach(renderHTML)

  // comprehension equivalent
  for {
    connection <- HttpService.safeSafeConnection(hostname, port)
    html <- connection.safeGet("www")
  } renderHTML(html)

  /* Imperative equivalent:
  * try {
  *   conn = HttpService.getConnection(host, port)
  *   try {
  *     conn.get("www")
  *   } catch (exception) {
  *     ...
  *   }
  * } catch (exception) {
  *   ...
  * }
  * */
}
