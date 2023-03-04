/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import annotation.entity.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ASUS
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountEntity implements IEntity{
    @Column("Id")
    private int id;
    @Column("email")
    private String email;
    @Column("password")
    private String password;
    @Column("avatarPath")
    private String avatarPath;
    @Column("userId")
    private int userId;
    @Column("token")
    private String token;
    @Column("status")
    private int status;
    @Column("role")
    private int role;


}
