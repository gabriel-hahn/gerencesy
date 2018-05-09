package com.gabrielhahn.gerencesy.resources;

import com.gabrielhahn.gerencesy.model.Board;
import com.gabrielhahn.gerencesy.services.BoardService;
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
@Path("board")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BoardResource {
    
    @Inject
    private BoardService service;
    
    @GET
    public List<Board> findAll() {
        return service.findAll();
    }
    
    @POST
    public Response insert(Board board) {
        Board novo = service.insert(board);
        return Response.status(Response.Status.CREATED)
                .entity(novo).build();
    }
    
    @GET
    @Path("{id}")
    public Board findById(@PathParam("id") Long id) {
        return service.findById(id);
    }
    
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id")Long id, Board board) {
        return Response.ok(service.update(board)).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id")Long id) {
        service.remove(id);
        return Response.noContent().build();
    }
}
