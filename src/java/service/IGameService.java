/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.SQLException;
import java.util.List;
import model.dto.CartMessageDTO;
import model.dto.GameModel;

/**
 *
 * @author ASUS
 */
public interface IGameService extends IServiceModel {

    public GameModel getOne(int id) throws SQLException, ClassNotFoundException;

    public List<GameModel> getAll() throws SQLException, ClassNotFoundException;

    public void setListCate(GameModel game) throws SQLException, ClassNotFoundException;

    public List<GameModel> getPage(int from, int max) throws SQLException, ClassNotFoundException;

    public int countGame(String name, int categoryId) throws SQLException, ClassNotFoundException;

    public int countGame() throws SQLException, ClassNotFoundException;

    public List<GameModel> filterByGameNameAndCategoryIdAndPaging(String name, int id, int from, int to) throws SQLException, ClassNotFoundException;

}
