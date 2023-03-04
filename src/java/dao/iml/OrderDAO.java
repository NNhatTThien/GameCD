/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.iml;

import dao.IOrderDAO;
import dao.IOrderDetailDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import mapper.Mapper;
import model.entity.GameEntity;
import model.entity.OrderDetailEntity;
import model.entity.OrderEntity;
import utils.DBHelper;

/**
 *
 * @author ASUS
 */
public class OrderDAO extends AbstractDAO<OrderEntity> implements IOrderDAO {

    private IOrderDetailDAO detailDAO;

    public OrderDAO() {
        this.genericType = OrderEntity.class;
    }

    @Override
    public boolean createOrders(int userId, List<OrderDetailEntity> listDetails) throws SQLException, ClassNotFoundException {
        detailDAO = new OrderDetailDAO();
        String sql = "insert into Orders(OrdDate, UserId, status) values(GETDATE(), ?, 1)";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            con.setAutoCommit(false);
            int orderId = post(con, sql, userId);

            detailDAO.createListDetail(con, listDetails, orderId);

            con.commit();
            return true;
        } catch (Exception e) {
            con.rollback();
            e.printStackTrace();
            return false;

        } finally {
            if (con != null) {
                con.close();
            }

        }

    }

    @Override
    public List<OrderEntity> getOrdersByUserId(int userId) throws SQLException, ClassNotFoundException {
        String sql = "select Id, OrdDate, shipdate, status, UserId  from Orders where UserId = ?";
        OrderEntity entity = facE.getEntity(genericType);
        List<OrderEntity> list = new ArrayList<>();
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            list = get(con, sql, Mapper.getInstance(), userId);
            entity = list.get(0);
        } catch (Exception e) {
            list = null;
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
            return list;
        }
    }

    @Override
    public OrderEntity getOne(int id) throws SQLException, ClassNotFoundException {
        String sql = "select Id, OrdDate, shipdate, status, UserId  from Orders where Id = ?";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            List<OrderEntity> list = get(con, sql, Mapper.getInstance(), id);
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

}
