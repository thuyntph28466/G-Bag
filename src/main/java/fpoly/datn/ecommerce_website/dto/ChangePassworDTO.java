package fpoly.datn.ecommerce_website.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Setter
@Getter
public class ChangePassworDTO {

    String password;
    String newPassword;
}
