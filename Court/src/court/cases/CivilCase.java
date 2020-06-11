package court.cases;

import court.citizen.Accuser;
import court.citizen.Defendant;
import court.citizen.Witness;

import java.util.HashSet;

public class CivilCase extends Case {

    private Accuser accuser;

    public CivilCase(Defendant defendant, HashSet<Witness> witnesses, Accuser accuser) {
        super(Type.CIVIL_CASE, defendant, witnesses);
        this.setAccuser(accuser);
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
