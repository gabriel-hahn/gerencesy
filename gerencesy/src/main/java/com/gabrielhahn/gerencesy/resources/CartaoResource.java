package com.gabrielhahn.gerencesy.resources;

import com.gabrielhahn.gerencesy.model.Cartao;
import com.gabrielhahn.gerencesy.services.CartaoService;

import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author gabrielhahnschaeffer
 */
@Path("cartao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartaoResource {
    
    @Inject
    private CartaoService service;
    
    @GET
    public List<Cartao> findAll() {
        return service.findAll();
    }
    
    @POST
    public Response insert(Cartao cartao) {
        Cartao novo = service.insert(cartao);
        return Response.status(Response.Status.CREATED)
                .entity(novo).build();
    }
    
    @GET
    @Path("{id}")
    public Cartao findById(@PathParam("id") Long id) {
        return service.findById(id);
    }
    
    @PUT
    public Response update(Cartao cartao) {
        service.update(cartao);
        return Response.ok().build();
    }
    
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id")Long id) {
        service.remove(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/dashboard")
    public Response dashboard() {
        List<Cartao> cartoes = service.findAll();
        HashMap cartoesAgrupados = new HashMap();

        //Mapeia os status
        cartoesAgrupados.put("A", 0);
        cartoesAgrupados.put("E", 0);
        cartoesAgrupados.put("P", 0);
        cartoesAgrupados.put("C", 0);

        cartoes.forEach(x -> {
            if(x.getStatus().equals("A")) {
                cartoesAgrupados.put("A", ((Integer) cartoesAgrupados.get("A")) + 1);
            }
            else if(x.getStatus().equals("E")) {
                cartoesAgrupados.put("E", ((Integer) cartoesAgrupados.get("E")) + 1);
            }
            else if(x.getStatus().equals("P")) {
                cartoesAgrupados.put("P", ((Integer) cartoesAgrupados.get("P")) + 1);
            }
            else {
                cartoesAgrupados.put("C", ((Integer) cartoesAgrupados.get("C")) + 1);
            }
        });

        return Response.ok(cartoesAgrupados).build();
    }
}
