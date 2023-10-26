package com.bigcorp.app.correction.rest;

import com.bigcorp.app.correction.model.Restaurant;
import com.bigcorp.app.correction.rest.restdto.RestaurantRestDto;
import com.bigcorp.app.correction.service.RestaurantCorrectionService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/correction/restaurants") 
@Produces("application/json") 
public class RestaurantRestService {
	
	@Inject
	private RestaurantCorrectionService restaurantService;
 
    @GET
    @Path("/{id}") 
    public Response getById(@PathParam("id") Long id) {
       Restaurant restaurant = this.restaurantService.findById(id);
       if (restaurant == null) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("Erreur : aucun restaurant ne correspond à l'id passé en paramètre.")
					.type(MediaType.TEXT_PLAIN)
					.build();
		}
		return Response
				.ok(new RestaurantRestDto(restaurant), MediaType.APPLICATION_JSON)
				.build();
    } 
    
    @DELETE
    @Path("/{id}") 
    public void delete(@PathParam("id") Long id) {
       this.restaurantService.delete(id);
    } 
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RestaurantRestDto postRestaurant(RestaurantRestDto restaurantRestDto) {
    	if(restaurantRestDto == null) {
    		return null;
    	}
    	return new RestaurantRestDto(this.restaurantService.save(restaurantRestDto.toRestaurant()));
    }
    
}