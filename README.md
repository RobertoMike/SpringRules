# About Jakidate

Jakidate is a library that contains <b>50+ annotations</b> that you can use to validate your forms/configurations. It extends the existing Jakarta/Javax annotations by adding various sets of annotations to simplify the work.
<br>If you are not familiar with Jakarta validation, you can check the documentation [here](https://beanvalidation.org/).

## Links
- [How to install](#how-to-install)
- [Validations](#validations)

## How to install

If you only need the jakidate library, you can use this

Maven
```xml
<dependency>
    <groupId>io.github.robertomike</groupId>
    <artifactId>jakidate</artifactId>
    <version>2.0.5</version>
</dependency>
```
Gradle
```gradle
dependencies {
    implementation 'io.github.robertomike:jakidate:2.0.5'
}
```

If you want to use it on Spring boot 2, 3, we recommend you to check this: [Spring Rules](./SpringRules)

If you want to use a javax version instead of jakarta, you need to see the [javax branch](../../tree/javax)

## Validations:

### Boolean Validations:
- Accepted: Checks if a value is accepted. [Supported types](#accepted-supported-types)
- Declined: Checks if a value is declined. [Supported types](#declined-supported-types)

### Collections Validations:
- Contains: Checks if a value is contained in a list of allowed values. [Supported types](#contain-supported-types)
- Distinct: Checks if a collection has no duplicate elements.

### Colors Validations:
- Hex: Checks if a string is a valid hex color.
- Hsl: Checks if a string is a valid Hsl color.
- Oklch: Checks if a string is a valid Oklch color.
- Rgb: Checks if a string is a valid Rgb color.

### Documents validations:
- FiscalCode: Checks if a string is a valid Italian Fiscal Code.
- ISIN: Checks if a string is a valid International Securities Identification Number (ISIN).
- ISSN: Checks if a string is a valid International Standard Serial Number (ISSN).

### Numbers Validations:
- Between: Checks if a number is between a minimum and maximum value.  [Supported types](#between-supported-types)
- MaxDigits: Checks if a given Number value has the maximum number of digits specified by the annotation.
- MinDigits: Checks if a given Number value has at least the minimum number of digits specified by the annotation.
- MultipleOf: Checks if a value is a multiple of a given number. [Supported types](#multipleof-supported-types)

### Comparations Validations:
- Different: Checks if two or more fields are different. [More details](../../src/main/kotlin/io/github/robertomike/jakidate/validations/objects/comparations/different.md)
- Same: Checks if two or more fields are the same. [More details](../../src/main/kotlin/io/github/robertomike/jakidate/validations/objects/comparations/same.md)

### Conditionals
- Exclude: Checks if a value is not contained in a list of forbidden values. [More details](../../src/main/kotlin/io/github/robertomike/jakidate/validations/objects/conditionals/exclude.md)
- Required: Checks if a value is required based on another field of the object. [More details](../../src/main/kotlin/io/github/robertomike/jakidate/validations/objects/conditionals/required.md)

### Password Validations:
- NotCompromisedPassword: Checks if a password has not been compromised in a data breach.
- Password: Checks if a password meets certain requirements, such as minimum and maximum length, digit, letter, and special character requirements.

### String Validations:
- Alpha:
  - Alpha: Checks if a string contains only letters. 
  - AlphaNum: Checks if a string contains only letters or numbers.
  - AlphaNumSymbol: Checks if a string contains only letters, numbers, or symbols.
  - AlphaSymbol: Checks if a string contains only letters or symbols.
  - IsNumeric: Checks if a string contains only numbers.
  - NumSymbol: Checks if a string contains only numbers or symbols.
  - Symbol: Checks if a string contains only symbols.
- Cases:
  - CamelCase: Checks if a string is in camelCase format.
  - CamelSnakeCase: Checks if a string is in camel_snake_case format.
  - KebabCase: Checks if a string is in kebab-case format.
  - LowerCase: Checks if a string is in lowercase format.
  - PascalCase: Checks if a string is in PascalCase format.
  - ScreamingKebabCase: Checks if a string is in SCREAMING-KEBAB-CASE format.
  - ScreamingSnakeCase: Checks if a string is in SCREAMING_SNAKE_CASE format.
  - SnakeCase: Checks if a string is in snake_case format.
  - TrainCase: Checks if a string is in TrainCase format.
  - UpperCase: Checks if a string is in UPPERCASE format.
- End:
  - DoesntEndWith: Checks if a string doesn't end with a specified value.
  - EndsWith: Checks if a string ends with a specified value.
- Nullable:
  - NullOrNotBlank: Used to validate a field of type string, the validation passes if the field is not passed or when it is null, but if it is passed, it must not be blank.
  - NullOrNotEmpty: Used to validate a field of type string, the validation passes if the field is not passed or when it is null, but if it is passed, it must not be empty.
- Start:
    - DoesntStartWith: Checks if a string doesn't start with a specified value.
    - StartsWith: Checks if a string starts with a specified value.
- Ascii: Checks if a string contains only ASCII characters.

### Web Validations:
- MacAddress: Checks if a string is a MacAddress in a correct format.
- RelativePath: Checks if a string is a valid path in a variety of contexts, such as a URL path or a file path.
- Url: Checks if a given string is a URL in the correct format.
- Ip:
  - Ip: Checks if a string is a valid IP, IPv4, or IPv6 address.
  - Ipv4: Checks if a string is a valid IPv4 address.
  - Ipv6: Checks if a string is a valid IPv6 address.

Usage
To use these validation classes, simply annotate the fields or properties of your class with the corresponding annotation. For example:

```java
import io.github.robertomike.jakidate.validations.passwords.NotCompromisedPassword;
import io.github.robertomike.jakidate.validations.passwords.Password;
import io.github.robertomike.jakidate.validations.numbers.MinDigits;
import io.github.robertomike.jakidate.validations.numbers.MaxDigits;
import io.github.robertomike.jakidate.validations.web.ip.Ip;
import io.github.robertomike.jakidate.validations.objects.comparations.Different;
import io.github.robertomike.jakidate.validations.objects.comparations.Same;

class User {

    @NotCompromisedPassword
    @Password(minLength = 8, maxLength = 256, digit = true, letters = true, uppercaseAndLowercase = true, specialCharacters = true)
    String password;

    @MinDigits(2)
    Int minDigits;

    @MaxDigits(10)
    Int maxDigits;

    @Ip
    String ipAddress;

    @Different
    String different;

    @Different
    String different;

    @Same
    String same;

    @Same
    String same;
    
    // The annotations can be used also inside the generic types of collections.
    Set<@FiscalCode @NotNull String> fiscalCode;
}
```

## Validations supported types:

- ##### <a id="accepted-supported-types">Accepted:</a> String, Boolean, Number.
- ##### <a id="between-supported-types">Between:</a> Int, Double, Long, Float, Short, Byte, BigInteger, BigDecimal, Date, LocalDateTime.
- ##### <a id="contain-supported-types">Contain:</a> String, Int, Long, BigInteger, BigDecimal, Short, Byte, Double, Float, Map.
- ##### <a id="declined-supported-types">Declined:</a> Boolean, String, Number.

If you have any ideas on how to implement the library or new annotations that you would like to see in the future, please [open an issue](https://github.com/robertomike/jakidate/issues) with your suggestions.

## If you think this library was useful to you...

[![coffee](./buy-me-coffee.png)](https://www.buymeacoffee.com/robertomike)