# Welcome to Exclude validation

This annotation allows you to exclude fields based on another field of the object.
<br>In order to work correctly, the usage of the <b>@ExcludeIf</b> or <b>@ExcludeUnless</b> annotation is required.

## @ExcludeIf:
This annotation is put it on top of two or more fields. There is a condition field and n° quantity of fields that will be excluded when the condition field is true.

### Usage examples:

Here are some examples, going from the most basic to the more advanced.

#### Excluding one field

Only excluding one field based on condition field that can support different types: <b>Boolean, Numbers, String and *Custom.</b>

```java
import io.github.robertomike.jakidate.validations.objects.Exclude;
import io.github.robertomike.jakidate.validations.objects.ExcludeIf;

@Exclude // This annotation is necessary to use this validation.
class Example{

    @ExcludeIf(true) // Always needs to have at least an ExcludeIf annotation set to true, this will be our condition field.
    private Boolean condition;

    @ExcludeIf // Always needs to have at least a field to validate.
    private String password;
}

// In this example, the validation will pass.
Example validationPassedExample = new Example(
        true, // "condition" field value.
        "" // "password" field is empty, so the validation will pass.
);

// In this example, the validation will not pass.
Example validationRejectedExample = new Example(
        true, // "condition" field value.
        "myPassword" // "password" field has a value, so the validation will not pass and an error message will be added.
);

// When "condition" is set to false.
Example validationPassedExample = new Example(
        false, // "condition" field value is now set to false, therefore the validation is now deactivated.
        "myPassword" // "password" field has a value, but the validation is deactivated, so no error message will be added.
);
```

#### Using a different type for the condition field

In this example we are going to use the condition field with different types.
<br>[Here](#true-supported-types) you can find the supported values for each type.

```java
import io.github.robertomike.jakidate.validations.objects.Exclude;
import io.github.robertomike.jakidate.validations.objects.ExcludeIf;

@Exclude
class Example {
    
    @ExcludeIf(true)
    private String condition; // condition field is of type String.

    @ExcludeIf
    private String password;
}

// In this example, the validation will pass.
Example validationPassedExample = new Example(
        "1", // "condition" field value.
        "" // "password" field is empty, so the validation will pass.
);

// In this example, the validation will not pass.
Example validationRejectedExample = new Example(
        "0", // "condition" field value.
        "myPassword" // "password" field has a value, so the validation will not pass and an error message will be added.

);
```

#### Excluding multiple fields using the same condition

In this example we are going to validate more than one field, using the same condition field to validate all the elements.

```java
import io.github.robertomike.jakidate.validations.objects.Exclude;
import io.github.robertomike.jakidate.validations.objects.ExcludeIf;

@Exclude
class Example{

    @ExcludeIf(true)
    Boolean control; // Clearly, you can name the condition field as you want.

    @ExcludeIf
    String firstName;

    @ExcludeIf
    Boolean isPremiumSubscriber;

    @ExcludeIf
    Number age;
}

Example validationPassedExample = new Example(
  true, // "control" field value.
  "", // "password" field is empty, so the validation will pass.
  null, // "isPremiumSubscriber" field is null, so the validation will pass.
  null // "age" field is null, so the validation will pass.
);
```

#### Excluding multiple fields using the multiple conditions for each field

In this example we are going to validate more than one field, but this time we are going to use different conditions for each field we want to validate.
<br> For each new condition, we need to specify a different <b>key</b> and use it also for the field we want to validate.

```java
import io.github.robertomike.jakidate.validations.objects.Exclude;
import io.github.robertomike.jakidate.validations.objects.ExcludeIf;

@Exclude
class Example{
    @ExcludeIf(true) //^ When no key is specified, it uses "default" as key.
    String firstNameCondition;
    @ExcludeIf //^ No need to specify the key in this case, it will use "default".
    String firstName;
    @ExcludeIf(true, "isPremiumSubscriber") //^ We specify the key used for the condition field.
    String isPremiumSubscriberCondition;
    @ExcludeIf("isPremiumSubscriber") //^ We specify the same key as the condition used to validate this field.
    Boolean isPremiumSubscriber;
    @ExcludeIf(true, "age") //^ We specify the key used for the condition field.
    Number ageCondition;
    @ExcludeIf("age") //^ We specify the same key as the condition used to validate this field.
    Number age;
}

Example validationPassedExample = new Example(
  "true", // "firstNameCondition" field value.
  null, // "firstName" field is null, so the validation will pass.
  "yes", // "isPremiumSubscriberCondition" field value.
  null, // "isPremiumSubscriber" field is null, so the validation will pass.
  1, // "ageCondition" field value.
  null // "age" field is null, so the validation will pass.
);
```

#### Setting checkEmpty to false

This example shows how the validations work when <b>checkEmpty</b> is set to false, in this case the validation should pass even if the fields are empty, and it will not pass when a field is null.

```java
import io.github.robertomike.jakidate.validations.objects.Exclude;
import io.github.robertomike.jakidate.validations.objects.ExcludeIf;

@Exclude(checkEmpty = false) // The checkEmpty parameter it's always set to true by default, in this case we set it to false.
class Example{
    @ExcludeIf(true)
    Boolean condition;
    @ExcludeIf
    String firstName;
    @ExcludeIf
    String lastName;
    @ExcludeIf
    Number age;
}

Example validationPassedExample = new Example(
  true, // "condition" field value.
  null, // "password" field is null, so the validation will pass.
  "", // This should not pass when checkEmpty is set to false, because it's empty and not null.
  null // "age" field is null, so the validation will pass.
);
```

#### Using Custom type for the condition field:

----------------------------------- TO FINISH -----------------------------------

### @ExcludeUnless:

This annotation works in the same way as the @ExcludeIf annotation, but this time the fields will <b>not</b> be excluded in case the condition field is true.

#### Excluding one field

Only excluding one field based on condition field that can support different types: <b>Boolean, Numbers, String and *Custom.</b>

```java
import io.github.robertomike.jakidate.validations.objects.Exclude
import io.github.robertomike.jakidate.validations.objects.ExcludeUnless
@Exclude
class Example{
    @ExcludeUnless(true)
    String condition;
    @ExcludeUnless
    String password;
}

Example validationPassedExample = new Example(
  "1", // "condition" field value.
  "password123" // "password" field is not null, so the validation will pass.
);

Example validationNotPassedExample = new Example(
  "true", // "condition" field value.
  null // "password" field is null, so the validation not will pass.
);

// When "condition" is set to false.
Example validationPassedExample = new Example(
  "false", // "condition" field value is now set to false, therefore the condition is now reversed.
  "" // "password" field has a value, so the validation will pass.
);
```

#### Excluding multiple fields, using also @ExcludeIf

```java
@Exclude
class Example{
    @ExcludeUnless(true) //^ When no key is specified, it uses "default" as key.
    Boolean control;
    @ExcludeUnless
    String password;
    @ExcludeIf(true, "email")
    String condition;
    @ExcludeIf(key = "email")
    String email;
}


Example validationPassedExample = new Example(
        true, // "control" field value.
        "password123", // "password" field is not null, so the validation will pass.
        true, // "condition" field value.
        "" // "email" field is empty, so the validation will pass.        
);
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