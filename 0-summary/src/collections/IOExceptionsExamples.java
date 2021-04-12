import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class IOExceptionsExamples {
  
  public static void main(String[] args) {
    // accessToFiles();
  }

  public static void accessToFiles() throws IOException {
    Path homeAbs = Path.of("C:\\", "Users", "Angelo");
    Path homeRel = Path.of("src", "main", "java"); // relative path

    System.out.println(homeAbs.getParent());
    System.out.println(homeRel.getFileName());

    if (Files.exists(homeAbs)) {
      System.out.println("Yippikaye");
    }
    // more operations
    // Files.copy()
    // Files.move()
    // Files.delete()

    Stream<Path> files = Files.list(homeAbs);
    // e.g. get only directories and list their absolute path
    files
      .filter(Files::isDirectory)
      .map(Path::toAbsolutePath)
      .forEach(System.out::println);
    files.close();
    
    // get full folder structure
    Files.walk(homeRel)
      .forEach(System.out::println);
  }

  public static void exceptions() throws IOException {
    Path home = Path.of("C:\\", "Users", "Angelo");

    Files.copy(home.resolve(".gitconfig"), Path.of("C:\\", "User"));
  }

  public static void tryCatch() {
    Path src = Path.of("C:\\", "Users", "Angelo");
    Path dest = Path.of("C:\\", "Users", "Angelo");

    try {
      Files.copy(src, dest);
    } catch (IOException e) {
      System.out.println("Fehler beim Kopieren");
    } finally {
      // last task, e.g. close files
    }

    // can also pass to try directly
    try (Stream<Path> files = Files.list(src)) {
      files
        .filter(Files::isDirectory)
        .map(Path::toAbsolutePath)
        .forEach(System.out::println);
      files.close();
    } catch (IOException e) {
      System.out.println("Fehler beim Kopieren");
    }
  }

  public static void inOutputStrem() throws IOException {
    // files are always stored as bits/bytes
    Path path = Path.of("src", "main", "java");

    // READING
    InputStream in = Files.newInputStream(path);
    // now we can read each byte
    int b;
    while ((b = in.read()) != -1) {
      System.out.println(b);
    }

    OutputStream out = Files.newOutputStream(path);
    // WRITING
    try {
      byte[] bytes = new byte[10];
      out.write(bytes);
    } catch (IOException e) {
      System.out.println("Ein Fehler");
    } finally {
      // close in finally
      out.close();
    }
  }

  public static void moreExamples() {
    try {
      URL url = new URL("https://www.fhnw.ch");
      InputStream in1 = url.openStream();

      Process proc = new ProcessBuilder("bash", "-c", "ls").start();
      InputStream in2 = proc.getInputStream();

      System.out.println(in1);
      System.out.println(in2);
    } catch(IOException e) {
      // meh
    }
  }

  public static void textReading () throws IOException {
    Path path = Path.of("src", "main", "java");
    InputStream in = Files.newInputStream(path);

    // make sure to define the unicode standard
    Reader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
    BufferedReader buffered = new BufferedReader(reader);
    Stream<String> lines = buffered.lines();
    System.out.println(lines);

    byte[] bytes = in.readAllBytes();
    String text = new String(bytes, StandardCharsets.UTF_8);
    System.out.println(text);

    Scanner scanner = new Scanner(path, StandardCharsets.UTF_8);
    Stream<String> tokens = scanner.tokens();
    System.out.println(scanner);
    System.out.println(tokens);

    // other methods for reading lines and outputting
    BufferedReader reader2 = Files.newBufferedReader(path);
    Stream<String> lines2 = reader2.lines();
    Stream<String> lines3 = Files.lines(path);
    List<String> lines4 = Files.readAllLines(path);
    String text2 = Files.readString(path);
    System.out.println(lines2);
    System.out.println(lines3);
    System.out.println(lines4);
    System.out.println(text2);

    // writing to a file
    OutputStream out = Files.newOutputStream(path);
    BufferedWriter writer = new BufferedWriter(
      new OutputStreamWriter(out, StandardCharsets.UTF_8)
    );
    writer.write("This is going into a file");
    // alternatives
    BufferedWriter writer2 = Files.newBufferedWriter(path);
    writer2.write("This too.");
    List<String> lines5 = List.of("Line 1", "Line 2", "Line 3");
    Files.write(path, lines5);
  }

}
