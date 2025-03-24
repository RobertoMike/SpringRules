# Welcome to Different validation

This annotation checks if two or more fields have a different value from each other.
<br>In order to work correctly, the usage of the <b>@DifferentAs</b> annotation is required.

### Usage examples:

Here are some examples, going from the most basic to the more advanced.

#### Comparing one field

This example is basic, only comparing two fields

```java
import io.github.robertomike.jakidate.validations.objects.Different;
import io.github.robertomike.jakidate.validations.objects.DifferentAs;

@Different // This annotation is necessary to use this validation 
class Example {
    @DifferentAs // Always needs to have at least a @DifferentAs annotation, this will be our condition field.
    String oldEmail;

    @DifferentAs // Always needs to have at least a field to validate.
    String newEmail;
}

// In this example, the validation will pass.
Example validationPassedExample = new Example(
    "oldEmail@example.com", // "oldEmail" field value.
    "newEmail@example.com" // "newEmail" field has a different value from "oldEmail" field, so the validation will pass.
);

// In this example, the validation will not pass.
Example validationRejectedExample = new Example(
    "myEmail@example.com", // "oldEmail" field value.
    "myEmail@example.com" // "newEmail" field has the same value as "oldEmail" field, so the validation will not pass.
);
```

#### Comparing multiple fields using the same condition

In this example we are going to validate more than one field, using the same condition field to validate all the elements.

```java
import io.github.robertomike.jakidate.validations.objects.Different;
import io.github.robertomike.jakidate.validations.objects.DifferentAs;

@Different
class Example {
    @DifferentAs
    String oldEmail;

    @DifferentAs
    String newEmail;

    @DifferentAs
    String otherRandomEmail;
}

// In this example, all the fields have different values, so the validation will pass.
Example validationPassedExample = new Example(
    "oldEmail@example.com",
    "newEmail@example.com",
    "otherRandomEmail@example.com"
);

// In this example, field 2 has the same value as field 3, so the validation will not pass and an error message will be added, indicating the fields name that have the same value.
Example validationRejectedExample = new Example(
    "oldEmail@example.com",
    "myEmail@example.com",
    "myEmail@example.com"
);
```

#### Comparing multiple fields using the multiple conditions for each field

In this example we are going to validate more than one field, but this time we are going to use different conditions for each field we want to validate.
<br> For each new condition, we need to specify a different <b>key</b> and use it also for the field we want to validate.

```java
import io.github.robertomike.jakidate.validations.objects.Different;
import io.github.robertomike.jakidate.validations.objects.DifferentAs;

@Different
class Example {
    @DifferentAs //^ When no key is specified, it uses "default" as key.
    String oldEmail;

    @DifferentAs //^ No need to specify the key in this case, it will use "default".
    String newEmail;

    @DifferentAs("password") //^ We specify the key used for the condition field.
    String oldPassword;

    @DifferentAs("password") //^ We specify the same key as the condition used to validate this field.
    String newPassword;
}

// In this example, all the fields that belongs to the same group have different values, so the validation will pass.
Example validationPassedExample = new Example(
    "oldEmail@example.com",
    "newEmail@example.com",

    //^ These fields have the same value as the fields above, but they belong to a different group, so the validation will pass.
    "oldEmail@example.com",
    "newEmail@example.com"
);
```