package lectures.part2.oop

object Enums {

  // cannot instantiate Permissions
  // can only instantiate one of the enum value
  enum Permissions {
    case READ, WRITE, EXECUTE, NONE

    // add fields and methods
    def openDocument(): Unit =
      if (this == READ) println("opening")
      else println("Unauthorized")
  }

  enum PermissionsWithBits(bits: Int) {
    case READ extends PermissionsWithBits(4) // 100
    case WRITE extends PermissionsWithBits(2) // 010
    case EXECUTE extends PermissionsWithBits(1) // 001
    case NONE extends PermissionsWithBits(0) // 000
  }

  // can have companion object
  object PermissionsWithBits {
    // factory method, return one constant of enum
    def fromBits(bits: Int): PermissionsWithBits =
      PermissionsWithBits.NONE
  }

  def main(args: Array[String]): Unit = {
    val foo: Permissions = Permissions.READ
    foo.openDocument()

    // standard API
    // check permissions ordinal (in which enum is defined)
    println(foo.ordinal)

    // iterate all instances
    val all = PermissionsWithBits.values

    // create from string
    val bar: Permissions = Permissions.valueOf("WRITE")
    println(bar)
  }

}
