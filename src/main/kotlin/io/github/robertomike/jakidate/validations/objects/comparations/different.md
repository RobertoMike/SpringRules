# Welcome to Different validation

This validation checks that two or more fields are different.

## Usage

This example is basic, only comparing two fields

```kotlin
import io.github.robertomike.jakidate.validations.objects.Different
import io.github.robertomike.jakidate.validations.objects.DifferentAs

@Different // This is necessary to use this validation 
class User(
    @DifferentAs
    val oldEmail: String,
    @DifferentAs
    val newEmail: String,
)
```

Here you can see how to compare more than two fields

```kotlin
import io.github.robertomike.jakidate.validations.objects.Different
import io.github.robertomike.jakidate.validations.objects.DifferentAs

@Different // This is necessary to use this validation 
class User(
    @DifferentAs
    val oldEmail: String,
    @DifferentAs
    val newEmail: String,
    @DifferentAs
    val otherRandomEmail: String,
)
```

This example is more complex, where you compare two or more different groups of fields

```kotlin
import io.github.robertomike.jakidate.validations.objects.Different
import io.github.robertomike.jakidate.validations.objects.DifferentAs

@Different // This is necessary to use this validation 
class User(
    @DifferentAs
    val oldEmail: String,
    @DifferentAs
    val newEmail: String,
    // The annotation parameter allow to separate the logic for more than one group
    @DifferentAs("password")
    val oldPassword: String,
    @DifferentAs("password")
    val newPassword: String,
)
```

