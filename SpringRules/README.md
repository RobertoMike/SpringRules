# About SpringRules

SpringRules is a library that includes all the validations used in [Jakidate](#jakidate) library, plus some specific validations for Spring and some utility classes for the error messages.

## Links
- [How to install](#how-to-install)
- [Validations](#validations)

## How to install

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
    implementation 'io.github.robertomike:springrules:1.0.0'
}
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