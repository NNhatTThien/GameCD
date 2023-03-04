/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.iml;

import dao.IAccountDAO;
import dao.iml.AccountDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.dto.AccountModel;
import model.dto.OrderModel;
import model.dto.UserModel;
import model.entity.AccountEntity;
import service.IAccountService;
import service.IUserService;
import utils.Encode;
import utils.MyUtils;

/**
 *
 * @author ASUS
 */
public class AccountService extends AbstractServiceModel<AccountEntity, AccountModel> implements IAccountService {

    private IAccountDAO accountDao = new AccountDAO();

    public AccountService() {
        this.modelType = AccountModel.class;
        this.entityType = AccountEntity.class;
    }

    public List<AccountModel> getAll() throws SQLException, ClassNotFoundException {
        final List<AccountModel> listModels = new ArrayList<>();
        accountDao.getAll().stream().forEach(e -> {
            listModels.add(super.toModel(e));
        });
        return listModels;
    }

    @Override
    public AccountModel getOne(int id) throws SQLException, ClassNotFoundException {

        AccountModel model = facM.getModel(modelType);
        AccountEntity entity = accountDao.getOne(id);
        if (entity == null) {
            return null;
        } else {
            model = toModel(entity);
            return model;

        }

    }

    @Override
    public void createAccount(AccountModel accountModel, UserModel userModel) throws SQLException, ClassNotFoundException {
        UserService userSer = new UserService();
        if (accountModel != null && userModel != null) {
            accountDao.createAccount(this.toEntity(accountModel), userSer.toEntity(userModel));
        }
    }

    @Override
    public AccountModel checkLogin(String email, String password) throws SQLException, ClassNotFoundException {
        return accountDao.checkLogin(email, password) != null ? this.toModel(accountDao.checkLogin(email, password)) : null;
    }

    @Override
    public String updateToken(int id) throws SQLException, ClassNotFoundException {
        String token = Encode.ranToken();
        List<AccountEntity> listE = accountDao.checkToken(token);
        if (listE == null || listE.isEmpty()) {
            accountDao.updateToken(id, token);
        } else {
            updateToken(id);
        }
        return token;
    }

    @Override
    public boolean checkEmail(String email) throws SQLException, ClassNotFoundException {
        return accountDao.checkEmail(email);
    }

    @Override
    public AccountModel checkToken(String token) throws SQLException, ClassNotFoundException {
        List<AccountEntity> listE = accountDao.checkToken(token);
        if (listE == null || listE.size() == 0) {
            return null;
        } else {
            return this.toModel(listE.get(0));
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        AccountService news = new AccountService();
//        AccountModel a = new AccountModel(100, "sadfas", "dsafas", "12312", new UserModel(), "234", 0, 0);
//        UserModel b = new UserModel(100, "sdafas", "asdfas", "21", null);
//        news.createAccount(a, b);
        
    }
    @Override
    public void updatePass(int id, String pass) throws SQLException, ClassNotFoundException {
        pass = Encode.toSHA1(pass);
        accountDao.updatePass(id, pass);
    }

    @Override
    public void updateAvatar(int id, String avatar) throws SQLException, ClassNotFoundException {
        accountDao.updateAvatar(id, avatar);
    }
}
