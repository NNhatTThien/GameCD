/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.iml;

import dao.IGameDAO;
import dao.IOrderDetailDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.entity.OrderDetailEntity;

/**
 *
 * @author ASUS
 */
public class OrderDetailDAO extends AbstractDAO<OrderDetailEntity> implements IOrderDetailDAO {

    private IGameDAO gameDao;
    public OrderDetailDAO() {
        this.genericType = OrderDetailEntity.class;
    }

    @Override
    public void createListDetail(Connection con, List<OrderDetailEntity> list, int orderId) throws SQLException, ClassNotFoundException {
        gameDao = new GameDAO();
        String sql = "insert into OrderDetails(OrderID, GameId, quantity) values(?, ?, ?)";
        System.out.println(list);
        for (OrderDetailEntity e : list) {
           
            post(con, sql, orderId, e.getGameId(), e.getQuantity());
            gameDao.decreaseQuantity(con, e.getGameId(), e.getQuantity());
        }
    }

}
