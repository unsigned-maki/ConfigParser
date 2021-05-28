# Config
Very simple config parser class for Java.

## How To

Example:

```java
public class ConfigTest {
    public static void main(String[] args) {
        Config config = new Config("Path/to/config.file"); // Creates instance of Config class which is an extension of the HashMap class
        config.read(); // reads the config file to HashMap
        config.put("key", "value"); // set value of key
        config.get("key"); // get value of key
        config.write(); // writes HashMap to config file
    }
}
```

Config File Format:

```
key=value
```

Note: Seperator of key and value is "=" by default can be changed by passing a second string argument to the Config class constructor.

```java
Config config = new Config("Path/to/config.file", "-"); // "-" will be used as seperator
```
