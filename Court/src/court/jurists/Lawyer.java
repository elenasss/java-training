package court.jurists;

public class Lawyer extends LegalPerson {

    public Lawyer(String name, int experience, int setNumberOfCases) {
        super(name, Occupation.LAWYER, experience, setNumberOfCases);
    }

    public void setNumberOfCases(int numberOfCases) {
        if (numberOfCases < 10) {
            throw new IllegalArgumentException(
                    "Number of cases must be a positive number greater than 9.");
        }
        super.numberOfCases = numberOfCases;
    }
}
