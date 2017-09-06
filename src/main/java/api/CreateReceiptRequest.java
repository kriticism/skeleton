package api;

import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;

/**
 * This is an API Object.  It's job is to model and document the JSON API that we expose
 *
 * Fields can be annotated with Validation annotations - these will be applied by the
 * Server when transforming JSON requests into Java objects IFF you specify @Valid in the
 * endpoint.  See {@link controllers.ReceiptController#createReceipt(CreateReceiptRequest)} for
 * and example.
 */
// An obhect that documents the RESTful API
// has two fields that can possibly be specified
public class CreateReceiptRequest {
    // a validation annotation, Merchant Name is required
    // hibernate validator is run
    @NotEmpty
    public String merchant;

    public BigDecimal amount;
}
