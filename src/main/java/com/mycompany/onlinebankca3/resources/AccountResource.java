/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.onlinebankca3.resources;

import com.mycompany.onlinebankca3.model.Account;
import com.mycompany.onlinebankca3.model.ResponseAccount;
import com.mycompany.onlinebankca3.services.AccountService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author SineadNaughton
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountResource {

    AccountService accountService = new AccountService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAccounts(@PathParam("customerId") int customerId) {
        ResponseAccount resp = accountService.getAllAccounts(customerId);
        return Response.status(Response.Status.OK)
                .entity(resp)
                .build();
    }

    //get specific
    @GET
    @Path("/{accountNo}")
    public Response getAccount(@PathParam("customerId") int customerId, @PathParam("accountNo") int accNo) {
        ResponseAccount resp = accountService.getAccount(customerId, accNo);
        return Response.status(Response.Status.OK)
                .entity(resp)
                .build();
    }

    //add
    @POST
    public Response addAccount(@PathParam("customerId") int customerId, Account account) {
        ResponseAccount resp = accountService.addAccount(customerId, account);
        return Response.status(Response.Status.OK)
                .entity(resp)
                .build();
    }

    //update
    @PUT
    @Path("/{accountNo}")
    public Response updateAccountBranch(@PathParam("customerId") int customerId, @PathParam("accountNo") int accNo, Account account) {
        ResponseAccount resp = accountService.updateAccountBranch(customerId, accNo, account);
        return Response.status(Response.Status.OK)
                .entity(resp)
                .build();

    }

    @DELETE
    @Path("/{accountNo}")
    public Response removeAccount(@PathParam("customerId") int customerId, @PathParam("accountNo") int accNo) {
        ResponseAccount resp = accountService.removeAccount(customerId, accNo);
        return Response.status(Response.Status.OK)
                .entity(resp)
                .build();
    }

    @Path("/{accountNo}/transactions")
    public TransactionResource getTransactionResource() {
        return new TransactionResource();
    }
}
