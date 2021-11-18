package org.kerim;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.Random;

import static javax.ws.rs.core.Response.Status.OK;
import static javax.ws.rs.core.Response.Status.GATEWAY_TIMEOUT;
import static javax.ws.rs.core.Response.Status.SERVICE_UNAVAILABLE;

@Path("/callme")
public class CallmeResource {

    @ConfigProperty(name = "POD_ID", defaultValue = "NOT PULL POD ID!")
    String podId;

    @ConfigProperty(name = "quarkus.application.name")
    String applicationName;

    private static final Logger LOG = LogManager.getLogger("CallmeResource");
    private Random random = new Random();

    @GET
    @Path("/ping")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ping(){
        LOG.info("Ping: name={} time={}", applicationName , new SimpleDateFormat("HH:mm:ss")
                .format(new java.util.Date(System.currentTimeMillis())));
        Callme temp = new Callme();
        temp.setId(1L);
        temp.setServiceName("callme-service");
        temp.setPodId(podId);
        temp.setTime(new SimpleDateFormat("HH:mm:ss")
                .format(new java.util.Date(System.currentTimeMillis())));
        temp.setUri("callme-service/callme/ping");
        //return "I'm "+ applicationName + " Timestamp..:" + new SimpleDateFormat("HH:mm:ss").format(new java.util.Date(System.currentTimeMillis()));
        return Response.ok(temp).build();
    }

    @GET
    @Path("/ping-with-random-error")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pingWithRandomError(){
        int r = random.nextInt(100);
        if (r % 2 == 0) {
            LOG.info("Ping with random error: name={}, random={}, httpCode={}, time={}",
                    applicationName, r, GATEWAY_TIMEOUT.getStatusCode(), new SimpleDateFormat("HH:mm:ss")
                            .format(new java.util.Date(System.currentTimeMillis())));
            return Response.status(504).build();
        } else {
            LOG.info("Ping with random correct: name={}, random={}, httpCode={}, time={}",
                    applicationName, r, OK.getStatusCode(),new SimpleDateFormat("HH:mm:ss")
                            .format(new java.util.Date(System.currentTimeMillis())));
            Callme temp = new Callme();
            temp.setId(1L);
            temp.setServiceName("callme-service");
            temp.setPodId(podId);
            temp.setTime(new SimpleDateFormat("HH:mm:ss")
                    .format(new java.util.Date(System.currentTimeMillis())));
            temp.setUri("callme-service/callme/ping");
            return Response.ok(temp).build();
        }
    }

    @GET
    @Path("/ping-with-delay")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pingWithRandomDelay() throws InterruptedException {
        int r = new Random().nextInt(2000);
        LOG.info("Ping with random delay: name={}, delay={}", applicationName, r);
        Thread.sleep(r);
        Callme temp = new Callme();
        temp.setId(1L);
        temp.setServiceName("callme-service");
        temp.setPodId(podId);
        temp.setTime(new SimpleDateFormat("HH:mm:ss")
                .format(new java.util.Date(System.currentTimeMillis())));
        temp.setUri("callme-service/callme/ping");
        return Response.ok(temp).build();
    }

}
