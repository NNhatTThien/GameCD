/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.entity.OrderDetailEntity;
import model.entity.OrderEntity;

/**
 *
 * @author ASUS
 */
public interface IOrderDAO {
    public boolean createOrders(int userId, List<OrderDetailEntity> listDetails) throws SQLException, ClassNotFoundException;
    
    public List<OrderEntity> getOrdersByUserId(int userId)  throws SQLException, ClassNotFoundException;
    
    public OrderEntity getOne(int id) throws SQLException, ClassNotFoundException;
    
}
