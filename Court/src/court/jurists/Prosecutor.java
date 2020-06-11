package court.jurists;

public class Prosecutor extends LegalPerson {

    public Prosecutor(String name, int experience, int numberOfCases) {
        super(name, Occupation.PROSECUTOR, experience, numberOfCases);
    }

    public void setExperience(int experience) {
        if (experience < 10) {
            throw new IllegalArgumentException(
                    "Years in business must be at least 10!");
        }
        this.experience = experience;
    }
}
