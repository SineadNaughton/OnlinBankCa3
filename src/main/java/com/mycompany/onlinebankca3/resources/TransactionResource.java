/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.onlinebankca3.resources;

import com.mycompany.onlinebankca3.model.ResponseAccount;
import com.mycompany.onlinebankca3.model.ResponseTransaction;
import com.mycompany.onlinebankca3.model.Transaction;
import com.mycompany.onlinebankca3.services.TransactionService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author SineadNaughton
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransactionResource {

    TransactionService transactionService = new TransactionService();

    @GET
    public Response getAllTransactions(@PathParam("customerId") int customerId, @PathParam("accountNo") int accountNo,
                                                                                @QueryParam("start") String start,
                                                                                @QueryParam("end") String end,
                                                                                @QueryParam("filter") String filter) throws ParseException {
        ResponseTransaction resp = new ResponseTransaction();
        if (start != null && end != null) {
            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(start);
            Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(end);
            resp = transactionService.getTransactionsByDate(customerId, accountNo, startDate, endDate);
        }
        else if (filter != null) {
            resp = transactionService.getTransactionsByDebitorCredit(customerId, accountNo, filter);
        }
        else{
            resp = transactionService.getAllTransactions(customerId, accountNo);
        }
        return Response.status(Response.Status.OK)
                .entity(resp)
                .build();
    }

    @GET
    @Path("/{transactionId}")
    public Response getTransaction(@PathParam("customerId") int customerId, @PathParam("accountNo") int accountNo, @PathParam("transactionId") long transactionId) {
        ResponseTransaction resp = transactionService.getTransaction(customerId, accountNo, transactionId);
        return Response.status(Response.Status.OK)
                .entity(resp)
                .build();
    }

    @POST
    @Path("/deposit")
    public Response depositFunds(@PathParam("customerId") int customerId, @PathParam("accountNo") int accountNo, Transaction transaction) {
        ResponseTransaction resp = transactionService.depoitFunds(customerId, accountNo, transaction);
        return Response.status(Response.Status.OK)
                .entity(resp)
                .build();
    }

    @POST
    @Path("/withdrawal")
    public Response withdrawFunds(@PathParam("customerId") int customerId, @PathParam("accountNo") int accountNo, Transaction transaction) {
        ResponseTransaction resp = transactionService.withdrawFundsDebitCard(customerId, accountNo, transaction);
        return Response.status(Response.Status.OK)
                .entity(resp)
                .build();
    }

    @POST
    @Path("/transfer")
    public Response transferFunds(@PathParam("customerId") int customerId, @PathParam("accountNo") int accountNo, Transaction transaction) {
        ResponseTransaction resp = transactionService.transferFunds(customerId, accountNo, transaction);
        return Response.status(Response.Status.OK)
                .entity(resp)
                .build();
    }

}
