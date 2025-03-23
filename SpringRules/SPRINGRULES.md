# About SpringRules

SpringRules is a library...

## Links
- [How to install](#how-to-install)
- [Validations](#validations)

## How to install

Installation...

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

    @Exists(repository = Repository)
    Long userId;

    @Unique(repository = Repository)
    Long userName;

    @Extension({"jpg", "jpeg", "png"})
    MultipartFile file1;

    @FileSize(min = 1, max = 0)
    MultipartFile file2;
};
```

## Validations supported types???: