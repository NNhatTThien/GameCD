/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constant.action;

/**
 *
 * @author ASUS
 */
public enum ActionEnum {
    //jsp
    WELCOME_PAGE("", ResourceEnum.WELCOME_PAGE),
    LOGIN_PAGE("login-page", ResourceEnum.LOGIN_PAGE),
    SIGN_UP_PAGE("sign-up-page", ResourceEnum.SIGN_UP_PAGE),
    PAGE_NOT_FOUND("page-not-found", ResourceEnum.PAGE_NOT_FOUND),
    MARKET_PAGE("market-page", ResourceEnum.MARKET_PAGE),
    VIEW_DETAILS("details-game", ResourceEnum.VIEW_DETAILS_GAME),
    VIEW_CART("view-cart", ResourceEnum.VIEW_CART),
    USER_PROFILE_PAGE("profile", ResourceEnum.USER_PROFILE_PAGE),
    //servlet
    SIGN_UP("sign-up", ResourceEnum.SIGN_UP),
    LOGIN("login", ResourceEnum.LOGIN),
    LOGOUT("logout", ResourceEnum.LOGOUT),
    SEARCH_GAME("search-game", ResourceEnum.SEARCH_GAME),
    CART_GAME("cart-game", ResourceEnum.CART_GAME),
    ORDER("order", ResourceEnum.ORDER),
    USER_CHANGE("change-profile", ResourceEnum.USER_CHANGE)
    //folder
    
    
    ;

    private ActionEnum(String key, ResourceEnum resource) {
        this.key = key;
        this.resource = resource;
    }

    private final String key;
    private final ResourceEnum resource;



    public String getKey() {
        return key;
    }

    public ResourceEnum getResource() {
        return resource;
    }
    
    
}
