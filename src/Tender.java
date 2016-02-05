
public class Tender {
    private TenderType _tenderType;

    public Tender(TenderType tenderType) {
        _tenderType = tenderType;
    }

    public TenderType getTenderType() {
        return _tenderType;
    }

    public boolean isCredit() {
        return _tenderType == TenderType.CREDIT;
    }
}
