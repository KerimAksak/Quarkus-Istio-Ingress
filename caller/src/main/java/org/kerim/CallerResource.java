package org.kerim;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.kerim.restclient.CallmeProxy;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;

@Path("/caller")
public class CallerResource {

    @Inject
    @RestClient
    CallmeProxy callmeProxy;

    @ConfigProperty(name = "POD_ID", defaultValue = "NOT PULL ID!")
    String podId;

    @ConfigProperty(name = "quarkus.application.name")
    String applicationName;

    private static final Logger LOG = LogManager.getLogger("CallerResource");

    @GET
    @Path("/ping")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ping(){
        LOG.info("Ping: name={} function={} time={}", applicationName ,"ping()", new SimpleDateFormat("HH:mm:ss")
                .format(new java.util.Date(System.currentTimeMillis())));
        Caller temp = new Caller();
        temp.setId(1L);
        temp.setServiceName("caller-service");
        temp.setPodId(podId);
        temp.setTime(new SimpleDateFormat("HH:mm:ss")
                .format(new java.util.Date(System.currentTimeMillis())));
        temp.setUri("caller-service/caller/ping");
        return Response.ok(temp).build();
    }

    @GET
    @Path("/ping-callme")
    @Produces(MediaType.APPLICATION_JSON)
    public String pingCallme(){
        LOG.info("Ping callme: name={}", applicationName);
        return callmeProxy.pingCallme();
    }

    @GET
    @Path("/ping-callme-random-error")
    @Produces(MediaType.APPLICATION_JSON)
    public String pingWithRandomError(){
        LOG.info("Ping with random error: name={}", applicationName);
        return callmeProxy.pingWithRandomError();
    }

    @GET
    @Path("/ping-callme-delay")
    @Produces(MediaType.APPLICATION_JSON)
    public String pingWithRandomDelay(){
        LOG.info("Ping with random delay: name={}", applicationName);
        return callmeProxy.pingWithRandomDelay();
    }
}
