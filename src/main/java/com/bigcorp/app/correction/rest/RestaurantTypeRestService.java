package com.bigcorp.app.correction.rest;

import com.bigcorp.app.correction.model.RestaurantType;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("/restaurant-types") // Le nom de la ressource est très généralement au pluriel
@Produces("application/json") // Format incontournable en REST
public class RestaurantTypeRestService {
 
    @GET
    @Path("/{id}") // L’URL finale sera : application+classe+méthode
    public RestaurantType getById(@PathParam("id") String id) {
        RestaurantType restaurantType = new RestaurantType();
		return restaurantType;
    }
    
}