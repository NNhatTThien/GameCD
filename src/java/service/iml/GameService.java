/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.iml;

import dao.IGameDAO;
import dao.iml.GameDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dto.CartMessageDTO;
import model.dto.CategoryModel;
import model.dto.GameModel;
import model.entity.GameEntity;
import service.IGameService;

/**
 *
 * @author ASUS
 */
public class GameService extends AbstractServiceModel<GameEntity, GameModel> implements IGameService {

    private CategoryGameService cateGameService = new CategoryGameService();
    private IGameDAO gameDao = new GameDAO();

    public GameService() {
        this.modelType = GameModel.class;
        this.entityType = GameEntity.class;
    }

    @Override
    public GameModel toModel(GameEntity e) {
        GameModel m = facM.getModel(modelType);
        m = super.toModel(e);
        m.setListImg();
        return m;
    }

    @Override
    public GameModel getOne(int id) throws SQLException, ClassNotFoundException {
        GameEntity e = facE.getEntity(entityType);
        e = gameDao.getOne(id);
        if (e != null) {
            return this.toModel(e);
        } else {
            return null;
        }
    }

    @Override
    public List<GameModel> getAll() throws SQLException, ClassNotFoundException {
        final List<GameModel> listModels = new ArrayList<>();
        for (GameEntity e : gameDao.getAll()) {
            GameModel k = this.toModel(e);
            this.setListCate(k);
            listModels.add(k);
        }
        return listModels;
    }

    @Override
    public void setListCate(GameModel game) throws SQLException, ClassNotFoundException {
        List<CategoryModel> listCate = cateGameService.filterByGameID(game.getId());
        game.setListCategory(listCate);
    }

    @Override
    public List<GameModel> getPage(int from, int max) throws SQLException, ClassNotFoundException {
        List<GameModel> listM = new ArrayList<>();
        for (GameEntity gameEntity : gameDao.getPage(from, max)) {
            GameModel k = this.toModel(gameEntity);
            this.setListCate(k);
            listM.add(k);
        }
        return listM;
    }

    @Override
    public int countGame(String name, int categoryId) throws SQLException, ClassNotFoundException {
        return gameDao.countGame(name, categoryId);
    }

    @Override
    public int countGame() throws SQLException, ClassNotFoundException {
        return gameDao.countGame();
    }

    @Override
    public List<GameModel> filterByGameNameAndCategoryIdAndPaging(String name, int id, int from, int to)
            throws SQLException, ClassNotFoundException {
        List<GameModel> listM = new ArrayList<>();
        for (GameEntity gameEntity : gameDao.filterByGameIdAndCateIdAndPaging(name, id, from, to)) {
            GameModel k = this.toModel(gameEntity);
            this.setListCate(k);
            listM.add(k);
        }
        return listM;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        GameService gSer = new GameService();
        GameModel model = gSer.getOne(1);
        gSer.setListCate(model);
        System.out.println(gSer.filterByGameNameAndCategoryIdAndPaging("a", 4, 0, 1000));
        System.out.println(gSer.countGame("a", 4));
    }

}
