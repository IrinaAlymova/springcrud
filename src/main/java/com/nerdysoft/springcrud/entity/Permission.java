package com.nerdysoft.springcrud.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "permissions")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;
}
