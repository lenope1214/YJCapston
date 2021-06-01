package com.jumanji.capston.DTO;

import com.jumanji.capston.data.User;
import lombok.Data;

@Data
public class PutUserDTO {
    private String id;
    private User user;
}
