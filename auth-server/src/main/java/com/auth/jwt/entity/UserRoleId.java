package com.auth.jwt.entity;
import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class UserRoleId implements Serializable {
    private  String username;
    private String role;


}