package com.bigcorp.app.correction.rest;

import com.bigcorp.app.correction.model.Restaurant;
import com.bigcorp.app.correction.model.RestaurantType;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/correction/restaurants-bidons") // Le nom de la ressource est très généralement au pluriel
@Produces("application/json") // Format incontournable en REST
public class RestaurantBidonRestService {
 
    @GET
    @Path("/{id}") // L’URL finale sera : application+classe+méthode
    public Restaurant getById(@PathParam("id") Long id) {// Correspond à {id}
        Restaurant restaurant = new Restaurant();
        restaurant.setActive(Boolean.TRUE);
        restaurant.setId(id);
        restaurant.setName("Chez Karim");
        RestaurantType restaurantType = new RestaurantType();
        restaurantType.setId(4l);
        restaurantType.setName("salut");
		restaurant.setRestaurantType(restaurantType);
		return restaurant;
    } 
    
    @GET
    public Restaurant getByIdQueryParam(@QueryParam("id") String id) {
        Restaurant restaurant = new Restaurant();
        restaurant.setActive(Boolean.TRUE);
        restaurant.setId(Long.parseLong(id));
        restaurant.setName("Chez Karim");
        RestaurantType restaurantType = new RestaurantType();
        restaurantType.setId(4l);
        restaurantType.setName("salut");
		restaurant.setRestaurantType(restaurantType);
		return restaurant;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Restaurant postRestaurant(Restaurant restaurant) {
    	if(restaurant == null) {
    		return null;
    	}
    	System.out.println("Je sauvegarde le restaurant : " + restaurant);
        return restaurant;
    }
    
}