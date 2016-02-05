import java.util.Map;

/**
 * a transaction journal with a memento
 */
public class Journal {
    private Map _memento;
    private Tender _tender;
    private boolean _isAuthorized;

    public Journal(Map memento, Tender tender, boolean isAuthorized) {
        _memento = memento;
        _tender = tender;
        _isAuthorized = isAuthorized;
    }

    public Map getMemento() {
        return _memento;
    }

    public Tender getTender() {
        return _tender;
    }

    public boolean isAuthorized() {
        return _isAuthorized;
    }

    public boolean isTenderGiftCard() {
        return _tender.getTenderType() == TenderType.GIFTCARD;
    }

    public boolean isTenderCredit() {
        return _tender.getTenderType() == TenderType.CREDIT;
    }

    public boolean isTenderCheck() {
        return _tender.getTenderType() == TenderType.CHECK;
    }

    public boolean isTenderDebit() {
        return _tender.getTenderType() == TenderType.DEBIT;
    }

    public boolean isTenderCreditCheckOrGiftCard() {
        return isTenderCredit() || isTenderCheck() || isTenderGiftCard();
    }
}
