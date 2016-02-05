import java.util.Map;

public class FormatInfo {
    public void formatAuthorizationInfo(Journal journal, TenderAction action,
                                        IdentityUtility identityUtility, StringBuffer sb) {

        if (!journal.isAuthorized()) {
            return;
        }

        FormatInfoMethodObject fimo = new FormatInfoMethodObject(journal, action, identityUtility, sb);
        fimo.formatAuthorizationInfo();
    }
}
