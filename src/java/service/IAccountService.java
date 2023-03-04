/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.SQLException;
import model.dto.AccountModel;
import model.dto.UserModel;

/**
 *
 * @author ASUS
 */
public interface IAccountService extends IServiceModel {

    public AccountModel getOne(int id) throws SQLException, ClassNotFoundException;
    public void createAccount(AccountModel accountModel, UserModel userModel) throws SQLException, ClassNotFoundException;
    public AccountModel checkLogin(String email, String password) throws SQLException, ClassNotFoundException;
    
    public String updateToken(int id) throws SQLException, ClassNotFoundException;
    
    public boolean checkEmail(String email) throws SQLException, ClassNotFoundException;
    public AccountModel checkToken(String token) throws SQLException, ClassNotFoundException;
    
    public void updatePass(int id, String pass) throws SQLException, ClassNotFoundException;
    
    public void updateAvatar(int id, String avatar) throws SQLException, ClassNotFoundException;
}
