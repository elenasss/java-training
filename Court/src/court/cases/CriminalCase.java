package court.cases;

import court.citizen.Defendant;
import court.citizen.Witness;
import court.jurists.Prosecutor;

import java.util.HashSet;

public class CriminalCase extends Case {

    private Prosecutor prosecutor;

    public CriminalCase(Defendant defendant, HashSet<Witness> witnesses, Prosecutor prosecutor) {
        super(Type.CRIMINAL_CASE, defendant, witnesses);
        this.prosecutor = prosecutor;
    }

    public Prosecutor getProsecutor() {
        return prosecutor;
    }
}