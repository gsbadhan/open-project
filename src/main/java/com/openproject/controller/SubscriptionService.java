package com.openproject.controller;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.openproject.vo.ChangeRequest;
import com.openproject.vo.OrderRequest;

@Path("/billing")
@WebService(name = "subscriptionService")
public interface SubscriptionService {

    @POST
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/companies/{companyId}/users/{userId}/subscriptions")
    Response newSubscription(@PathParam("companyId") String companyId, @PathParam("userId") String userId,
                             OrderRequest order);

    @PUT
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/companies/{companyId}/users/{userId}/subscriptions/{subscriptionId}")
    Response updateSubscription(@PathParam("companyId") String companyId, @PathParam("userId") String userId,
                                @PathParam("subscriptionId") String subscriptionId, ChangeRequest changeRequest);

    @DELETE
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/subscriptions/{subscriptionId}")
    Response cancelSubscription(@PathParam("subscriptionId") String subscriptionId);

}
