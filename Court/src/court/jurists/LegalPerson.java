package court.jurists;

import court.Demo;
import court.QandA;
import court.citizen.Citizen;

import java.io.PrintWriter;

public abstract class LegalPerson {

    public enum Occupation {
        LAWYER,
        JUDGE,
        PROSECUTOR,
        JURY;
    }

    private String name;
    private Occupation occupation;
    protected int experience;
    protected int numberOfCases;

    public LegalPerson(String name, Occupation occupation, int experience, int numberOfCases) {
        this.occupation = occupation;
        this.setName(name);
        this.setExperience(experience);
        this.setNumberOfCases(numberOfCases);
    }

    public String getName() {
        return name;
    }

    public int getNumberOfCases() {
        return numberOfCases;
    }

    public void setName(String name) {
        if (name.length() < 2) {
            throw new IllegalArgumentException(
                    "Legal person's name must be at least 2 symbols.");
        }
        this.name = name;
    }

    public void setExperience(int experience) {
        if (experience < 0) {
            throw new IllegalArgumentException(
                    "Years in business must be a positive number");
        }
        this.experience = experience;
    }

    public void setNumberOfCases(int numberOfCases) {
        if (numberOfCases < 0) {
            throw new IllegalArgumentException(
                    "Number of cases must be a positive number");
        }
        this.numberOfCases = numberOfCases;
    }

    public void increaseCaseCount() {
        this.numberOfCases++;
    }

    public void askQuestion(PrintWriter writer, LegalPerson legalPerson) {
        String question = QandA.questions[Demo.randomNumber(0, 8)];
        writer.println("\t" + legalPerson.getName() + " asks:");
        writer.println(question);
    }

    public void getAnswer(PrintWriter writer, Citizen citizen) {
        String answer = QandA.answers[Demo.randomNumber(0, 6)];
        writer.println("\t" + citizen.getFullName() + " answers: ");
        writer.println(answer);
    }

    @Override
    public String toString() {
        return String.format(
                "Name: %s; Occupation: %s, Experience: %d; Number of cases: %d; ",
                this.name, this.occupation, this.experience, this.numberOfCases);
    }

}
