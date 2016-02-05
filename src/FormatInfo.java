import java.util.Map;

public class FormatInfo {
    public void formatAuthorizationInfo(Journal journal, TenderAction action,
                                        IdentityUtility identityUtility, StringBuffer sb) {
        Map memento = journal.getMemento();
        Tender tender = journal.getTender();

        if (journal.isAuthorized()) {
            if (identityUtility.isEMVEnabled() && identityUtility.isPaymentMiddlewareEnabled()) {
                if (memento.get(TenderConstants.ENTRY_METHOD) != null &&
                        !journal.isTenderGiftCard()) {
                    sb.append(Util.EOL).append("  Entry: ").append(
                            (String) memento.get(TenderConstants.ENTRY_METHOD));
                }
            }

            sb.append(Util.EOL).append("  Auth. Status: ").append(memento.get(TenderConstants.AUTH_STATUS));

            if (identityUtility.isEMVEnabled() &&
                    identityUtility.isPaymentMiddlewareEnabled() &&
                    action == TenderAction.Success &&
                    tender.getTenderType() == TenderType.CREDIT) {
                return;
            }

            if (memento.get(TenderConstants.AUTH_CODE) != null) {
                if (journal.isTenderGiftCard())
                    sb.append(Util.EOL).append("  Approval Code: ");
                else
                    sb.append(Util.EOL).append("  Auth. Code: ");
                sb.append((String) memento.get(TenderConstants.AUTH_CODE));
            }
            if (memento.get(TenderConstants.AUTH_RESPONSE) != null) {
                if (identityUtility.isEMVEnabled() &&
                        identityUtility.isPaymentMiddlewareEnabled() &&
                        memento.get(TenderConstants.AUTH_RESPONSE) != null) {
                    sb.append(Util.EOL).append("  Auth. Response: ").append(((String) memento.get(TenderConstants.AUTH_RESPONSE)).replaceAll(" ", ""));
                } else {
                    sb.append(Util.EOL).append("  Auth. Response: ").append(
                            (String) memento.get(TenderConstants.AUTH_RESPONSE));
                }
            }

            if (memento.get(TenderConstants.PREPAIDCARD_BALANCE) != null) {
                sb.append(Util.EOL).append("  Available. Balance: ").append(
                        memento.get(TenderConstants.PREPAIDCARD_BALANCE));

            }

            if (journal.isTenderCredit() || journal.isTenderCheck() || journal.isTenderGiftCard()) {
                if (memento.get(TenderConstants.AUTH_METHOD) != null) {
                    sb.append(Util.EOL).append("  Auth. Method: ").append(
                            (String) memento.get(TenderConstants.AUTH_METHOD));
                }
                if (memento.get(TenderConstants.AUTH_AMOUNT) != null) {
                    if (journal.isTenderGiftCard())
                        sb.append(Util.EOL).append("  Approved Amt: ");
                    else
                        sb.append(Util.EOL).append("  Auth. Amt: ");
                    sb.append(
                            (String) memento.get(TenderConstants.AUTH_AMOUNT));
                }
                if (memento.get(TenderConstants.REMAINING_BALANCE) != null) {
                    sb.append(Util.EOL).append("  Remaining Balance: ").append(memento.get(TenderConstants.REMAINING_BALANCE));
                }
            }
            if (journal.isTenderDebit()) {
                if (memento.get(TenderConstants.AUTH_AMOUNT) != null) {
                    sb.append(Util.EOL).append("  Auth. Amt: ");
                    sb.append((String) memento.get(TenderConstants.AUTH_AMOUNT));
                }
                if (memento.get(TenderConstants.AUTHORIZATION_METHOD) != null) {
                    sb.append(Util.EOL).append("  Auth. Method: ");
                    sb.append((String) memento.get(TenderConstants.AUTHORIZATION_METHOD));
                }
            }
            if (journal.isTenderGiftCard()) {
                if (memento.get(TenderConstants.AMOUNT) != null)
                    sb.append(Util.EOL).append("  Requested Amt: ").append(
                            (String) memento.get(TenderConstants.AMOUNT));
            }
            if (memento.get(TenderConstants.FINANCIAL_NETWORK_STATUS) != null) {
                sb.append(Util.EOL).append("  Network: ").append(
                        (String) memento.get(TenderConstants.FINANCIAL_NETWORK_STATUS));
            }
            if (memento.get(TenderConstants.AUTH_RESPONSE_CODE) != null) {
                sb.append(Util.EOL).append("  Auth. Response Code: ").append(
                        (String) memento.get(TenderConstants.AUTH_RESPONSE_CODE));
            }
            if (memento.get(TenderConstants.FIRST_NAME) != null &&
                    memento.get(TenderConstants.LAST_NAME) != null) {
                sb.append(Util.EOL).append("  Name: ").append(
                        (String) memento.get(TenderConstants.FIRST_NAME)).append(" ").append(
                        (String) memento.get(TenderConstants.LAST_NAME));
            }
            if (memento.get(TenderConstants.AUDIT_TRACE_NUMBER) != null) {
                sb.append(Util.EOL).append("  Audit Trace Number: ").append(
                        (String) memento.get(TenderConstants.AUDIT_TRACE_NUMBER));
            }

            if (memento.get(TenderConstants.MERCHANT_ID) != null) {
                sb.append(Util.EOL).append("  Merchant ID: ").append(
                        (String) memento.get(TenderConstants.MERCHANT_ID));
            }
            if (memento.get(TenderConstants.ACCOUNT_TYPE) != null) {
                sb.append(Util.EOL).append("  Account Type: ").append(
                        (String) memento.get(TenderConstants.ACCOUNT_TYPE));
            }
            if (memento.get(TenderConstants.TERMINAL_ID) != null) {
                sb.append(Util.EOL).append("  Terminal ID: ").append(
                        (String) memento.get(TenderConstants.TERMINAL_ID));
            }

            if (identityUtility.isEMVEnabled() && identityUtility.isPaymentMiddlewareEnabled()) {

                if (memento.get(TenderConstants.APPLICATION_LABEL) != null &&
                        !memento.get(TenderConstants.APPLICATION_LABEL).equals("0")) {
                    sb.append(Util.EOL).append("  Application Label: ").append(
                            (String) memento.get(TenderConstants.APPLICATION_LABEL));
                }
                if (memento.get(TenderConstants.AID) != null && !memento.get(TenderConstants.AID).equals("0")) {
                    sb.append(Util.EOL).append("  AID : ").append(
                            (String) memento.get(TenderConstants.AID));
                }

                Integer transactionType = (Integer) memento.get(TenderConstants.TRANSACTION_TYPE);
                if (!transactionType.equals(TenderConstants.TYPE_RETURN) &&
                        !transactionType.equals(TenderConstants.TYPE_VOID)) {
                    if (memento.get(TenderConstants.TVR) != null && !"0".equals(memento.get(TenderConstants.TVR))) {
                        sb.append(Util.EOL).append("  TVR: ").append(
                                (String) memento.get(TenderConstants.TVR));
                    }
                    if (memento.get(TenderConstants.TSI) != null && !"0".equals(memento.get(TenderConstants.TSI))) {
                        sb.append(Util.EOL).append("  TSI: ").append(
                                (String) memento.get(TenderConstants.TSI));
                    }
                }
            }
        }

    }

}
