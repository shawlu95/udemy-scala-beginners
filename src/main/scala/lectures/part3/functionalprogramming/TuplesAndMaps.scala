package lectures.part3.functionalprogramming

object TuplesAndMaps extends App {
  // tuples = finite ordered "list"
  // "new" optional
  val aTuple = new Tuple2(2, "hello scala") // Tuple1[Int, String]
  val bTuple = (2, "hello scala")

  // _1 is the first element
  println(aTuple._1) // 2

  // copy and overwrite (just like case classes)
  println(aTuple.copy(_2 = "bye java"))
  println(aTuple.swap) // ("hello scala", 2)

  // Maps - keys -> values
  val aMap: Map[String, Int] = Map()

  // a more useful map, can use arrow syntax sugar
  val phonebook = Map(("shaw", 650), ("ava", 666), "bailey" -> 2)
  println(phonebook)

  // map ops
  println(phonebook.contains("shaw"))
  println(phonebook("shaw"))
  // println(phonebook("crash")) // NoSuchElementException

  // add default value to map
  val phonebook2 = phonebook.withDefaultValue(911)

  // add a pairing and create a new map
  // map is IMMUTABLE
  val newPair = "chicken" -> 100
  val newBook = phonebook + newPair

  // functionals on maps
  // map a paring to a new pairing, transform key to lowercase
  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2))
  // filter keys and
  println(phonebook.view.filterKeys(x => x.startsWith("s")).toMap)
  // map values
  println(phonebook.view.mapValues(number => "01 " + number).toMap)

  // List((shaw,650), (ava,666), (bailey,2))
  println(phonebook.toList)
  // Map(Daniel -> 555)
  println(List(("Daniel", "555")).toMap)

  // used a lot by data scientist
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))
}
