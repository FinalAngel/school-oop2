Summary Process
===============

Changelog
---------

* [22.02.2021] Added JUnit & Maven build tool
  ```
  test/JunitTest.java
  src/main/java
  src/test/java
  garget/ (contains kompiled files)
  pom.xml
  ```

Maven
-----

* Konvention vor Konfiguration
* Ordnerstruktur vorgegeben

* run `mvn compile`
* then `java -cp target/classes/ hello.HelloWorld`
* run `mvn test compile`
* run `mvn clean`
