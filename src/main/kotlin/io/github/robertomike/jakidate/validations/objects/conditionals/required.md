# Welcome to Required validation

This annotation allows you to require fields based on another field of the object.
<br>In order to work correctly, the usage of the <b>@RequiredIf</b> or <b>@RequiredUnless</b> annotation is required.

## @RequiredIf:
This annotation is put it on top of two or more fields. There is a condition field and n° quantity of fields that will be required when the condition field is true.

### Usage examples:

Here are some examples, going from the most basic to the more advanced.

#### Requiring one field

Only requiring one field based on condition field that can support different types: <b>Boolean, Numbers, String and *Custom.</b>

```java
import io.github.robertomike.jakidate.validations.objects.conditionals.Required;
import io.github.robertomike.jakidate.validations.objects.conditionals.RequiredIf;

@Required // This annotation is necessary to use this validation.
class Example{

    @RequiredIf(true) // Always needs to have at least a RequiredIf annotation set to true, this will be our condition field.
    private Boolean condition;

    @RequiredIf // Always needs to have at least a field to validate.
    private String password;
}

// In this example, the validation will pass.
Example validationPassedExample = new Example(
        true, // "condition" field value.
        "myPassword" // "password" field has a value, so the validation will not pass and an error message will be added.
);

// In this example, the validation will not pass.
Example validationRejectedExample = new Example(
        true, // "condition" field value.
        "" // "password" field is empty, so the validation will not pass.
);

// When "condition" is set to false.
Example falseConditionValidationPassedExample = new Example(
        false, // "condition" field value is now set to false, therefore the validation is now deactivated.
        "" // "password" field has no value, but the validation is deactivated, so no error message will be added.
);
```

#### Using a different type for the condition field

In this example we are going to use the condition field with different types.
<br>[Here](#true-supported-types) you can find the supported values for each type.

```java
import io.github.robertomike.jakidate.validations.objects.conditionals.Required;
import io.github.robertomike.jakidate.validations.objects.conditionals.RequiredIf;

@Required
class Example {
    
    @RequiredIf(true)
    private String condition; // condition field is of type String.

    @RequiredIf
    private String password;
}

// In this example, the validation will pass.
Example validationPassedExample = new Example(
        "0", // "condition" field value.
        "myPassword" // "password" field has a value, so the validation will pass.
);

// In this example, the validation will not pass.
Example validationRejectedExample = new Example(
        "1", // "condition" field value.
        "" // "password" field is empty, so the validation will not pass and an error message will be added.
);
```

#### Requiring multiple fields using the same condition

In this example we are going to validate more than one field, using the same condition field to validate all the elements.

```java
import io.github.robertomike.jakidate.validations.objects.conditionals.Required;
import io.github.robertomike.jakidate.validations.objects.conditionals.RequiredIf;

@Required
class Example{

    @RequiredIf(true)
    Boolean control; // Clearly, you can name the condition field as you want.

    @RequiredIf
    String firstName;

    @RequiredIf
    Boolean isPremiumSubscriber;

    @RequiredIf
    Number age;
}

Example validationPassedExample = new Example(
  true, // "control" field value.
  "myPassword", // "password" field has a value, so the validation will pass.
  true, // "isPremiumSubscriber" field is true, so the validation will pass.
  18 // "age" field has a value, so the validation will pass.
);
```

#### Requiring multiple fields using multiple conditions for each field

In this example we are going to validate more than one field, but this time we are going to use different conditions for each field we want to validate.
<br> For each new condition, we need to specify a different <b>key</b> and use it also for the field we want to validate.

```java
import io.github.robertomike.jakidate.validations.objects.conditionals.Required;
import io.github.robertomike.jakidate.validations.objects.conditionals.RequiredIf;

@Required
class Example{

    @RequiredIf(true) //^ When no key is specified, it uses "default" as key.
    String firstNameCondition;
    @RequiredIf //^ No need to specify the key in this case, it will use "default".
    String firstName;
    @RequiredIf(true, "isPremiumSubscriber") //^ We specify the key used for the condition field.
    String isPremiumSubscriberCondition;
    @RequiredIf("isPremiumSubscriber") //^ We specify the same key as the condition used to validate this field.
    Boolean isPremiumSubscriber;
    @RequiredIf(true, "age") //^ We specify the key used for the condition field.
    Number ageCondition;
    @RequiredIf("age") //^ We specify the same key as the condition used to validate this field.
    Number age;
}

Example validationPassedExample = new Example(
  "true", // "firstNameCondition" field value.
  "John", // "firstName" field has a value, so the validation will pass.
  "yes", // "isPremiumSubscriberCondition" field value.
  true, // "isPremiumSubscriber" field is true, so the validation will pass.
  1, // "ageCondition" field value.
  18 // "age" field has a value, so the validation will pass.
);
```

#### Setting checkEmpty to false

This example shows how the validations work when <b>checkEmpty</b> is set to false, in this case the validation should pass even if the fields are empty, and it will not pass when a field is null.

```java
import io.github.robertomike.jakidate.validations.objects.conditionals.Required;
import io.github.robertomike.jakidate.validations.objects.conditionals.RequiredIf;

@Required(checkEmpty = false) // The checkEmpty parameter it's always set to true by default, in this case we set it to false.
class Example{

    @RequiredIf(true)
    Boolean condition;
    @RequiredIf
    String firstName;
    @RequiredIf
    String lastName;
    @RequiredIf
    Number age;
}

Example validationPassedExample = new Example(
  true, // "condition" field value.
  "myPassword", // "password" field has a value, so the validation will pass.
  "", // This should pass when checkEmpty is set to false, because it's empty and not null.
  18 // "age" field has a value, so the validation will pass.
);
```

#### Using Custom type for the condition field:

This example shows how to use a custom type, such as enum, for the condition field.

```java
import io.github.robertomike.jakidate.validations.objects.conditionals.Required;
import io.github.robertomike.jakidate.validations.objects.conditionals.RequiredIf;
enum PersonTypeEnum {
    NATURAL, LEGAL_PERSON
}

public class CustomExpression extends Expression<PersonTypeEnum> { 
    @Override 
    public Boolean apply(PersonTypeEnum value, Boolean unless) {
        return (PersonTypeEnum.NATURAL.equals(value)) == !unless;
    }
}

@Required
class Example {

    @Condition(expression = CustomExpression.class) 
    PersonTypeEnum control;

    @RequiredIf
    String taxCode; // control.equals(PersonTypeEnum.NATURAL)

    @RequiredUnless
    String vat; // control.equals(PersonTypeEnum.LEGAL_PERSON)
}

Example validationPassedExample = new Example(
    PersonTypeEnum.NATURAL,
    "Hello", // Can have a value
    "" // Must be empty or null
);

Example validationRejectedExample = new Example(
    PersonTypeEnum.LEGAL_PERSON, 
    "Hello", // Must be empty or null
    "ciao" // Can have a value
);
```

### @RequiredUnless:

This annotation works in the same way as the @RequiredIf annotation, but this time the fields will <b>not</b> be required in case the condition field is true.

#### Requiring one field

Only requiring one field based on condition field that can support different types: <b>Boolean, Numbers, String and *Custom.</b>

```java
import io.github.robertomike.jakidate.validations.objects.conditionals.Required;
import io.github.robertomike.jakidate.validations.objects.conditionals.RequiredUnless;

@Required
class Example{

    @RequiredUnless(true)
    String condition;
    @RequiredUnless
    String password;
}

Example validationPassedExample = new Example(
  "true", // "condition" field value.
  null // "password" field is null, so the validation will pass.
);

Example validationRejectedExample = new Example(
        "1", // "condition" field value.
        "password123" // "password" field is not null, so the validation will not pass.
);

// When "condition" is set to false.
Example falseConditionValidationPassedExample = new Example(
        "false", // "condition" field value is now set to false, therefore the validation is now deactivated.
        "password123" // "password" field has a value, but the validation is deactivated, so no error message will be added.
);
```

#### Requiring multiple fields, using also @RequiredIf

```java
@Required
class Example{

    @RequiredUnless(true) //^ When no key is specified, it uses "default" as key.
    Boolean control;
    @RequiredUnless
    String password;
    @RequiredIf(true, "email")
    String condition;
    @RequiredIf(key = "email")
    String email;
}

Example validationPassedExample = new Example(
        true, // "control" field value.
        "", // "password" field is null, so the validation will pass.
        true, // "condition" field value.
        "email@example.com" // "email" field has a value, so the validation will pass.        
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