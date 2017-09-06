package api;


import generated.tables.records.ReceiptsRecord;
import io.dropwizard.jersey.validation.Validators;
import org.junit.Test;

import javax.validation.Validator;
import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;

public class ReceiptResponseTest {

    private final Validator validator = Validators.newValidator();

    @Test
    public void testValidResponse() {
        ReceiptResponse record = new ReceiptResponse();
        record.setId(1);
        record.setMerchant("TESTMERCHANT");
        record.setAmount(new BigDecimal(33.44));
        record.setCreated(Time.valueOf(LocalTime.now()));
        assertThat(validator.validate(record), empty());
    }

    @Test
    public void testMissingMerchant() {
        ReceiptResponse record = new ReceiptResponse();
        record.setId(1);
        // record.setMerchant("TESTMERCHANT");
        record.setAmount(new BigDecimal(33.44));
        record.setCreated(Time.valueOf(LocalTime.now()));
        assertThat(validator.validate(record), hasSize(1));
    }

}