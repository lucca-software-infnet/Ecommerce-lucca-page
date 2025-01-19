package EcommercePage.producingwebservice.model.dtos;

import javax.validation.constraints.NotNull;

import EcommercePage.producingwebservice.model.enums.UserRole;

public record RegisterDto(@NotNull String email,@NotNull String password, @NotNull UserRole role ) {
    
}
