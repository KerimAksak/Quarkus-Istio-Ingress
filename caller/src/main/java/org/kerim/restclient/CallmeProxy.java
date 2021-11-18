package org.kerim.restclient;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/callme")
@RegisterRestClient
public interface CallmeProxy {

    @GET
    @Path("/ping")
    @Produces(MediaType.APPLICATION_JSON)
    public String pingCallme();

    @GET
    @Path("/ping-with-random-error")
    @Produces(MediaType.APPLICATION_JSON)
    String pingWithRandomError();

    @GET
    @Path("/ping-with-delay")
    @Produces(MediaType.APPLICATION_JSON)
    String pingWithRandomDelay();

}
