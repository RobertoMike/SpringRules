# About SpringRules

SpringRules is a library that includes all the validations used in [Jakidate](../) library, plus some specific validations for Spring and some utility classes for the error messages.

## Links
- [How to install](#how-to-install)
- [Configuration](#configuration)
  -  [Disable the controller advices](#disable-the-controller-advices)
  -  [Structures for validations errors](#structures-for-validations-errors)
- [Validations](#validations)
  - [Database Validations](#database-validations)
  - [File Validations](#file-validations)

## How to install

If you use Spring boot 3 then go to the [master branch](../../master/SpringRules)

Maven
```xml
<dependency>
    <groupId>io.github.robertomike</groupId>
    <artifactId>springrules</artifactId>
    <version>1.0.0</version>
</dependency>
```
Gradle
```gradle
dependencies {
    implementation 'io.github.robertomike:springrules:2.0.0'
}
```

## Configuration
Per default Spring Rules has two controllers advices actives, this controllers advice will catch the 
validations exception and transforms the errors in a more beautiful structure.

### Structures for validations errors
There are three possible structures that you can receive based on the property 'spring-rules.violation-body'

Examples:

- SINGLE_MESSAGE -> 
```json 
[
  {
    "field": "name",
    "message": "Some error"
  },
  {
    "field": "user.name",
    "message": "Some error"
  }
] 
```
- MULTIPLE_MESSAGE -> 
 ```json 
[
  {
    "field": "name",
    "messages": [
      "Some error"
    ]
  }
] 
```
- SUBFIELDS_MESSAGES -> 
```json 
[
  {
    "field": "user",
    "messages": [
      "Some error"
    ],
    "subfields": [
      {
        "field": "name",
        "messages": [
          "Some error"
        ]
      }
    ]
  }
] 
```

### Disable the controller advices
If you want to disable the controller advices you can do it putting this two properties on false like this
```properties
# To disable the controller advice for the exception ConstraintViolationException
spring-rules.controller-advice.constraint-violations=false
# To disable the controller advice for the exception MethodArgumentNotValidException
spring-rules.controller-advice.method-argument-not-valid=false
```


## Validations:

### Database Validations:
- Exists: Checks if a value exists in the database through the repository.
- Unique: Checks if a value is unique in the database through the repository.

### File Validations:
- Extension: This annotation is used to validate the extension of a file based on a list of allowed extensions.
- FileSize: This annotation is used to validate the size of a file.

Usage
To use these validation classes, simply annotate the fields or properties of your class with the corresponding annotation. For example:

```java
class User{

    @Exists(UserRepository.class) // The default method is findById, but it can be changed.
    Long userId; // The type of the value must be the same as the type of the field used in the query.

    @Unique(value = UserRepository.class, method = "findByUserName") // The default method is findById, but it can be changed.
    Long userName; // The type of the value must be the same as the type of the field used in the query.

    @Extension({"jpg", "jpeg", "png"})
    MultipartFile file1;

    @FileSize(min = 1, max = 0)
    MultipartFile file2;
}
```

## If you think this library was useful to you...

[![coffee](../buy-me-coffee.png)](https://www.buymeacoffee.com/robertomike)