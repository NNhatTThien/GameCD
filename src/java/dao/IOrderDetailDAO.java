/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.entity.OrderDetailEntity;

/**
 *
 * @author ASUS
 */
public interface IOrderDetailDAO {
    public void createListDetail( Connection con,List<OrderDetailEntity> list, int orderId) throws SQLException, ClassNotFoundException;
}
