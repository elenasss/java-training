package court.jurists;

public class Jury extends LegalPerson {

    public Jury(String name, int experience, int numberOfCases) {
        super(name, Occupation.JURY, experience, numberOfCases);
    }

}
