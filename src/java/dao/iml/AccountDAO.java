/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.iml;

import dao.IAccountDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mapper.Mapper;
import model.entity.AccountEntity;
import model.entity.UserEntity;
import utils.DBHelper;

/**
 *
 * @author ASUS
 */
public class AccountDAO extends AbstractDAO<AccountEntity> implements IAccountDAO {

    public AccountDAO() {
        this.genericType = AccountEntity.class;
    }

    @Override
    public List<AccountEntity> getAll() throws SQLException, ClassNotFoundException {
        List<AccountEntity> list = new ArrayList<>();
        String sql = "select Id, email, password, avatarPath, userId, token, status, role from Accounts";

        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            list = get(con, sql, Mapper.getInstance());
            return list;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public void createAccount(AccountEntity aEntity, UserEntity uEntity) throws SQLException, ClassNotFoundException {
        String sql = "insert into Users( fullname, phone, address) values(?, ?, ?)";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            con.setAutoCommit(false);
            int k = post(con, sql, uEntity.getFullName(), uEntity.getPhone(), uEntity.getAddress());
            String sql2 = "insert into Accounts(email, password, avatarPath, userId, token, status, role)"
                    + " values(? ,? , ?, ?, ?, 1, 0)";
            post(con, sql2, aEntity.getEmail(),
                    aEntity.getPassword(),
                    aEntity.getAvatarPath(),
                    k,
                    aEntity.getToken());
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDAO dao = new UserDAO();
        AccountDAO daoA = new AccountDAO();

    }

    @Override
    public AccountEntity getOne(int id) throws SQLException, ClassNotFoundException {
        String sql = "select Id, email, password, avatarPath, userId, token, status, role from Accounts where id = ?";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            List<AccountEntity> list = get(con, sql, Mapper.getInstance(), id);
            if (list.isEmpty()) {
                return null;
            } else {
                return list.get(0);
            }
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public AccountEntity checkLogin(String email, String password) throws SQLException, ClassNotFoundException {
        AccountEntity entity = null;
        String sql = "select Id, email, password, avatarPath, userId, token, status, role from Accounts "
                + "where email = ? and password = ? and status = 1";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            List<AccountEntity> list = get(con, sql, Mapper.getInstance(), email, password);
            if (list.isEmpty()) {
                return null;
            } else {
                return list.get(0);
            }
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }

    @Override
    public void updateToken(int id, String token) throws SQLException, ClassNotFoundException {
        String sql = "update Accounts set token = ? where Id = ?";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            put(con, sql, token, id);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public boolean checkEmail(String email) throws SQLException, ClassNotFoundException {
        AccountEntity entity = null;
        String sql = "select Id, email, password, avatarPath, userId, token, status, role from Accounts "
                + "where email = ?";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            List<AccountEntity> list = get(con, sql, Mapper.getInstance(), email);
            if (list.isEmpty()) {
                return true;
            } else {
                return false;
            }
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public List<AccountEntity> checkToken(String token) throws SQLException, ClassNotFoundException {

        AccountEntity entity = null;
        String sql = "select Id, email, password, avatarPath, userId, token, status, role from Accounts "
                + "where token = ?";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            List<AccountEntity> list = get(con, sql, Mapper.getInstance(), token);
            if (list.isEmpty()) {
                return null;
            } else {
                return list;
            }
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public void updatePass(int id, String pass) throws SQLException, ClassNotFoundException {
        String sql = "update Accounts set password = ? where Id = ?";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            put(con, sql, pass, id);
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }

    @Override
    public void updateAvatar(int id, String imgPath) throws SQLException, ClassNotFoundException {
         String sql = "update Accounts set avatarPath = ? where Id = ?";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            put(con, sql, imgPath, id);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
}
