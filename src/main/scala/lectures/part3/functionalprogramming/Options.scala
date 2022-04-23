package lectures.part3.functionalprogramming

import scala.util.Random

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

  /* Exercise
  */
  val config: Map[String, String] = Map(
    "host" -> "127.0.0.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "connected"
  }

  object Connection {
    val random = new Random(System.nanoTime())
    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  // API might or might not return connection
  // try until a connection is established
  /*
  if (h != null)
    if (p != null)
      return Connection(h, p)
  return null
  */
  val host = config.get("host")
  val port = config.get("port")
  val connection = host.flatMap(h => port.flatMap(p => Connection(h, p)))

  /*
  if (c != null)
    return c.connect
  return null
  */
  val status = connection.map(c => c.connect)

  /*
  if (status == nul) println(None
  else print Some(status.get)
  */
  println(status)

  // if (status != null) print(status
  status.foreach(println)

  // chain all steps, careful with nesting here
  println("chain all steps")
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      .map(connection => connection.connect))
    .foreach(println)

  // for-comprehension: either host, port, conn is none, return none
  val yolo = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
  println("for-comprehension")
  yolo.foreach(println)
}
