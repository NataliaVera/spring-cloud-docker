package org.nvera.springcloud.mscourse.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDAO {

    private Long userid;
    private String name;
    private String email;
    private String password;
}
