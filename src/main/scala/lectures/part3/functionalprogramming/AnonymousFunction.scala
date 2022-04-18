package lectures.part3.functionalprogramming

object AnonymousFunction {
  /*
  * Instantiate a function is still very object-like*/
  val doubler = new Function[Int, Int] {
    override def apply(v1: Int): Int =  v1 * 2
  }

  // the Scala-way: syntactic sugar/lambda function
  val doubler2 = (x: Int) => x * 2
  val doubler3: Int => Int = (x: Int) => x * 2

  // compiler helps with type inference
  val doubler4: Int => Int = x => x * 2

  // multiple params, put in parenthesis
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // no param
  val sleep: () => Int = () => 3

  // common style, not always preferred
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // More syntactic sugar:
  val niceIncrementer1: Int => Int = (x: Int) => x + 1
  val niceIncrementer2: Int => Int = _ + 1

  val niceAdder1: (Int, Int) => Int = (a, b) => a + b
  val niceAdder2: (Int, Int) => Int = _ + _

  // anonymous high-order version
  val anonyAdd = (x: Int) => (y: Int) => x + y

  def main(args: Array[String]): Unit = {
    // lambda function must be called with parentheses (lmao)
    println(sleep) // an instance of function
    println(sleep()) // execute the function and return 3
    println(anonyAdd(10)(3))
  }
}
