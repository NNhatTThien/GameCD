/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.iml;

import dao.IGameDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mapper.Mapper;
import model.entity.AccountEntity;
import model.entity.GameEntity;
import utils.DBHelper;

/**
 *
 * @author ASUS
 */
public class GameDAO extends AbstractDAO<GameEntity> implements IGameDAO {

    public GameDAO() {
        this.genericType = GameEntity.class;
    }

    @Override
    public List<GameEntity> getAll() throws SQLException, ClassNotFoundException {
        List<GameEntity> list = new ArrayList<>();
        String sql = "select Id, Name, price, imgPath, description, quantity, status from Games";

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
    public GameEntity getOne(int id) throws SQLException, ClassNotFoundException {
        String sql = "select Id, Name, price, imgPath, description, quantity, status from Games where id = ?";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            List<GameEntity> list = get(con, sql, Mapper.getInstance(), id);
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
    public List<GameEntity> getPage(int from, int max) throws SQLException, ClassNotFoundException {
        List<GameEntity> list = new ArrayList<>();
        String sql = "select Id, Name, price, imgPath, description, quantity, status from Games ORDER BY Id ASC "
                + "OFFSET  ? ROWS "
                + "FETCH NEXT ? ROWS ONLY ";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            list = get(con, sql, Mapper.getInstance(), from, max);
            return list;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public int countGame(String name, int categoryId) throws SQLException, ClassNotFoundException {
        int row = 0;
        String sql = "select COUNT(*) from Games g "
                + "join CategoryGames cg on cg.GameId = g.Id where cg.CategoryId = ? and g.Name like ?";
        Connection con = null;

        try {
            con = DBHelper.makeConnectDB();
            if (categoryId == 0) {
                sql = "select COUNT(*) from Games g where g.Name like ?";
                row = count(con, sql, "%" + name + "%");
            } else {
                row = count(con, sql, categoryId, "%" + name + "%");
            }

            return row;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public int countGame() throws SQLException, ClassNotFoundException {
        int row = 0;
        String sql = "select count(*) from games";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            row = count(con, sql);
            return row;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public List<GameEntity> filterByGameIdAndCateIdAndPaging(String name, int id, int from, int to)
            throws SQLException, ClassNotFoundException {
        List<GameEntity> list = new ArrayList<>();

        String sql = "select g.Id as Id, Name, price, imgPath, description, quantity, status from Games g "
                + "join CategoryGames cg on  g.Id = cg.GameId where g.Name like ? and cg.CategoryId = ? "
                + "ORDER BY g.Id ASC "
                + "                OFFSET  ? ROWS "
                + "                FETCH NEXT ? ROWS ONLY ";
        if (id == 0) {
            sql = "select g.Id as Id, Name, price, imgPath, description, quantity, status from Games g "
                    + "where g.Name like ? "
                    + "ORDER BY g.Id ASC "
                    + "                OFFSET  ? ROWS "
                    + "                FETCH NEXT ? ROWS ONLY ";
        }
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            if (id == 0) {
                list = get(con, sql, Mapper.getInstance(), "%" + name + "%", from, to);
            } else {
                list = get(con, sql, Mapper.getInstance(), "%" + name + "%", id, from, to);
            }
            return list;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        GameDAO dao = new GameDAO();
        System.out.println(dao.filterByGameIdAndCateIdAndPaging("a", 0, 0, 1000));
    }

    @Override
    public void decreaseQuantity(Connection con,int id, int quantity) throws SQLException, ClassNotFoundException {
        String sql = "update Games set quantity = ? where Id = ?";
        String sql1 = "select Id, Name, price, imgPath, description, quantity, status from games where id = ?";
       
        GameEntity game = (GameEntity)get(con, sql1, Mapper.getInstance(), id).get(0);
        
        put(con, sql, game.getQuantity() - quantity, id);

    }

}
