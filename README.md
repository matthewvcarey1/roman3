# Noddy Integer to Roman Numeral Converter in Java
In this implementation a different algorithm uses a json config file.

There seem to be several ways of implementing large roman numbers and config files gives us the possibility of switching between them.

I have opted for the using the vinculum to represent value 1000 times its original. So X (10) with one vinculum is 10,000.

This implementation represents roman numbers a bit more like arabic numbers with different strings for each digit that get concatinated together. Just that zero is an empty string.

### Usual Gradle instructions
To build and test
```bash
./gradlew build
```

To create a jar file that can be executed with java -jar filename.jar
```bash
./gradlew jar
```