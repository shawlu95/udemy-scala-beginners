package playground;

public class JavaPlayground {
    public static void main (String args[]) {
        // This is a class-level functionality, which scala does NOT have
        // Scala has "object" instead, which has static-like functionality
        System.out.println(Person.N_EYES);
    }
}

class Person {
    public  static  final int N_EYES = 2;
}