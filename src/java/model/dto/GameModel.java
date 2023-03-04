/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

import annotation.model.Id;
import annotation.model.ManyToMany;
import annotation.model.MapToEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ASUS
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GameModel implements Serializable, IModel {

    @Id
    @MapToEntity(mapId = "id")
    @EqualsAndHashCode.Include
    private int id;
    @MapToEntity(mapId = "name")
    private String name;
    @MapToEntity(mapId = "price")
    private int price;
    @MapToEntity(mapId = "imgPath")
    private String imgPath;
    @MapToEntity(mapId = "description")
    private String description;
    @MapToEntity(mapId = "quantity")
    private int quantity;
    @MapToEntity(mapId = "status")
    private int status;
    
    @MapToEntity(mapId = "gameId")
    @ManyToMany
    private List<CategoryModel> listCategory  = new ArrayList<>();

    
    @MapToEntity(mapId = "gameId")
    @ManyToMany
    private List<OrderModel> listOrderWithGame = new ArrayList<>();
    
    private List<String> imgPaths = new ArrayList<>();

    public void setListImg(){
        if(imgPath != null){
            String []imgPathArray = this.imgPath.split(":");
            for (String string : imgPathArray) {
                this.imgPaths.add(string);
            }
        }
    }
    public static void main(String[] args) {
         GameModel model1 = new GameModel();
        model1.setId(1);
        GameModel model2 = new GameModel();
        model2.setId(1);
        System.out.println(model1.equals(model2));
    }
}
