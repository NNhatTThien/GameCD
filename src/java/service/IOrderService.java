/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.SQLException;
import java.util.List;
import model.dto.CartDTO;
import model.dto.OrderModel;

/**
 *
 * @author ASUS
 */
public interface IOrderService extends IServiceModel {

    public boolean createOrder(int userId, CartDTO cart) throws SQLException, ClassNotFoundException;
    public List<OrderModel> getOrderModelByUser(int id) throws SQLException, ClassNotFoundException;
    public OrderModel getOne(int id) throws SQLException, ClassNotFoundException;
}
