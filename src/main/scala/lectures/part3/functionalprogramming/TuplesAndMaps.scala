package lectures.part3.functionalprogramming

import scala.annotation.tailrec

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

  // data can be lost when keys overlap
  val dupbook = Map(("Shaw", 0), ("shaw", 1))
  println(dupbook.map(pair => pair._1.toLowerCase -> pair._2))

  // add a person to the network
  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set())

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)
    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)
    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  // remove friendships, then remove the person from map
  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    def removeHelper(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if (friends.isEmpty) networkAcc
      else removeHelper(friends.tail, unfriend(networkAcc, person, friends.head))

    // remove relationship
    val unfriended = removeHelper(network(person), network)

    // remove person
    unfriended - person
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Mary"), "Bob")

  println(network)
  println(friend(network, "Mary", "Bob"))
  println(unfriend(friend(network, "Mary", "Bob"), "Bob", "Mary"))
  println(remove(friend(network, "Mary", "Bob"), "Bob"))

  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val pair = friend(people, "Bob", "Jim")
  val net = friend(pair, "Bob", "Mary")
  println(net)

  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if (!network.contains(person)) 0
    else network(person).size

  println(nFriends(net, "Bob"))
  println(nFriends(net, "Mary"))

  def mostFriends(network: Map[String, Set[String]]): String =
    // max by key, return name
    net.maxBy(pair => pair._2.size)._1
  println(mostFriends(net))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
    // network.filterKeys(k => network(k).isEmpty).size
    // network.filter(pair => pair._2.isEmpty).size
    // network.count(pair => pair._2.isEmpty)
    network.count(_._2.isEmpty)
  println(nPeopleWithNoFriends(net))

  // Check if there exists path from a to b
  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    @tailrec
    def bfs(target: String, considered: Set[String], discovered: Set[String]): Boolean = {
      if (discovered.isEmpty) false
      else {
        val person = discovered.head
        if (person == target) true
        else if (considered.contains(person)) bfs(target, considered, discovered.tail)
        else bfs(target, considered + person, discovered.tail ++ network(person))
      }
    }
    bfs(b, Set(), network(a) + a)
  }

  println(socialConnection(net, "Mary", "Jim"))
  println(socialConnection(network, "Mary", "Bob"))
}
