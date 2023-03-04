/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

import annotation.model.Id;
import annotation.model.ManyToOne;
import annotation.model.MapToEntity;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import service.iml.GameService;
import service.iml.OrderService;

/**
 *
 * @author ASUS
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailModel implements Serializable, IModel {

    @Id
    @MapToEntity(mapId = "id")
    private int id;
    @ManyToOne(getAction = OrderService.class)
    @MapToEntity(mapId = "order")
    private OrderModel order;
    @ManyToOne(getAction = GameService.class)
    @MapToEntity(mapId = "game")
    private GameModel game;
    @MapToEntity(mapId = "id")
    private int quantity;
}
