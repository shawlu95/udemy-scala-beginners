## Functional Programming

- use function as "first-class element", as if plain value
- to support for-comprehension, the class must support: filter, map, flatmap

## Variance

- Covariant (+A): F[Cat] can be used where F[Animal] is expected.
- Invariant (A): F[Cat] and F[Animal] are unrelated.
- Contravariant (-A): F[Animal] can be used where F[Cat] is expected.

## Null & Exception

- handle null with Option
- handle exception with `Try[T]`
  - call method in `Try`: `Try(unsafeMethod())`
  - Try could return a `Failure` or a `Success`
  - avoid try-catch spaghetti code

## Pattern Matching

- immutable, useful to represent data object
- come with hashCode, equals, str method
- `sealed` class must be exhaustive in pattern matching

```scala
val animal = ...
animal match {
  case Dog(some) => println("...")

}
```
