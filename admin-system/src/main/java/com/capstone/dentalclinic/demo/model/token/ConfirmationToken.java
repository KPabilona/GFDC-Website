package com.capstone.dentalclinic.demo.model.token;

import com.capstone.dentalclinic.demo.model.Employee;
    import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {

    @Id
    @SequenceGenerator( allocationSize = 1,
            name = "confirmation_token_sequence",
            sequenceName = "confirmation_token_sequence")
    @GeneratedValue(generator = "confirmation_token_sequence",
                    strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull
    @Column(nullable = false)
    private String token;
    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @NotNull
    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(name = "employee_id",
                nullable = false)
    private Employee employee;

    public ConfirmationToken(String token,
                             LocalDateTime createdAt,
                             LocalDateTime expiresAt,
                             Employee employee) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.employee = employee;
    }
}
