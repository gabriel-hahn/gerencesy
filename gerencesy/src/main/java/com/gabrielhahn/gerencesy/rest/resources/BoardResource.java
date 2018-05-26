package com.gabrielhahn.gerencesy.rest.resources;

import com.gabrielhahn.gerencesy.model.Board;
import com.gabrielhahn.gerencesy.rest.AbstractCrudResource;
import com.gabrielhahn.gerencesy.services.AbstractCrudService;
import com.gabrielhahn.gerencesy.services.BoardService;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author gabrielhahnschaeffer
 */
@Path("board")
public class BoardResource extends AbstractCrudResource<Board> {

    @Inject
    private BoardService service;

    @Override
    protected AbstractCrudService<Board> getService() {
        return service;
    }

    @GET
    @Path("/getIdBoardCheck")
    public Long getIdBoardCheck() {
        return service.getIdBoardCheck();
    }
    
    @POST
    public Response insert(Board board) {
        Board novo = service.insert(board);
        return Response.status(Response.Status.CREATED)
                .entity(novo).build();
    }

    @PUT
    @Path("/changeBoardActive/{id}")
    public Response changeBoardActive(@PathParam("id") Long id) {
        return Response.ok(service.changeBoardActive(id)).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id")Long id) {
        service.remove(id);
        return Response.noContent().build();
    }
}
