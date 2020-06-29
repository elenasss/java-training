package court.citizen;

import court.jurists.Lawyer;

import java.util.HashSet;

public class Defendant extends Citizen {
    private HashSet<Lawyer> lawyers;

    public Defendant(String fullName, String address, int age) {
        super(fullName, address, age);
        this.lawyers = new HashSet<>();
    }

    public HashSet<Lawyer> getLawyers() {
        return lawyers;
    }

    public void addLawyer(Lawyer lawyer) {
        if (lawyer == null) {
            throw new NullPointerException("Enter a valid lawyer.");
        }
        this.lawyers.add(lawyer);
    }
}
