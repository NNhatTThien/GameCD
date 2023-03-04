/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.SQLException;
import model.dto.AccountModel;
import model.dto.IModel;
import model.dto.UserCheckErrorUpdateDTO;
import model.dto.UserModel;
import model.entity.IEntity;

/**
 *
 * @author ASUS
 */
public interface IUserService extends IServiceModel {

    public UserModel getOne(int id) throws SQLException, ClassNotFoundException;
    public boolean updateProfile(int id, String name, String phone, String address) throws SQLException, ClassNotFoundException;
    public UserCheckErrorUpdateDTO checkChangeProfile(AccountModel test,String name,String phone, String password, String newPass, String conFirm) throws SQLException, ClassNotFoundException;
    public int createUser(String name, String phone, String address) throws SQLException, ClassNotFoundException;
    public void deleteGuest(int id) throws SQLException, ClassNotFoundException;
    public void setListOrder(UserModel user) throws SQLException, ClassNotFoundException;
}
