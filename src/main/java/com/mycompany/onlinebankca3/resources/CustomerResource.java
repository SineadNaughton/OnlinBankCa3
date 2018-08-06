/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.onlinebankca3.resources;

import com.mycompany.onlinebankca3.model.Customer;
import com.mycompany.onlinebankca3.model.ResponseCustomer;
import com.mycompany.onlinebankca3.services.CustomerService;
import java.net.URI;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author SineadNaughton
 */
@Path("/customers")
public class CustomerResource {

    CustomerService customerService = new CustomerService();

    //get all returns list of customers 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }

    //get specific customer
    @GET
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomer(@PathParam("customerId") int id, @Context UriInfo uriInfo) {
        ResponseCustomer resp = customerService.getCustomer(id);
        //customer.addLink(getUriForSelf(uriInfo, customer), "self");
        //customer.addLink(getUriAccounts(uriInfo, customer), "accounts");
        return Response.status(Response.Status.OK)
                //.entity(customer)
                .entity(resp)
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCustomer(Customer customer, @Context UriInfo uriInfo) {
        ResponseCustomer resp = customerService.addCustomer(customer);
        //String newId = String.valueOf(customer.getCustomerId());
        //URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();

        // ResponseCustomer resp = new ResponseCustomer();
        //resp.setMessage("Customer has been sucessfully created");
        // resp.setObject(customer);
        return Response.status(Response.Status.OK)
                //.entity(customer)
                .entity(resp)
                .build();
    }

    @PUT
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCustomer(@PathParam("customerId") int id, Customer customer) {
        // customer.setCustomerId(id);
        ResponseCustomer resp = customerService.updateCustomer(id, customer);
        return Response.status(Response.Status.OK)
                //.entity(customer)
                .entity(resp)
                .build();
    }

    @DELETE
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteCustomer(@PathParam("customerId") int id) {
        ResponseCustomer resp = customerService.removeCustomer(id);

        return Response.status(Response.Status.OK)
                //.entity(customer)
                .entity(resp)
                .build();
    }

    private String getUriForSelf(UriInfo uriInfo, Customer customer) {
        String uri = uriInfo.getBaseUriBuilder()
                .path(CustomerResource.class)
                .path(Long.toString(customer.getCustomerId()))
                .build()
                .toString();
        return uri;
    }

    private String getUriAccounts(UriInfo uriInfo, Customer customer) {
        URI uri = uriInfo.getBaseUriBuilder()
                .path(CustomerResource.class)
                .path(CustomerResource.class, "getAccountResource")
                .path(AccountResource.class)
                .resolveTemplate("customerId", customer.getCustomerId())
                .build();
        return uri.toString();
    }

    //subresource of account
    @Path("/{customerId}/accounts")
    public AccountResource getAccountResource() {
        return new AccountResource();
    }
}
