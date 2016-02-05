
public class FormatInfoMethodObject {
    private Journal journal;
    private TenderAction action;
    private IdentityUtility identityUtility;
    private StringBuffer sb;
    private Tender tender;
    private TenderMemento tenderMemento;

    public FormatInfoMethodObject(Journal aJournal, TenderAction anAction,
                                  IdentityUtility anIdentityUtility, StringBuffer aStringBuffer) {
        journal = aJournal;
        action = anAction;
        identityUtility = anIdentityUtility;
        sb = aStringBuffer;
        tender = journal.getTender();
        tenderMemento = new TenderMemento(journal.getMemento());
    }

    public void formatAuthorizationInfo() {
        outputEntryMethod();
        outputAuthStatus();

        if (isSuccessfulCreditEvmWithPaymentMiddleware()) {
            return;
        }

        outputAuthCode();
        outputAuthResponse();
        outputPrepaidCardBalance();

        if (isTenderCreditCheckOrGiftCard()) {
            outputAuthMethod();
            outputTenderAuthAmount();
            outputRemainingBalance();
        }

        if (isTenderDebit()) {
            outputAuthAmount();
            outputAuthorizationMethod();
        }

        outputGiftCardAmount();
        outputFinancialNetworkStatus();
        outputAuthResponseCode();
        outputFullName();
        outputAuditTraceNumber();
        outputMerchantId();
        outputAccountType();
        outputTerminalId();

        if (isEvmWithPaymentMiddleware()) {

            outputApplicationLabel();
            outputAID();

            if (hasTransactionTypeNotReturnOrVoid()) {
                outputTVR();
                outputTSI();
            }
        }
    }

    private boolean hasTransactionTypeNotReturnOrVoid() {
        return tenderMemento.hasTransactionTypeNotReturnOrVoid();
    }

    private boolean isEvmWithPaymentMiddleware() {
        return identityUtility.isEVMWithPaymentMiddleware();
    }

    private boolean isTenderDebit() {
        return journal.isTenderDebit();
    }

    private boolean isTenderCreditCheckOrGiftCard() {
        return journal.isTenderCreditCheckOrGiftCard();
    }

    private boolean isSuccessfulCreditEvmWithPaymentMiddleware() {
        return isEvmWithPaymentMiddleware() &&
                action == TenderAction.Success &&
                tender.isCredit();
    }

    private void outputTSI() {
        if (tenderMemento.hasNonZeroTSI()) {
            sb.append(Util.EOL).append("  TSI: ").append(tenderMemento.getTSI());
        }
    }

    private void outputTVR() {
        if (tenderMemento.hasNonZeroTVR()) {
            sb.append(Util.EOL).append("  TVR: ").append(tenderMemento.getTVR());
        }
    }

    private void outputAID() {
        if (tenderMemento.hasNonZeroAID()) {
            sb.append(Util.EOL).append("  AID : ").append(tenderMemento.getAID());
        }
    }

    private void outputApplicationLabel() {
        if (tenderMemento.hasNonZeroApplicationLabel()) {
            sb.append(Util.EOL).append("  Application Label: ").append(tenderMemento.getApplicationLabel());
        }
    }

    private void outputTerminalId() {
        if (tenderMemento.hasTerminalId()) {
            sb.append(Util.EOL).append("  Terminal ID: ").append(tenderMemento.getTerminalId());
        }
    }

    private void outputAccountType() {
        if (tenderMemento.hasAccountType()) {
            sb.append(Util.EOL).append("  Account Type: ").append(tenderMemento.getAccountType());
        }
    }

    private void outputMerchantId() {
        if (tenderMemento.hasMerchantId()) {
            sb.append(Util.EOL).append("  Merchant ID: ").append(tenderMemento.getMerchantId());
        }
    }

    private void outputAuditTraceNumber() {
        if (tenderMemento.hasAuditTraceNumber()) {
            sb.append(Util.EOL).append("  Audit Trace Number: ").append(tenderMemento.getAuditTraceNumber());
        }
    }

    private void outputFullName() {
        if (tenderMemento.hasFullName()) {
            sb.append(Util.EOL).append("  Name: ").append(tenderMemento.getFullName());
        }
    }

    private void outputAuthResponseCode() {
        if (tenderMemento.hasAuthResponseCode()) {
            sb.append(Util.EOL).append("  Auth. Response Code: ").append(tenderMemento.getAuthResponseCode());
        }
    }

    private void outputFinancialNetworkStatus() {
        if (tenderMemento.hasFinancialNetworkStatus()) {
            sb.append(Util.EOL).append("  Network: ").append(tenderMemento.getFinancialNetworkStatus());
        }
    }

    private void outputGiftCardAmount() {
        if (journal.isTenderGiftCard() && tenderMemento.hasAmount()) {
            sb.append(Util.EOL).append("  Requested Amt: ").append(tenderMemento.getAmount());
        }
    }

    private void outputAuthorizationMethod() {
        if (tenderMemento.hasAuthorizationMethod()) {
            sb.append(Util.EOL).append("  Auth. Method: ");
            sb.append(tenderMemento.getAuthorizationMethod());
        }
    }

    private void outputRemainingBalance() {
        if (tenderMemento.hasRemainingBalance()) {
            sb.append(Util.EOL).append("  Remaining Balance: ").append(tenderMemento.getRemainingBalance());
        }
    }

    private void outputAuthAmount() {
        if (tenderMemento.hasAuthAmount()) {
            sb.append(Util.EOL).append("  Auth. Amt: ");
            sb.append(tenderMemento.getAuthAmount());
        }
    }

    private void outputTenderAuthAmount() {
        if (tenderMemento.hasAuthAmount()) {
            if (journal.isTenderGiftCard()) {
                sb.append(Util.EOL).append("  Approved Amt: ");
            }
            else {
                sb.append(Util.EOL).append("  Auth. Amt: ");
            }
            sb.append(tenderMemento.getAuthAmount());
        }
    }

    private void outputAuthMethod() {
        if (tenderMemento.hasAuthMethod()) {
            sb.append(Util.EOL).append("  Auth. Method: ").append(tenderMemento.getAuthMethod());
        }
    }

    private void outputPrepaidCardBalance() {
        if (tenderMemento.hasPrepaidCardBalance()) {
            sb.append(Util.EOL).append("  Available. Balance: ").append(tenderMemento.getPrepaidCardBalance());
        }
    }

    private void outputAuthResponse() {
        if (tenderMemento.hasAuthResponse()) {
            if (isEvmWithPaymentMiddleware()) {
                sb.append(Util.EOL).append("  Auth. Response: ").append(tenderMemento.getAuthResponse().replaceAll(" ", ""));
            }
            else {
                sb.append(Util.EOL).append("  Auth. Response: ").append(tenderMemento.getAuthResponse());
            }
        }
    }

    private void outputAuthCode() {
        if (tenderMemento.hasAuthCode()) {
            if (journal.isTenderGiftCard()) {
                sb.append(Util.EOL).append("  Approval Code: ");
            }
            else {
                sb.append(Util.EOL).append("  Auth. Code: ");
            }
            sb.append(tenderMemento.getAuthCode());
        }
    }

    private void outputAuthStatus() {
        sb.append(Util.EOL).append("  Auth. Status: ").append(tenderMemento.getAuthStatus());
    }

    private void outputEntryMethod() {
        if (isEvmWithPaymentMiddleware() &&
                tenderMemento.hasEntryMethod() && !journal.isTenderGiftCard()) {
            sb.append(Util.EOL).append("  Entry: ").append(tenderMemento.getEntryMethod());
        }
    }
}