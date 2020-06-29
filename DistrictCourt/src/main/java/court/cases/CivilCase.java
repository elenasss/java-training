package court.cases;

import court.citizen.Accuser;
import court.citizen.Defendant;
import court.citizen.Witness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;

public class CivilCase extends Case {

    private Accuser accuser;
    private static final Logger logger = LoggerFactory.getLogger(Case.class);

    public CivilCase(Defendant defendant, HashSet<Witness> witnesses, Accuser accuser) {
        super(Type.CIVIL_CASE, defendant, witnesses);
        this.setAccuser(accuser);
        logger.debug("civil case created");
    }

    public Accuser getAccuser() {
        return accuser;
    }

    public void setAccuser(Accuser accuser) {
        if (accuser == null) {
            throw new NullPointerException("Accusor cannot be null");
        }

        this.accuser = accuser;
    }
}
