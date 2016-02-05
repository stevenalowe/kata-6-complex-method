import java.util.Map;

public class FormatInfo {
    public void formatAuthorizationInfo(Journal journal, TenderAction action,
                                        IdentityUtility identityUtility, StringBuffer sb) {
        Tender tender = journal.getTender();
        TenderMemento tenderMemento = new TenderMemento(journal.getMemento());

        if (!journal.isAuthorized()) {
            return;
        }

        if (identityUtility.isEMVEnabled() && identityUtility.isPaymentMiddlewareEnabled()) {
            if (tenderMemento.hasEntryMethod() && !journal.isTenderGiftCard()) {
                sb.append(Util.EOL).append("  Entry: ").append(tenderMemento.getEntryMethod());
            }
        }

        sb.append(Util.EOL).append("  Auth. Status: ").append(tenderMemento.getAuthStatus());

        if (identityUtility.isEMVEnabled() &&
                identityUtility.isPaymentMiddlewareEnabled() &&
                action == TenderAction.Success &&
                tender.getTenderType() == TenderType.CREDIT) {
            return;
        }

        if (tenderMemento.hasAuthCode()) {
            if (journal.isTenderGiftCard())
                sb.append(Util.EOL).append("  Approval Code: ");
            else
                sb.append(Util.EOL).append("  Auth. Code: ");
            sb.append(tenderMemento.getAuthCode());
        }
        if (tenderMemento.hasAuthResponse()) {
            if (identityUtility.isEMVEnabled() &&
                    identityUtility.isPaymentMiddlewareEnabled()) {
                sb.append(Util.EOL).append("  Auth. Response: ").append(tenderMemento.getAuthResponse().replaceAll(" ", ""));
            } else {
                sb.append(Util.EOL).append("  Auth. Response: ").append(tenderMemento.getAuthResponse());
            }
        }

        if (tenderMemento.hasPrepaidCardBalance()) {
            sb.append(Util.EOL).append("  Available. Balance: ").append(tenderMemento.getPrepaidCardBalance());

        }

        if (journal.isTenderCredit() || journal.isTenderCheck() || journal.isTenderGiftCard()) {
            if (tenderMemento.hasAuthMethod()) {
                sb.append(Util.EOL).append("  Auth. Method: ").append(tenderMemento.getAuthMethod());
            }
            if (tenderMemento.hasAuthAmount()) {
                if (journal.isTenderGiftCard())
                    sb.append(Util.EOL).append("  Approved Amt: ");
                else
                    sb.append(Util.EOL).append("  Auth. Amt: ");
                sb.append(tenderMemento.getAuthAmount());
            }
            if (tenderMemento.hasRemainingBalance()) {
                sb.append(Util.EOL).append("  Remaining Balance: ").append(tenderMemento.getRemainingBalance());
            }
        }
        if (journal.isTenderDebit()) {
            if (tenderMemento.hasAuthAmount()) {
                sb.append(Util.EOL).append("  Auth. Amt: ");
                sb.append(tenderMemento.getAuthAmount());
            }
            if (tenderMemento.hasAuthorizationMethod()) {
                sb.append(Util.EOL).append("  Auth. Method: ");
                sb.append(tenderMemento.getAuthorizationMethod());
            }
        }
        if (journal.isTenderGiftCard()) {
            if (tenderMemento.hasAmount()) {
                sb.append(Util.EOL).append("  Requested Amt: ").append(tenderMemento.getAmount());
            }
        }
        if (tenderMemento.hasFinancialNetworkStatus()) {
            sb.append(Util.EOL).append("  Network: ").append(tenderMemento.getFinancialNetworkStatus());
        }
        if (tenderMemento.hasAuthResponseCode()) {
            sb.append(Util.EOL).append("  Auth. Response Code: ").append(tenderMemento.getAuthResponseCode());
        }
        if (tenderMemento.hasFullName()) {
            sb.append(Util.EOL).append("  Name: ").append(tenderMemento.getFullName());
        }
        if (tenderMemento.hasAuditTraceNumber()) {
            sb.append(Util.EOL).append("  Audit Trace Number: ").append(tenderMemento.getAuditTraceNumber());
        }

        if (tenderMemento.hasMerchantId()) {
            sb.append(Util.EOL).append("  Merchant ID: ").append(tenderMemento.getMerchantId());
        }
        if (tenderMemento.hasAccountType()) {
            sb.append(Util.EOL).append("  Account Type: ").append(tenderMemento.getAccountType());
        }
        if (tenderMemento.hasTerminalId()) {
            sb.append(Util.EOL).append("  Terminal ID: ").append(tenderMemento.getTerminalId());
        }

        if (identityUtility.isEMVEnabled() && identityUtility.isPaymentMiddlewareEnabled()) {

            if (tenderMemento.hasApplicationLabel() &&
                    !tenderMemento.getApplicationLabel().equals("0")) {
                sb.append(Util.EOL).append("  Application Label: ").append(tenderMemento.getApplicationLabel());
            }
            if (tenderMemento.hasAID() && !tenderMemento.getAID().equals("0")) {
                sb.append(Util.EOL).append("  AID : ").append(tenderMemento.getAID());
            }

            Integer transactionType = tenderMemento.getTransactionType();
            if (!transactionType.equals(TenderConstants.TYPE_RETURN) &&
                    !transactionType.equals(TenderConstants.TYPE_VOID)) {
                if (tenderMemento.hasTVR() && !"0".equals(tenderMemento.getTVR())) {
                    sb.append(Util.EOL).append("  TVR: ").append(tenderMemento.getTVR());
                }
                if (tenderMemento.hasTSI() && !"0".equals(tenderMemento.getTSI())) {
                    sb.append(Util.EOL).append("  TSI: ").append(tenderMemento.getTSI());
                }
            }
        }
    }
}
