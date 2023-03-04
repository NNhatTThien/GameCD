/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.entity.CategoryEntity;
import model.entity.GameEntity;

/**
 *
 * @author ASUS
 */
public interface IGameDAO {

    public List<GameEntity> getAll() throws SQLException, ClassNotFoundException;

    public List<GameEntity> getPage(int from, int max) throws SQLException, ClassNotFoundException;

    public GameEntity getOne(int id) throws SQLException, ClassNotFoundException;
    public int countGame(String name, int categoryId)  throws SQLException, ClassNotFoundException;
    public int countGame()  throws SQLException, ClassNotFoundException;
    
    public List<GameEntity> filterByGameIdAndCateIdAndPaging(String name, int id, int from, int to)  throws SQLException, ClassNotFoundException;
    
    public void decreaseQuantity(Connection con,int id, int quantity)  throws SQLException, ClassNotFoundException;
}
