import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

public class PdfFinder {

  private static final byte[] PDF_SIG = { 0x25, 0x50, 0x44, 0x46, 0x2d };

  public static void main(String[] args) throws IOException {
    Path dir = Path.of("/test/");

    // check files by file ending
    try (Stream<Path> paths = Files.walk(dir)) {
      paths.filter(Files::isRegularFile)
        .map(Path::toString)
        .filter(p -> p.endsWith(".pdf"))
        .forEach(System.out::println);
    }

    // check files by signature bytes
    try (Stream<Path> paths = Files.walk(dir)) {
      paths.filter(Files::isRegularFile)
        .filter(PdfFinder::isPdf)
        .forEach(System.out::println);
    }
  }
  
  // helper to check PDF according to its byte signature
  public static boolean isPdf(Path p) {
    try (InputStream in = Files.newInputStream(p)) {
      byte[] sig = in.readNBytes(5);
      return Arrays.equals(sig, PDF_SIG);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

}
