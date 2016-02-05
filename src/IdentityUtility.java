
public class IdentityUtility {
    private boolean _isEMVEnabled;
    private boolean _isPaymentMiddlewareEnabled;

    public IdentityUtility(boolean isEmvEnabled, boolean isPaymentMiddlewareEnabled) {
        _isEMVEnabled = isEmvEnabled;
        _isPaymentMiddlewareEnabled = isPaymentMiddlewareEnabled;
    }

    public boolean isEMVEnabled() {
        return _isEMVEnabled;
    }

    public boolean isPaymentMiddlewareEnabled() {
        return _isPaymentMiddlewareEnabled;
    }

    public boolean isEVMWithPaymentMiddleware() {
        return isEMVEnabled() && isPaymentMiddlewareEnabled();
    }
}
