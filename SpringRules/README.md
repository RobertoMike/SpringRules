# About SpringRules

SpringRules is a library that includes all the validations used in the [Jakidate](../) library, plus some specific validations for Spring and some utility classes for the error messages.

## Links
- [How to install](#how-to-install)
- [Configuration](#configuration)
  -  [Disable the controller advice](#disable-the-controller-advice)
  -  [Structures for validations errors](#structures-for-validations-errors)
- [Validations](#validations)
  - [Database Validations](#database-validations)
  - [File Validations](#file-validations)

## How to install

If you use Spring boot 2 then go to the [javax branch](../../javax/SpringRules)

Maven
```xml
<dependency>
    <groupId>io.github.robertomike</groupId>
    <artifactId>springrules</artifactId>
    <version>2.0.8</version>
</dependency>
```
Gradle
```gradle
dependencies {
    implementation 'io.github.robertomike:springrules:2.0.8'
}
```

## Configuration
By default, SpringRules has two controller advice actives, this controller advice will catch the 
validation exception and transform the errors in a more beautiful structure.

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

### Disable the controller advice
If you want to disable the controller advice, you can do it putting these two properties on false like this
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
import io.github.robertomike.springrules.validations.database.Exists;
import io.github.robertomike.springrules.validations.database.Unique;
import io.github.robertomike.springrules.validations.file.Extension;
import io.github.robertomike.springrules.validations.file.FileSize;

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