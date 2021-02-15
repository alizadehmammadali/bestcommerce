package az.ibar.bcmsuser.model;

import az.ibar.bcmsuser.model.enums.MerchantType;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotEmpty(message = "Email is mandatory")
    private String email;

    @NotEmpty(message = "Password is mandatory")
    private String password;

    @NotNull(message = "Merchant Type is mandatory")
    private MerchantType merchantType;

    @NotEmpty(message = "Merchant Name is mandatory")
    private String merchantName;

    @NotEmpty(message = "Owner Name is mandatory")
    private String ownerName;

    @NotEmpty(message = "Address is mandatory")
    private String address;

    @NotEmpty(message = "Phone Number is mandatory")
    private String phoneNumber;
}
