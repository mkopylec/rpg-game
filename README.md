# Simple RPG game

An example application created using Domain-Driven Design technique for training purposes.

## Domain



## Usage
Every class that represents a model of source file is a subclass of `SourceFile`.
To create a physical file use `createSourceFile()` method.
To create a string representation of file content invoke `toString()`.
The generated source code often needs some kind of reformatting.

The following examples are **not a complete overview** of available methods.
They are just a simple demonstration of how to use the Source Gen-X tool.

### Generating a Java class
Creating a sample main class:
    
```java
Constant constant = new Constant("String", "GREETING", "\"Hello World!\"");

Method main = new Method(AccessModifier.PUBLIC, Modifier.STATIC, "void", "main")
        .addParameter(new Parameter("String[]", "args"))
        .setBody("\tSystem.out.println(GREETING);");

JavaClass mainClass = new JavaClass("sourcegenx.demo", "Application")
        .addField(constant)
        .addMethod(main);

mainClass.createSourceFile("/demo/src/main/java/sourcegenx/demo");
```
    
Output Application.java:

    package sourcegenx.demo;


    public class Application   {

        public static final String GREETING = "Hello World!";

        public static void main(String[] args) {
    	    System.out.println(GREETING);
        }
    }

### Generating a Java interface
Creating a sample DAO interface:

```java
Import imp = new Import("sourcegenx.demo.entities.User");
       
InterfaceMethod findMethod = new InterfaceMethod("User", "findById")
        .addParameter(new Parameter("long", "id"));
       
JavaInterface dao = new JavaInterface("sourcegenx.demo", "UserDao<User>")
        .addImport(imp)
        .addMethod(findMethod);
        
dao.createSourceFile("/demo/src/main/java/sourcegenx/demo", "UserDao");
```
        
Output UserDao.java:

    package sourcegenx.demo;

    import sourcegenx.demo.entities.User;

    public interface UserDao<User>  {


         User findById(long id);
    }

### Generating a Java enum
Creating a sample cardinal directions enum:

```java
JavaEnum directions = new JavaEnum("sourcegenx.demo", "CardinalDirection")
        .addValue(new EnumValue("NORTH"))
        .addValue(new EnumValue("WEST"))
        .addValue(new EnumValue("SOUTH"))
        .addValue(new EnumValue("EAST"));
        
directions.createSourceFile("/demo/src/main/java/sourcegenx/demo");
```
    
Output CardinalDirection.java:

    package sourcegenx.demo;


    public enum CardinalDirection  {

        NORTH,
        WEST,
        SOUTH,
        EAST;


    }
    
### Generating a Java annotation
Creating a sample secured annotation:

```java
Annotation target = new Annotation("Target")
        .addAttribute("value", "METHOD");

Annotation retention = new Annotation("Retention")
        .addAttribute("value", "RUNTIME");

AnnotationElement allowedRoles = new AnnotationElement("String[]", "allowedRoles")
        .setDefaultValue("{\"ADMIN\", \"CUSTOMER\"}");

JavaAnnotation secured = new JavaAnnotation("sourcegenx.demo", "Secured")
        .addImport(new Import("java.lang.annotation.Retention"))
        .addImport(new Import("java.lang.annotation.Target"))
        .addImport(new Import(Modifier.STATIC, "java.lang.annotation.ElementType.METHOD"))
        .addImport(new Import(Modifier.STATIC, "java.lang.annotation.RetentionPolicy.RUNTIME"))
        .addAnnotation(target)
        .addAnnotation(retention)
        .addElement(allowedRoles);

secured.createSourceFile("/demo/src/main/java/sourcegenx/demo");
```
    
Output Secured.java:

    package sourcegenx.demo;

    import java.lang.annotation.Retention;
    import java.lang.annotation.Target;
    import static java.lang.annotation.ElementType.METHOD;
    import static java.lang.annotation.RetentionPolicy.RUNTIME;

    @Target(METHOD)
    @Retention(RUNTIME)
    public @interface Secured {

        String[] allowedRoles() default {"ADMIN", "CUSTOMER"};
    }

### Generating a configuration properties file
Creating a sample properties file:

```java
ConfigProperties properties = new ConfigProperties()
        .addProperty(new Property("database.name", "test"))
        .addProperty(new Property("database.host", "localhost"));

properties.createSourceFile("/demo/src/main/resources", "application");
```

Output application.properties:

    database.name=test
    database.host=localhost

### Generating a configuration YAML file
Creating a sample YAML file:

```java
YamlNode root = new YamlNode("root")
        .addChild(new YamlNode("message.default").addValue("Some message"))
        .addChild(new YamlNode("message.special")
                        .addChild(new YamlNode("server").addValue("1"))
                        .addChild(new YamlNode("client").addValue("2"))
        );

ConfigYaml yaml = new ConfigYaml().addNode(root);

yaml.createSourceFile("/demo/src/main/resources", "application");
```

Output application.yaml:

    root:
      message.default: Some message
      message.special:
        server: 1
        client: 2

## More examples
See test specifications for more samples:

- [Java class spec](https://github.com/mkopylec/source-gen-x/blob/master/src/test/groovy/pl/allegro/tech/sourcegenx/core/java/JavaClassSpec.groovy)
- [Java interface spec](https://github.com/mkopylec/source-gen-x/blob/master/src/test/groovy/pl/allegro/tech/sourcegenx/core/java/JavaInterfaceSpec.groovy)
- [Java enum spec](https://github.com/mkopylec/source-gen-x/blob/master/src/test/groovy/pl/allegro/tech/sourcegenx/core/java/JavaEnumSpec.groovy)
- [Java annotation spec](https://github.com/mkopylec/source-gen-x/blob/master/src/test/groovy/pl/allegro/tech/sourcegenx/core/java/JavaAnnotationSpec.groovy)
- [Config properties spec](https://github.com/mkopylec/source-gen-x/blob/master/src/test/groovy/pl/allegro/tech/sourcegenx/core/config/ConfigPropertiesSpec.groovy)
- [Config YAML spec](https://github.com/mkopylec/source-gen-x/blob/master/src/test/groovy/pl/allegro/tech/sourcegenx/core/config/ConfigYamlSpec.groovy)

## License
Source Gen-X is published under [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).