package com.houstondirectauto.refurb.entity;

import java.io.Serializable;
import java.util.Set;

import com.houstondirectauto.refurb.UserStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class UserEntity extends AbstractTimestampEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String location;
    private String smartSheetId;
    private String lift;
    private String idmsId;
    private String pinCode;
    private String rfIdBadgeId;
    private Integer token;

    @Column(length = 500)
    private String image;

    @Column(columnDefinition = "boolean default true")
    private Boolean twoFactorEnabled;

    @Column(name = "twoFactorSecret", length = 6)
    private Integer twoFactorSecret;

    @Column(length = 15, columnDefinition = "varchar(15) default 'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @ManyToMany(targetEntity = RoleEntity.class, cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @NotNull(message = "role is required")
    private Set<RoleEntity> roles;
}