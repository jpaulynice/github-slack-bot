package com.project.rest.v1;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.exception.IllegalActionException;
import com.project.model.StartRequest;
import com.project.service.scheduler.Scheduler;

@Component
@Path("scheduler")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class GithubResource {
    private final Scheduler scheduler;

    @Autowired
    public GithubResource(final Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @GET
    @Path("status")
    public Response status() {
        return Response.ok(scheduler.status()).build();
    }

    @POST
    @Path("actions/start")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response start(final StartRequest request) {
        checkParams(request);
        scheduler.start(request.getDelay(), request.getFrequency());
        return Response.status(Status.ACCEPTED).build();
    }

    @POST
    @Path("actions/stop")
    public Response stop() {
        scheduler.stop();
        return Response.status(Status.ACCEPTED).build();
    }

    private void checkParams(final StartRequest request) {
        if (request == null) {
            throw new IllegalActionException("request object can not be null");
        }
        if (request.getDelay() < 0) {
            throw new IllegalActionException(
                    "delay must be greater than or equal to 0");
        }
        if (request.getFrequency() <= 0) {
            throw new IllegalActionException("frequency must be greater than 0");
        }
    }
}