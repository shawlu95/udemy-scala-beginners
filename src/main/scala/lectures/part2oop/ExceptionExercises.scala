package lectures.part2oop

object ExceptionExercises {

  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathException extends RuntimeException("Division by zero!")
  object Calculator {
    def add(x: Int, y: Int): Int = {
      val res = x + y
      if (x > 0 && y > 0 && res < 0)
        throw new OverflowException
      else if (x < 0  && y < 0 && res > 0)
        throw new UnderflowException
      else
        res
    }

    def subtract(x: Int, y: Int): Int = {
      val res = x - y
      if (x > y && y < 9 && res < 0)
        throw new OverflowException
      else if (x < 0 && y > 0 && res > 0)
        throw new UnderflowException
      else
        res
    }

    def multiply(x: Int, y: Int): Int = {
      val res = x * y
      if (x > 0 && y > 0 && res < 0)
        throw new OverflowException
      else if (x < 0 && y < 0 && res < 0)
        throw new OverflowException
      else if (x > 0 && y < 0 && res > 0)
        throw new UnderflowException
      else if (x < 0 && y > 0 && res > 0)
        throw new UnderflowException
      else
        res
    }

    def divide(x: Int, y: Int): Int = {{
      if (y == 0)
        throw new MathException
      x / y
    }}
  }

  def main(args: Array[String]): Unit = {
    // throw exception

    // out of memory
    // val array: Array[Int] = Array.ofDim(Int.MaxValue)

    // stack overflow
    // def infinite: Int = 1 + infinite
    // val yo = infinite

    println(Calculator.divide(2, 0))
  }

}
