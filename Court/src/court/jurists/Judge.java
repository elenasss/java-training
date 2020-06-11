package court.jurists;

public class Judge extends LegalPerson {

    public Judge(String name, int experience, int numberOfCases) {
        super(name, Occupation.JUDGE, experience, numberOfCases);
    }

        public void setExperience(int experience) {
            if (experience < 5) {
                throw new IllegalArgumentException(
                        "Years in business must be at least 5!");
            }
            this.experience = experience;
        }
    }
