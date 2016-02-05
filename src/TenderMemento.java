import java.util.Map;

/**
 * wrapper for tender memento map
 */
public class TenderMemento {
    private Map _memento;
    public TenderMemento(Map memento) {
        _memento = memento;
    }

    public boolean hasEntryMethod() {
        return _memento.get(TenderConstants.ENTRY_METHOD) != null;
    }

    public String getEntryMethod() {
        return (String)_memento.get(TenderConstants.ENTRY_METHOD);
    }

    public String getAuthStatus() {
        return (String)_memento.get(TenderConstants.AUTH_STATUS);
    }

    public boolean hasAuthCode() {
        return _memento.get(TenderConstants.AUTH_CODE) != null;
    }

    public String getAuthCode() {
        return (String)_memento.get(TenderConstants.AUTH_CODE);
    }

    public boolean hasAuthResponse() {
        return _memento.get(TenderConstants.AUTH_RESPONSE) != null;
    }

    public String getAuthResponse() {
        return (String)_memento.get(TenderConstants.AUTH_RESPONSE);
    }

    public boolean hasPrepaidCardBalance() {
        return _memento.get(TenderConstants.PREPAIDCARD_BALANCE) != null;
    }

    public String getPrepaidCardBalance() {
        return (String)_memento.get(TenderConstants.PREPAIDCARD_BALANCE);
    }

    public boolean hasAuthMethod() {
        return _memento.get(TenderConstants.AUTH_METHOD) != null;
    }

    public String getAuthMethod() {
        return (String)_memento.get(TenderConstants.AUTH_METHOD);
    }

    public boolean hasAuthAmount() {
        return _memento.get(TenderConstants.AUTH_AMOUNT) != null;
    }

    public String getAuthAmount() {
        return (String)_memento.get(TenderConstants.AUTH_AMOUNT);
    }

    public boolean hasRemainingBalance() {
        return _memento.get(TenderConstants.REMAINING_BALANCE) != null;
    }

    public String getRemainingBalance() {
        return (String)_memento.get(TenderConstants.REMAINING_BALANCE);
    }

    public boolean hasAuthorizationMethod() {
        return _memento.get(TenderConstants.AUTHORIZATION_METHOD) != null;
    }

    public String getAuthorizationMethod() {
        return (String)_memento.get(TenderConstants.AUTHORIZATION_METHOD);
    }

    public boolean hasAmount() {
        return _memento.get(TenderConstants.AMOUNT) != null;
    }

    public String getAmount() {
        return (String)_memento.get(TenderConstants.AMOUNT);
    }

    public boolean hasFinancialNetworkStatus() {
        return _memento.get(TenderConstants.FINANCIAL_NETWORK_STATUS) != null;
    }

    public String getFinancialNetworkStatus() {
        return (String)_memento.get(TenderConstants.FINANCIAL_NETWORK_STATUS);
    }

    public boolean hasAuthResponseCode() {
        return _memento.get(TenderConstants.AUTH_RESPONSE_CODE) != null;
    }

    public String getAuthResponseCode() {
        return (String)_memento.get(TenderConstants.AUTH_RESPONSE_CODE);
    }

    public boolean hasFirstName() {
        return _memento.get(TenderConstants.FIRST_NAME) != null;
    }

    public String getFirstName() {
        return (String)_memento.get(TenderConstants.FIRST_NAME);
    }

    public boolean hasLastName() {
        return _memento.get(TenderConstants.LAST_NAME) != null;
    }

    public String getLastName() {
        return (String)_memento.get(TenderConstants.LAST_NAME);
    }

    public boolean hasFullName() {
        return hasFirstName() && hasLastName();
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    public boolean hasAuditTraceNumber() {
        return _memento.get(TenderConstants.AUDIT_TRACE_NUMBER) != null;
    }

    public String getAuditTraceNumber() {
        return (String)_memento.get(TenderConstants.AUDIT_TRACE_NUMBER);
    }

    public boolean hasMerchantId() {
        return _memento.get(TenderConstants.MERCHANT_ID) != null;
    }

    public String getMerchantId() {
        return (String)_memento.get(TenderConstants.MERCHANT_ID);
    }

    public boolean hasAccountType() {
        return _memento.get(TenderConstants.ACCOUNT_TYPE) != null;
    }

    public String getAccountType() {
        return (String)_memento.get(TenderConstants.ACCOUNT_TYPE);
    }

    public boolean hasTerminalId() {
        return _memento.get(TenderConstants.TERMINAL_ID) != null;
    }

    public String getTerminalId() {
        return (String)_memento.get(TenderConstants.TERMINAL_ID);
    }

    public boolean hasApplicationLabel() {
        return _memento.get(TenderConstants.APPLICATION_LABEL) != null;
    }

    public boolean hasNonZeroApplicationLabel() {
        return hasApplicationLabel() && !getApplicationLabel().equals("0");
    }

    public String getApplicationLabel() {
        return (String)_memento.get(TenderConstants.APPLICATION_LABEL);
    }

    public boolean hasAID() {
        return _memento.get(TenderConstants.AID) != null;
    }

    public boolean hasNonZeroAID() {
        return hasAID() && !getAID().equals("0");
    }

    public String getAID() {
        return (String)_memento.get(TenderConstants.AID);
    }

    public boolean hasTransactionType() {
        return _memento.get(TenderConstants.TRANSACTION_TYPE) != null;
    }

    public Integer getTransactionType() {
        return (Integer)_memento.get(TenderConstants.TRANSACTION_TYPE);
    }

    public boolean hasTransactionTypeNotReturnOrVoid() {
        if (!hasTransactionType()) {
            return false;
        }
        Integer transactionType = getTransactionType();
        return !transactionType.equals(TenderConstants.TYPE_RETURN) &&
                !transactionType.equals(TenderConstants.TYPE_VOID);
    }

    public boolean hasTVR() {
        return _memento.get(TenderConstants.TVR) != null;
    }

    public boolean hasNonZeroTVR() {
        return hasTVR() && !"0".equals(getTVR());
    }

    public String getTVR() {
        return (String)_memento.get(TenderConstants.TVR);
    }

    public boolean hasTSI() {
        return _memento.get(TenderConstants.TSI) != null;
    }

    public boolean hasNonZeroTSI() {
        return hasTSI() && !"0".equals(getTSI());
    }

    public String getTSI() {
        return (String)_memento.get(TenderConstants.TSI);
    }

}
