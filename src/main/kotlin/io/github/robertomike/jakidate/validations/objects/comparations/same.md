# Welcome to Same validation

This validation checks that two or more fields are the same.

## Usage

This example is basic, only comparing two fields

```kotlin
import io.github.robertomike.jakidate.validations.objects.Same
import io.github.robertomike.jakidate.validations.objects.SameAs

@Same // This is necessary to use this validation 
class User(
    @SameAs
    val email: String,
    @SameAs
    val emailConfirmation: String,
)
```

Here you can see how to compare more than two fields

```kotlin
import io.github.robertomike.jakidate.validations.objects.Same
import io.github.robertomike.jakidate.validations.objects.SameAs

@Same // This is necessary to use this validation 
class User(
    @SameAs
    val email: String,
    @SameAs
    val emailConfirmation: String,
    @SameAs
    val superEmailConfirmation: String,
)
```

This example is more complex, where you compare two or more different groups of fields

```kotlin
import io.github.robertomike.jakidate.validations.objects.Same
import io.github.robertomike.jakidate.validations.objects.SameAs

@Same // This is necessary to use this validation 
class User(
    @SameAs
    val email: String,
    @SameAs
    val emailConfirmation: String,
    // The annotation parameter allow to separate the logic for more than one group
    @SameAs("password")
    val password: String,
    @SameAs("password")
    val passwordConfirmation: String,
)
```

