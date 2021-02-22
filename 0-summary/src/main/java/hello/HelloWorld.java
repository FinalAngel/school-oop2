package hello;

public class HelloWorld {

    public static void main(String[] args) {
        System.out.println(greeting("World"));
    }

    public static String greeting(String name) {
        return "Hello, " + name + "!";
    }
}
