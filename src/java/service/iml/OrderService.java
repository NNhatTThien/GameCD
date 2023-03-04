/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.iml;

import dao.IOrderDAO;
import dao.iml.OrderDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.dto.CartDTO;
import model.dto.OrderModel;
import model.entity.OrderDetailEntity;
import model.entity.OrderEntity;
import service.IOrderService;
import utils.MyUtils;

/**
 *
 * @author ASUS
 */
public class OrderService extends AbstractServiceModel<OrderEntity, OrderModel> implements IOrderService{
    
    private IOrderDAO orderDAO = new OrderDAO();
    public OrderService() {
        this.modelType = OrderModel.class;
        this.entityType = OrderEntity.class;
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        OrderService ser = new OrderService();
        OrderEntity order = new OrderEntity();
        System.out.println(ser.getOne(13));
    }

    @Override
    public boolean createOrder(int userId, CartDTO cart) throws SQLException, ClassNotFoundException {
        List<OrderDetailEntity> listOrderDetails= cart.entrySet().stream().map(e -> {
            return new OrderDetailEntity(0, 0, e.getKey().getId(), e.getValue());
        }).collect(Collectors.toList());
        return orderDAO.createOrders(userId, listOrderDetails);
    }

    @Override
    public List<OrderModel> getOrderModelByUser(int id) throws SQLException, ClassNotFoundException {
        List<OrderModel> listM = new ArrayList<>();
        List<OrderEntity> listE = orderDAO.getOrdersByUserId(id);
        for (OrderEntity orderEntity : listE) {
            OrderModel orderModel = toModel(orderEntity);
            listM.add(orderModel);
        }
        return listM;
    }

    @Override
    public OrderModel getOne(int id) throws SQLException, ClassNotFoundException {
        OrderEntity entity = orderDAO.getOne(id);
        if(entity == null){
            return null;
        } else {
            return toModel(entity);
        }
    }
   
}
