package com.gabrielhahn.gerencesy.rest.resources;

import com.gabrielhahn.gerencesy.model.Cartao;
import com.gabrielhahn.gerencesy.rest.AbstractCrudResource;
import com.gabrielhahn.gerencesy.services.AbstractCrudService;
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
public class CartaoResource extends AbstractCrudResource<Cartao> {
    
    @Inject
    private CartaoService service;

    @Override
    protected AbstractCrudService<Cartao> getService() {
        return service;
    }

    @GET
    @Path("/getByBoardActive")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByBoardActive() {
        return Response.ok(service.findAllBoardActive()).build();
    }

    @GET
    @Path("/dashboard")
    @Produces(MediaType.APPLICATION_JSON)
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
