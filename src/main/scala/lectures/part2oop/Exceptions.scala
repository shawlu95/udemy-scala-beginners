package lectures.part2oop

object Exceptions {
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No integer")
    else 42

  // throwable class: Exception and Error
  class MyException extends Exception

  def main(args: Array[String]): Unit = {
    val x: String = null
    // println(x.length) crashes

    // instantiate the NullPointerException class
    // and throw the instance (class extends throwable)
    // val weird: String = throw new NullPointerException

    // catch exception
    val potentialFail = try {
      // should return Int
      getInt(true)
    } catch {
      // return Unit or appropriate value!
      case e: RuntimeException => {
        println("Caught RTE")
        100 // return an int, so potentialFail always return int
      }
    } finally {
      // optional: executed no matter what
      // does NOT influence return type (only side effect)
      println("Finally")
    }

    // rarely need customization in practice
    val exception = new MyException
    throw exception
  }
}
