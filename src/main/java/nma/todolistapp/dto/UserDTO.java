package nma.todolistapp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nma.todolistapp.model.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
   // private long id;
    private String name;
    private String email;
    private String password;
}
