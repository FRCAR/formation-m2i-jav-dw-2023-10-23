package com.bigcorp.app.rest;
 
import com.bigcorp.app.model.Example;

import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
 
@Path("/examples")
@Produces("application/json")
public class ExampleController {
	
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Example getById(@PathParam("id") Long id) {
    	Example example = new Example();
    	example.setId(id);
    	example.setNom("Example numéro : " + id);
		return example;
    }
 
 
    @POST
    @Produces("application/json")
    public Example post(@Valid Example example) {
    	if(example == null) {
    		return null;
    	}
    	System.out.println("Sauvegarde de l'exemple :" + example);
		return example;
    }
    
}