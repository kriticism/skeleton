package controllers;

import api.CreateReceiptRequest;
import api.ReceiptResponse;
import dao.ReceiptDao;
import generated.tables.records.ReceiptsRecord;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static java.util.stream.Collectors.toList;

// functions in this class can be accesed via -> /receipts
// this is put at class level so that it doesn't have to be repeated for each function
@Path("/receipts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReceiptController {
    final ReceiptDao receipts;

    //constructor
    public ReceiptController(ReceiptDao receipts) {
        this.receipts = receipts;
    }

    // handles POST /receipts
    // a function that accepts as input a HTTP request, manipulates the DB somehow and returns ID
    // this function returns the ID of the receipt
    // input type of the function?
    // What does the function accept? A request.
    // what's the form of the request? An API object.
    // Which API object?  We need to create it!
    // what should be in the API object?  Things that would BE in a receipt.
    // It would specify WHAT things are acceptable as a part of creating receipt request.
    @POST
    public int createReceipt(@Valid @NotNull CreateReceiptRequest receipt) {
        return receipts.insert(receipt.merchant, receipt.amount);
    }

    // handles GET /receipts
    @GET
    public List<ReceiptResponse> getReceipts() {
        List<ReceiptsRecord> receiptRecords = receipts.getAllReceipts();
        // receipts is the Data Access Object (DAO);
        // its purpose is to serialize and deserialize data to and from my persistence store
        // Job of DAO: pushing data from Application --to--> Database
        return receiptRecords.stream().map(ReceiptResponse::new).collect(toList());
    }

}
