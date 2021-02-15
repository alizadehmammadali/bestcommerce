package az.ibar.bcmsapigateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AuthToken {
    private String token;
    private String username;
}
