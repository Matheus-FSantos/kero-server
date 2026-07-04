package github.io.matheusfsantos.kr_server.user.adapters.in.http.impl.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NewUserRequest(
    @NotBlank(message = "First name field cannot be blank")
    @Size(min = 4, max = 100, message = "First name field must be between 4 and 100 characters")
    String firstName,

    @NotBlank(message = "Last name field cannot be blank")
    @Size(min = 4, max = 100, message = "Last name field must be between 4 and 100 characters")
    String lastName,

    @NotBlank(message = "Email field cannot be blank")
    @Email(message = "Email must be valid")
    @Size(min = 8, max = 150, message = "Email field must be between 8 and 150 characters")
    String email,

    @NotBlank(message = "Password field cannot be blank")
    @Size(min = 4, max = 20, message = "Password field must be between 4 and 20 characters")
    String password
) { }
