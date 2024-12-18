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
import java.util.ArrayList;

import static javax.ws.rs.core.Response.Status.GATEWAY_TIMEOUT;

@Path("/caller")
public class CallerResource {

    @Inject
    @RestClient
    CallmeProxy callmeProxy;

    @ConfigProperty(name = "POD_ID", defaultValue = "NOT PULL ID! (CALLER)")
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
    public Callme pingCallme(){
        LOG.info("Ping callme: name={}", applicationName);
        Callme callmeResponse = callmeProxy.pingCallme();
        callmeResponse.setCallerPodId(podId);
        return callmeResponse;
    }

    @GET
    @Path("/ping-callme-random-error")
    @Produces(MediaType.APPLICATION_JSON)
    public Callme pingWithRandomError(){
        LOG.info("Ping with random error: name={}", applicationName);
        Callme callmeResponse = callmeProxy.pingWithRandomError();
        callmeResponse.setCallerPodId(podId);
        return callmeResponse;
    }

    @GET
    @Path("/ping-callme-delay")
    @Produces(MediaType.APPLICATION_JSON)
    public Callme pingWithRandomDelay(){
        LOG.info("Ping with random delay: name={}", applicationName);
        Callme callmeResponse = callmeProxy.pingWithRandomDelay();
        callmeResponse.setCallerPodId(podId);
        return callmeResponse;
    }

    @GET
    @Path("/error")
    @Produces(MediaType.APPLICATION_JSON)
    public Response error(){
        return Response.status(GATEWAY_TIMEOUT).build();
    }

    @GET
    @Path("/unhealthy")
    @Produces(MediaType.APPLICATION_JSON)
    public UnhealthyResponse unhealthy(){
        LOG.info("Ping with random delay: name={}", applicationName);
        UnhealthyResponse unhealthyResponse = new UnhealthyResponse();
        unhealthyResponse.status = "DOWN";
        return unhealthyResponse;
    }
}
