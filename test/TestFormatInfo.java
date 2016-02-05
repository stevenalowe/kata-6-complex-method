import junit.framework.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple characterization tests for FormatInfo
 */
public class TestFormatInfo {
    private static final String NULL_OUTPUT = "\n  Auth. Status: null";
    @Test
    public void TestNullFormatInfo() {
        Map momento = new HashMap();
        momento.put(TenderConstants.TRANSACTION_TYPE, TenderConstants.TYPE_SALE);
        Journal journal = new Journal(momento, new Tender(TenderType.CASH), true);
        IdentityUtility identityUtility = new IdentityUtility(true, true);
        FormatInfo formatInfo = new FormatInfo();
        StringBuffer sb = new StringBuffer();
        formatInfo.formatAuthorizationInfo(journal, TenderAction.Success, identityUtility, sb);
        Assert.assertEquals(NULL_OUTPUT, sb.toString());
    }

    private static final String FULL_OUTPUT = "\n" +
            "  Entry: EM\n" +
            "  Auth. Status: OK\n" +
            "  Auth. Code: GOOD\n" +
            "  Auth. Response: OK\n" +
            "  Available. Balance: 123.45\n" +
            "  Network: OK\n" +
            "  Auth. Response Code: OK\n" +
            "  Name: Test Customer\n" +
            "  Audit Trace Number: T1\n" +
            "  Merchant ID: M1\n" +
            "  Account Type: AT1\n" +
            "  Terminal ID: TID1\n" +
            "  Application Label: AL1\n" +
            "  AID : AID1\n" +
            "  TVR: TVR1\n" +
            "  TSI: TSI1";
    private static Object[] MOMENTO_FULL_VALUES = {
            TenderConstants.ENTRY_METHOD,               "EM",
            TenderConstants.AUTH_STATUS,                "OK",
            TenderConstants.AUTH_CODE,                  "GOOD",
            TenderConstants.AUTH_RESPONSE,              "OK",
            TenderConstants.PREPAIDCARD_BALANCE,        "123.45",
            TenderConstants.AUTH_METHOD,                "AM1",
            TenderConstants.AUTH_AMOUNT,                "123.45",
            TenderConstants.REMAINING_BALANCE,          "0.00",
            TenderConstants.AUTHORIZATION_METHOD,       "AM1",
            TenderConstants.AMOUNT,                     "123.45",
            TenderConstants.FINANCIAL_NETWORK_STATUS,   "OK",
            TenderConstants.AUTH_RESPONSE_CODE,         "OK",
            TenderConstants.FIRST_NAME,                 "Test",
            TenderConstants.LAST_NAME,                  "Customer",
            TenderConstants.AUDIT_TRACE_NUMBER,         "T1",
            TenderConstants.MERCHANT_ID,                "M1",
            TenderConstants.ACCOUNT_TYPE,               "AT1",
            TenderConstants.TERMINAL_ID,                "TID1",
            TenderConstants.APPLICATION_LABEL,          "AL1",
            TenderConstants.TRANSACTION_TYPE,           TenderConstants.TYPE_SALE,
            TenderConstants.AID,                        "AID1",
            TenderConstants.TVR,                        "TVR1",
            TenderConstants.TSI,                        "TSI1"
    };

    @Test
    public void TestFullFormatInfo() {
        Map momento = new HashMap();
        for (int i=0; i<MOMENTO_FULL_VALUES.length; i+=2) {
            momento.put(MOMENTO_FULL_VALUES[i], MOMENTO_FULL_VALUES[i+1]);
        }
        Journal journal = new Journal(momento, new Tender(TenderType.CASH), true);
        IdentityUtility identityUtility = new IdentityUtility(true, true);
        FormatInfo formatInfo = new FormatInfo();
        StringBuffer sb = new StringBuffer();
        formatInfo.formatAuthorizationInfo(journal, TenderAction.Success, identityUtility, sb);
        Assert.assertEquals(FULL_OUTPUT, sb.toString());
    }

    private static final String CREDIT_OUTPUT = "\n" +
            "  Entry: EM\n" +
            "  Auth. Status: OK";

    @Test
    public void TestCreditFormatInfo() {
        Map momento = new HashMap();
        for (int i=0; i<MOMENTO_FULL_VALUES.length; i+=2) {
            momento.put(MOMENTO_FULL_VALUES[i], MOMENTO_FULL_VALUES[i+1]);
        }
        Journal journal = new Journal(momento, new Tender(TenderType.CREDIT), true);
        IdentityUtility identityUtility = new IdentityUtility(true, true);
        FormatInfo formatInfo = new FormatInfo();
        StringBuffer sb = new StringBuffer();
        formatInfo.formatAuthorizationInfo(journal, TenderAction.Success, identityUtility, sb);
        Assert.assertEquals(CREDIT_OUTPUT, sb.toString());
    }

    private static final String NO_OUTPUT = "";

    @Test
    public void TestNotAuthorized() {
        Map momento = new HashMap();
        momento.put(TenderConstants.TRANSACTION_TYPE, TenderConstants.TYPE_SALE);
        Journal journal = new Journal(momento, new Tender(TenderType.CASH), false);
        IdentityUtility identityUtility = new IdentityUtility(true, true);
        FormatInfo formatInfo = new FormatInfo();
        StringBuffer sb = new StringBuffer();
        formatInfo.formatAuthorizationInfo(journal, TenderAction.Success, identityUtility, sb);
        Assert.assertEquals(NO_OUTPUT, sb.toString());
    }

    private static final String DEBIT_OUTPUT = "\n" +
            "  Entry: EM\n" +
            "  Auth. Status: OK\n" +
            "  Auth. Code: GOOD\n" +
            "  Auth. Response: OK\n" +
            "  Available. Balance: 123.45\n" +
            "  Auth. Amt: 123.45\n" +
            "  Auth. Method: AM1\n" +
            "  Network: OK\n" +
            "  Auth. Response Code: OK\n" +
            "  Name: Test Customer\n" +
            "  Audit Trace Number: T1\n" +
            "  Merchant ID: M1\n" +
            "  Account Type: AT1\n" +
            "  Terminal ID: TID1\n" +
            "  Application Label: AL1\n" +
            "  AID : AID1\n" +
            "  TVR: TVR1\n" +
            "  TSI: TSI1";

    @Test
    public void TestDebitFormatInfo() {
        Map momento = new HashMap();
        for (int i=0; i<MOMENTO_FULL_VALUES.length; i+=2) {
            momento.put(MOMENTO_FULL_VALUES[i], MOMENTO_FULL_VALUES[i+1]);
        }
        Journal journal = new Journal(momento, new Tender(TenderType.DEBIT), true);
        IdentityUtility identityUtility = new IdentityUtility(true, true);
        FormatInfo formatInfo = new FormatInfo();
        StringBuffer sb = new StringBuffer();
        formatInfo.formatAuthorizationInfo(journal, TenderAction.Success, identityUtility, sb);
        Assert.assertEquals(DEBIT_OUTPUT, sb.toString());
    }
}
