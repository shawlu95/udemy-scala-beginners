package lectures.part2.oop

object OOExercises extends App {
  val author = new Writer("Charles", "Dickens", 1812)
  val novel = new Novel("Oliver Twist", 1837, author)

  println(s"Author age ${novel.authorAge()}")
  println(s"Is written by ${author.fullName()}: ${novel.isWrittenBy(author)}")

  val imposter = new Writer("Charles", "Dickens", 1812)
  println(s"Is written by imposter: ${novel.isWrittenBy(imposter)}")

  val counter = new Counter()
  counter.increment().print
  counter.increment().increment().increment().print
  counter.increment(10).print
}

class Novel(name: String, year: Int, author: Writer) {
  def authorAge(): Int = year - author.year
  def isWrittenBy(author: Writer): Boolean = this.author == author
  def copy(newYear: Int): Novel = Novel(this.name, newYear, this.author)
}

class Writer(val firstname: String, val surname: String, val year: Int) {
  def fullName(): String = f"$firstname $surname"
}

class Counter(val count: Int = 0) {
  def currentCount(): Int = count // useless, can directly access count
  def increment(): Counter = {
    println("increment")
    Counter(count + 1)
  } // Immutable. Instances cannot be modified

  def decrement(): Counter = {
    println("decrement")
    Counter(count - 1)
  } // Immutable. Instances cannot be modified

  // recursion
  def increment(n: Int = 1): Counter = {
    println("increment")
    if (n <= 0) this
    else increment().increment(n - 1)
  }

  // recursion
  def decrement(n: Int = 2): Counter = {
    println("decrement")
    if (n <= 0) this
    else decrement().decrement(n - 1)
  }

  def print = println(count)
}
