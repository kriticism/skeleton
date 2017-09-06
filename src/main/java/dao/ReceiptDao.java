package dao;

import api.ReceiptResponse;
import generated.tables.records.ReceiptsRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.math.BigDecimal;
import java.util.List;

import static com.google.common.base.Preconditions.checkState;
// we get the below after doing gradle generate
import static generated.Tables.RECEIPTS;

public class ReceiptDao {
    DSLContext dsl;

    public ReceiptDao(Configuration jooqConfig) {
        this.dsl = DSL.using(jooqConfig);
    }

    // The RECEIPTS is an import that was generated
    // if you create a domain model (??) for the Data you want to store,
    // to query it via SQL, you need to generate some code in java that matches whaat is in the database


    // inserts into the DB
    // returns the ID of the inserted receipt
    public int insert(String merchantName, BigDecimal amount) {

        // JOOQ has a tool that allows us to generate classes
        // that allow us to generate classes that drive the DSL from our schema.

        ReceiptsRecord receiptsRecord = dsl
                .insertInto(RECEIPTS, RECEIPTS.MERCHANT, RECEIPTS.AMOUNT)
                .values(merchantName, amount)
                .returning(RECEIPTS.ID)
                .fetchOne();
        // This is a JOOQ function check to see if the insert failed (we egt null)
        //  and make sure that we got an id of the thing that we created
         // if either of the thing is false, the insert failed
        // asserts (throws an exception)

        checkState(receiptsRecord != null && receiptsRecord.getId() != null, "Insert failed");

        return receiptsRecord.getId();
    }

    public ReceiptsRecord getReceiptById(int id){ return dsl.selectFrom(RECEIPTS).where(RECEIPTS.ID.eq(id)).fetchOne();}

    public List<ReceiptsRecord> getAllReceipts() {
        return dsl.selectFrom(RECEIPTS).fetch();
    }
}
