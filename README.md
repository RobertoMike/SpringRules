Jakidate
================


## Validations:
- Password: A constraint validator that checks if a password meets certain requirements, such as minimum and maximum length, digit, letter, and special character requirements.
- NotCompromisedPassword: A constraint validator that checks if a password has been compromised by checking it against the Pwned Passwords API.
- MinDigits: A constraint validator that checks if a number has at least a minimum number of digits.
- MaxDigits: A constraint validator that checks if a number has at most a maximum number of digits.
- Ip: A constraint validator that checks if a string is a valid IP address.
- Different: A constraint validator that checks if two fields are different.
- Same: A constraint validator that checks if two fields are the same.

Usage
To use these validation classes, simply annotate the fields or properties of your class with the corresponding annotation. For example:

```
data class User(
    @NotCompromisedPassword
    @Password(minLength = 8, maxLength = 256, digit = true, letters = true, uppercaseAndLowercase = true, specialCharacters = true)
    val password: String,

    @MinDigits(2)
    val minDigits: Int,

    @MaxDigits(10)
    val maxDigits: Int,

    @Ip
    val ipAddress: String,

    @Different
    val different: String,

    @Different
    val different: String,

    @Same
    val same: String,

    @Same
    val same: String
)
```

