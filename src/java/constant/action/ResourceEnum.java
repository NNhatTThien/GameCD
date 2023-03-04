/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constant.action;

import model.dto.UserModel;

/**
 *
 * @author ASUS
 */
public enum ResourceEnum {
    //JSP
    WELCOME_PAGE("views/web/welcome.jsp", RoleEnum.GUEST),
    LOGIN_PAGE("views/web/login.jsp", RoleEnum.GUEST),
    SIGN_UP_PAGE("views/web/registration.jsp", RoleEnum.GUEST),
    PAGE_NOT_FOUND("views/web/page-not-found.jsp", RoleEnum.GUEST),
    MARKET_PAGE("views/web/market.jsp", RoleEnum.GUEST),
    VIEW_DETAILS_GAME("views/web/details.jsp", RoleEnum.GUEST),
    VIEW_CART("views/web/view-cart.jsp", RoleEnum.GUEST),
    USER_PROFILE_PAGE("views/web/user_profile.jsp", RoleEnum.USER),
    ORDER_PAGE("views/web/order_game.jsp", RoleEnum.GUEST),
    //SERVLE
    LOGIN("LoginController", RoleEnum.GUEST),
    SIGN_UP("RegistrationController", RoleEnum.GUEST),
    LOGOUT("LogoutController", RoleEnum.USER),
    SEARCH_GAME("SearchGameController", RoleEnum.GUEST),
    CART_GAME("CartGameController", RoleEnum.GUEST),
    ORDER("OrderGameController", RoleEnum.GUEST),
    USER_CHANGE("UserChangeController", RoleEnum.USER)
    //folder
    ;

    private ResourceEnum(String resource, RoleEnum role) {
        this.resource = resource;
        this.role = role;
    }

    private ResourceEnum(String resource, RoleEnum role, UserModel model) {
        this.model = model;
        this.resource = resource;
        this.role = role;
    }
    private String resource;
    private RoleEnum role;

    public static ResourceEnum getLOGIN_PAGE() {
        return LOGIN_PAGE;
    }

    public String getResource() {
        return resource;
    }

    public RoleEnum getRole() {
        return role;
    }
    private UserModel model;
}
