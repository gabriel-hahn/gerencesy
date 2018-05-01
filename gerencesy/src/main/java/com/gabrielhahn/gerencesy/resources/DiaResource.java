package com.gabrielhahn.gerencesy.resources;

import com.gabrielhahn.gerencesy.model.Dia;
import com.gabrielhahn.gerencesy.services.DiaService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author gabrielhahnschaeffer
 */
@Path("dia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DiaResource {
    
    @Inject
    private DiaService service;
    
    @GET
    public List<Dia> findAll() {
        return service.findAll();
    }
    
    @POST
    public Response insert(Dia dia) {
        Dia novo = service.insert(dia);
        return Response.status(Response.Status.CREATED)
                .entity(novo).build();
    }
    
    @GET
    @Path("{id}")
    public Dia findById(@PathParam("id") Long id) {
        return service.findById(id);
    }
    
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id")Long id, Dia dia) {
        return Response.ok(service.update(dia)).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id")Long id) {
        service.remove(id);
        return Response.noContent().build();
    }
}
