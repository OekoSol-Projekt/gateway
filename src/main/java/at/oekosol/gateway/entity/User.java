package at.oekosol.gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Table("users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private Long id;

    private String username;

    private String password;

    private boolean enabled;

    private Set<Role> roles;
}
