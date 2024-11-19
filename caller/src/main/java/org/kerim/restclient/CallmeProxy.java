package org.kerim.restclient;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.kerim.Callme;

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
    Callme pingCallme();

    @GET
    @Path("/ping-with-random-error")
    @Produces(MediaType.APPLICATION_JSON)
    Callme pingWithRandomError();

    @GET
    @Path("/ping-with-delay")
    @Produces(MediaType.APPLICATION_JSON)
    Callme pingWithRandomDelay();

}
