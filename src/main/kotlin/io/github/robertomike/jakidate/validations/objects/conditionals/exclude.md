# Welcome to Exclude validation

This annotation allows you to exclude fields based on another field of the object.
<br>In order to work correctly, the usage of the <b>@ExcludeIf</b> or <b>@ExcludeUnless</b> annotation is required.

## @ExcludeIf:
This annotation is put it on top of two or more fields. There is a condition field and n° quantity of fields that will be excluded when the condition field is true.

### Usage examples:

Here are some examples, going from the most basic to the more advanced.

#### Excluding one field

Only excluding one field based on condition field that can support different types: <b>Boolean, Numbers, String and *Custom.</b>


```kotlin
import io.github.robertomike.jakidate.validations.objects.Exclude
import io.github.robertomike.jakidate.validations.objects.ExcludeIf

@Exclude // This annotation is necessary to use this validation.
class Example(
    @field:ExcludeIf(true) // Always needs to have at least an ExcludeIf annotation set to true, this will be our condition field.
    val condition: Boolean,
    @field:ExcludeIf // Always needs to have at least a field to validate.
    val password: String?
)

// In this example, the validation will pass.
val validationPassedExample = Example(
    true, // "condition" field value.
    "", // "password" field is empty, so the validation will pass.
)

// In this example, the validation will not pass.
val validationRejectedExample = Example(
    true, // "condition" field value.
    "myPassword", // "password" field has a value, so the validation will not pass and an error message will be added.
)

// When "condition" is set to false.
val validationPassedExample = Example(
    false, // "condition" field value is now set to false, therefore the condition is now reversed.
    "", // "password" field has a value, so the validation will pass.
)

```

#### Using a different type for the condition field

In this example we are going to use the condition field with different types.
<br>[Here](#true-supported-types) you can find the supported values for each type.

```kotlin
import io.github.robertomike.jakidate.validations.objects.Exclude
import io.github.robertomike.jakidate.validations.objects.ExcludeIf

@Exclude
class Example(
    @field:ExcludeIf(true)
    val condition: String, // condition field is of type String.
    @field:ExcludeIf
    val password: String?,
)

// In this example, the validation will pass.
val validationPassedExample = Example(
    "1", // "condition" field value.
    "", // "password" field is empty, so the validation will pass.
)

// In this example, the validation will not pass.
val validationRejectedExample = Example(
    "0", // "condition" field value.
    "myPassword", // "password" field has a value, so the validation will not pass and an error message will be added.
)
```

#### Excluding multiple fields using the same condition

In this example we are going to validate more than one field, using the same condition field to validate all the elements.

```kotlin
import io.github.robertomike.jakidate.validations.objects.Exclude
import io.github.robertomike.jakidate.validations.objects.ExcludeIf

@Exclude
class Example(
    @field:ExcludeIf(true)
    val control: Boolean, // Clearly, you can name the condition field as you want.
    @field:ExcludeIf
    val firstName: String?,
    @field:ExcludeIf
    val isPremiumSubscriber: Boolean?,
    @field:ExcludeIf
    val age: Number?,

)

val validationPassedExample = Example(
  true, // "control" field value.
  "", // "password" field is empty, so the validation will pass.
  null, // "isPremiumSubscriber" field is null, so the validation will pass.
  null // "age" field is null, so the validation will pass.
)
```

#### Excluding multiple fields using the multiple conditions for each field

In this example we are going to validate more than one field, but this time we are going to use different conditions for each field we want to validate.
<br> For each new condition, we need to specify a different <b>key</b> and use it also for the field we want to validate.

```kotlin
import io.github.robertomike.jakidate.validations.objects.Exclude
import io.github.robertomike.jakidate.validations.objects.ExcludeIf

@Exclude
class Example(
    @field:ExcludeIf(true) //^ When no key is specified, it uses "default" as key.
    val firstNameCondition: String,
    @field:ExcludeIf //^ No need to specify the key in this case, it will use "default".
    val firstName: String?,
    @field:ExcludeIf(true, "isPremiumSubscriber") //^ We specify the key used for the condition field.
    val isPremiumSubscriberCondition: String,
    @field:ExcludeIf("isPremiumSubscriber") //^ We specify the same key as the condition used to validate this field.
    val isPremiumSubscriber: Boolean?,
    @field:ExcludeIf(true, "age") //^ We specify the key used for the condition field.
    val ageCondition: Number,
    @field:ExcludeIf("age") //^ We specify the same key as the condition used to validate this field.
    val age: Number?,
)

val validationPassedExample = Example(
  "true", // "firstNameCondition" field value.
  null, // "firstName" field is null, so the validation will pass.
  "yes", // "isPremiumSubscriberCondition" field value.
  null, // "isPremiumSubscriber" field is null, so the validation will pass.
  1, // "ageCondition" field value.
  null // "age" field is null, so the validation will pass.
)
```

#### Setting checkEmpty to false

This example shows how the validations work when <b>checkEmpty</b> is set to false, in this case the validation should pass even if the fields are empty, and it will not pass when a field is null.

```kotlin
import io.github.robertomike.jakidate.validations.objects.Exclude
import io.github.robertomike.jakidate.validations.objects.ExcludeIf

@Exclude(checkEmpty = false) // The checkEmpty parameter it's always set to true by default, in this case we set it to false.
class Example(
    @field:ExcludeIf(true)
    val condition: Boolean,
    @field:ExcludeIf
    val firstName: String?,
    @field:ExcludeIf
    val lastName: String?,
    @field:ExcludeIf
    val age: Number?,

)

val validationPassedExample = Example(
  true, // "condition" field value.
  null, // "password" field is null, so the validation will pass.
  "", // This should not pass when checkEmpty is set to false, because it's empty and not null.
  null // "age" field is null, so the validation will pass.
)
```

### @ExcludeUnless:

This annotation works in the same way as the @ExcludeIf annotation, but this time the fields will <b>not</b> be excluded in case the condition field is true.

#### Excluding one field

Only excluding one field based on condition field that can support different types: <b>Boolean, Numbers, String and *Custom.</b>

```kotlin
import io.github.robertomike.jakidate.validations.objects.Exclude
import io.github.robertomike.jakidate.validations.objects.ExcludeUnless
@Exclude
class Example(
    @field:ExcludeUnless(true)
    val condition: String,
    @field:ExcludeUnless
    val password: String?
)

val validationPassedExample = Example(
  "1", // "condition" field value.
  "password123" // "password" field is not null, so the validation will pass.
)

val validationNotPassedExample = Example(
  "true", // "condition" field value.
  null // "password" field is null, so the validation not will pass.
)

// When "condition" is set to false.
val validationPassedExample = Example(
  "false", // "condition" field value is now set to false, therefore the condition is now reversed.
  "", // "password" field has a value, so the validation will pass.
)
```

#### Excluding multiple fields, using also @ExcludeIf

```kotlin
@Exclude
class Example(
    @field:ExcludeUnless(true) //^ When no key is specified, it uses "default" as key.
    val control: Boolean,
    @field:ExcludeUnless
    val password: String?,
    @field:ExcludeIf(true, "email")
    val condition: String,
    @field:ExcludeIf(key = "email")
    val email: String?,
)
```
#### condition values:

- ##### <a id="true-supported-types">Accepted values for true:</a>
    - String: "yes", "on", "true", "y", "1"
    - Boolean: true
    - Number: 1

- ##### <a id="false-supported-types">Accepted values for false:</a>
    - String: "no", "off", "false", "n", "0"
    - Boolean: false
    - Number: 0