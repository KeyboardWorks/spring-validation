# Spring Validation
Validation through controller or service layer with exception handler

# Required Dependency

```xml
  <dependency> 
    <groupId>org.springframework.boot</groupId> 
    <artifactId>spring-boot-starter-validation</artifactId> 
  </dependency>
```

# How It Works

## Add Javax Validation Annotation

You can add one ore more annotation into the field. 

Javax validation has some annotation :
- @NotNull
- @AssertTrue
- @Size
- @Min
- @Max
- @Email
- @NotEmpty
- @NotBlank
- @Positive @PositiveOrZero
- @Negative @NegativeOrZero
- @Past @PastOrPresent
- @Future @FutureOrPresent

Example: 
```java
@Getter
@Setter
@Builder
public class ProductRequest {

  @NotBlank(message = "Code is mandatory")
  @Size(min = 5, message = "Product code length must greather than 5")
  private String code;

  @NotBlank(message = "Code is mandatory")
  @Size(min = 5, message = "Product name length must greather than 5")
  private String name;

  @NotNull(message = "Price is mandatory")
  @Positive(message = "Product must greather than zero")
  private BigDecimal price;
	
}
```

## Validation In Controller

You must add @Valid or @Validated to @RequestBody controller parameter.

Example:
```java
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public GenericResponse<ProductResponse> createUser(@Validated @RequestBody ProductRequest productRequest) {
    return GenericResponseHelper.ok(productService.createProduct(productRequest));
  }

  @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public GenericResponse<ProductResponse> updateUser(@PathVariable("id")String id, @Validated @RequestBody ProductRequest productRequest) {
    return GenericResponseHelper.ok(productService.updateProduct(id, productRequest));
  }
```
## Validation In Service Interface

You must add @Validated to interface service and add @Valid to method parameter who you want validate.

Example :
```java
@Validated
public interface ProductService {

  List<ProductResponse> getProducts();

  ProductResponse getProduct(String id);

  ProductResponse createProduct(@Valid ProductRequest productRequest);

  ProductResponse updateProduct(String id, @Valid ProductRequest productRequest);

  void deleteProduct(String id);

}
```

# Custom Validation

## ConstraintValidator

We must create class which implements ConstrainstValidator, and override method isValid.

Example:
```java
public class ProductCodeMustUniqueValidator implements ConstraintValidator<ProductCodeMustUnique, String> {

  @Autowired
  private ProductRepository productRepository;

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return !productRepository.findByCode(value).isPresent(); 
  }

}
```

## Create Annotation

After we create validator class, we need to create annotation class which will tagged the field want to validate.
In annotation class we must add @Constraint to tell the validator, this annotation must to validate.
Field validatedBy used by validator how this annotation validate value.

Example:
```java
@Constraint(validatedBy = ProductCodeMustUniqueValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ProductCodeMustUnique {

  String message() default "Product code already used";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
```
After that we can add this annotation to field who want to validate.

```java
  @ProductCodeMustUnique
  @NotBlank(message = "Code is mandatory")
  @Size(min = 5, message = "Product code length must greather than 5")
  private String code;
```

# Grouping Validation

If in one class you want specify the validation you can use grouping.
First you need to create interface class to grouping the validation.

Example:
```java
public interface CreateData {

}

public interface UpdateData {

}
```

Second add interface classes to javax annotation do you want to grouping.

Example:
```java
@Getter
@Setter
@Builder
public class UserRequest {

  @NotBlank(message = "Name is mandatory !", groups = {CreateData.class, UpdateData.class})
  private String name;

  @NotBlank(message = "Username is mandatory !", groups = {CreateData.class, UpdateData.class})
  @Size(min = 8, message = "Username minimal 8 characters", groups = {CreateData.class, UpdateData.class})
  private String username;

  @NotBlank(message = "Password is mandatory !", groups = {CreateData.class})
  @Size(min = 8, message = "Password minimal 8 characters", groups = {CreateData.class})
  private String password;
	
}
```

When you want to validate the class, you must use @Validated and add interface class group.

Example:
```java
@Validated(CreateData.class)
public interface UserServiceCreate {

  UserResponse createUser(@Valid UserRequest userRequest);
	
}
```
