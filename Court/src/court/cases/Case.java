package court.cases;

import court.Demo;
import court.citizen.Defendant;
import court.citizen.Witness;
import court.jurists.Judge;
import court.jurists.Jury;
import court.jurists.Lawyer;
import court.jurists.Prosecutor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Random;

public abstract class Case {

    public enum Type {
        CIVIL_CASE,
        CRIMINAL_CASE
    }

    private final Type type;
    private Judge judge;
    private final HashSet<Jury> jury;
    private final Defendant defendant;
    private HashSet<Witness> witnesses;
    private File output;
    private static int caseCount = 1;


    public Case(Type type, Defendant defendant, HashSet<Witness> witnesses) {
        this.type = type;
        this.jury = new HashSet<>();
        this.witnesses = new HashSet<>();
        this.defendant = defendant;
        this.witnesses = witnesses;

        try {
            String caseName = "case" + caseCount++;
            output = new File(caseName + ".txt");
            output.createNewFile();
        } catch (IOException e) {
            System.out.println("The file was not created");
            e.printStackTrace();
        }
    }

    public Type getType() {
        return type;
    }

    public void setJudge(Judge judge) {
        this.judge = judge;
    }

    public void addJury(Jury jury) {
        this.jury.add(jury);
    }

    public void proceed() {
        increaseNumberOfCases();
        try (PrintWriter writer = new PrintWriter(output)) {
            askQuestions(writer);
            makeDecision(writer);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private void increaseNumberOfCases() {
        this.judge.increaseCaseCount();

        for (Jury jury : jury) {
            jury.increaseCaseCount();
        }

        if (this.type == Type.CRIMINAL_CASE) {
            Prosecutor prosecutor = ((CriminalCase) this).getProsecutor();
            prosecutor.increaseCaseCount();
        }
        if (this.type == Type.CIVIL_CASE) {
            HashSet<Lawyer> lawyers = ((CivilCase) this).getAccuser().getLawyers();
            for (Lawyer lawyer : lawyers) {
                lawyer.increaseCaseCount();
            }
        }

        HashSet<Lawyer> lawyers = defendant.getLawyers();
        for (Lawyer lawyer : lawyers) {
            lawyer.increaseCaseCount();
        }
    }

    private void askQuestions(PrintWriter writer) {
        if (this.type == Type.CIVIL_CASE) {
            writer.println("CIVIL CASE:");
            writer.println(this);
            proceedCivilCase(writer);
        } else if (this.type == Type.CRIMINAL_CASE) {
            writer.println("CRIMINAL CASE:");
            writer.println(this);
            proceedCriminalCase(writer);
        }

        writer.println("DEFENDANT'S LAWYERS ASK  WITNESSES QUESTIONS");
        HashSet<Lawyer> defendantLawyers = new HashSet<>(defendant.getLawyers());
        for (Lawyer lawyer : defendantLawyers) {
            for (Witness witness : witnesses) {
                for (int i = 0; i < 5; i++) {
                    lawyer.askQuestion(writer, lawyer);
                    lawyer.getAnswer(writer, witness);
                }
            }
            writer.println();
        }
        writer.println("------------------------");
    }

    private void proceedCivilCase(PrintWriter writer) {
        HashSet<Lawyer> lawyers = new HashSet<>(((CivilCase) this).getAccuser().getLawyers());
        writer.println("ACCUSER'S LAWYERS ASK DEFENDANT QUESTIONS");

        for (Lawyer lawyer : lawyers) {
            for (int i = 0; i < 3; i++) {
                lawyer.askQuestion(writer, lawyer);
                lawyer.getAnswer(writer, defendant);
            }
            writer.println();
            writer.println("ACCUSER'S LAWYERS ASK WITNESSES QUESTIONS");
            for (Witness witness : witnesses) {
                for (int i = 0; i < 2; i++) {
                    lawyer.askQuestion(writer, lawyer);
                    lawyer.getAnswer(writer, witness);
                }
            }
        }
        writer.println("------------------------");
    }

    private void proceedCriminalCase(PrintWriter writer) {
        writer.println("PROSECUTOR ASK DEFENDANT QUESTIONS");
        Prosecutor prosecutor = ((CriminalCase) this).getProsecutor();
        for (int i = 0; i < 5; i++) {
            prosecutor.askQuestion(writer, prosecutor);
            prosecutor.getAnswer(writer, defendant);
        }
        writer.println("------------------------");

        writer.println("PROSECUTOR ASK WITNESSES QUESTIONS");
        for (Witness witness : witnesses) {
            for (int i = 0; i < 5; i++) {
                prosecutor.askQuestion(writer, prosecutor);
                prosecutor.getAnswer(writer, witness);
            }
        }
        writer.println("------------------------");
    }

    private void makeDecision(PrintWriter writer) {
        int sentence = 0;
        int count = 0;
        for (Jury jury : jury) {
            if (new Random().nextBoolean()) {
                writer.println("The judicial assessor " + jury.getName() + " finds the defendant not guilty!");
                count++;
            } else {
                writer.println("The judicial assessor " + jury.getName() + " finds the defendant guilty!");
            }
        }

        if (count > this.jury.size() / 2) {
            writer.println("The jury finds the defendant not guilty!");
        } else {
            sentence = Demo.randomNumber(3, 40);
            writer.println("The jury finds the defendant guilty!");
            writer.println("The defendant is sentenced to " + sentence + " years in prison!");
            writer.println("END OF CASE");
        }
    }

    @Override
    public String toString() {
        return String.format("Judge: %s; Defendant: %s; Witnesses: %s",
                this.judge, this.defendant, this.witnesses.toString());
    }
}
