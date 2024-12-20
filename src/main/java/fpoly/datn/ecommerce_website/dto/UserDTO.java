package fpoly.datn.ecommerce_website.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class UserDTO {


    private String userId;


    private String fullName;


    private LocalDate birthDay;

    private String account;

    private String password;

    private String phoneNumber;

    private String email;

    private Integer userStatus;

    private Boolean gender;

    private String address;

    private String userNote;

    private String roleName;
}
