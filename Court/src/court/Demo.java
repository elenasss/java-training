package court;

import com.google.gson.Gson;
import court.cases.CivilCase;
import court.cases.CriminalCase;
import court.citizen.Accuser;
import court.citizen.Defendant;
import court.citizen.Witness;
import court.jurists.Judge;
import court.jurists.Jury;
import court.jurists.Lawyer;
import court.jurists.Prosecutor;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Demo {

    public static void main(String[] args) {
        Court court = new Court("Raionen Syd Veliko Tyrnovo","blv. Bulgaria");

        Judge judge1 = new Judge("Ivan Ivanov", 6, 12);
        Judge judge2 = new Judge("Georgi Georgiev", 8, 10);
        Judge judge3 = new Judge("Petyr Petrov", 9, 16);
        ArrayList<Judge> judges = new ArrayList<>();
        judges.add(judge1);
        judges.add(judge2);
        judges.add(judge3);

        Jury jury1 = new Jury("Petyr Ivanov", 12, 5);
        Jury jury2 = new Jury("Petyr Simeonov", 22, 12);
        Jury jury3 = new Jury("Petyr Atanasov", 10, 10);
        Jury jury4 = new Jury("Petyr Tanev", 15, 20);
        Jury jury5 = new Jury("Petyr Tsanev", 9, 16);
        Jury jury6 = new Jury("Petyr Zanev", 12, 23);
        Jury jury7 = new Jury("Petyr Nanev", 15, 25);
        Jury jury8 = new Jury("Petyr Petkov", 11, 23);
        Jury jury9 = new Jury("Petyr Petrov", 18, 32);
        Jury jury10 = new Jury("Petyr Danov", 20, 40);
        ArrayList<Jury> jury = new ArrayList<>();
        jury.add(jury1);
        jury.add(jury2);
        jury.add(jury3);
        jury.add(jury4);
        jury.add(jury5);
        jury.add(jury6);
        jury.add(jury7);
        jury.add(jury8);
        jury.add(jury9);
        jury.add(jury10);

        Lawyer lawyer1 = new Lawyer("Ivan Tanev", 13, 23);
        Lawyer lawyer2 = new Lawyer("Ivan Tsanev", 13, 23);
        Lawyer lawyer3 = new Lawyer("Ivan Penev", 13, 23);
        Lawyer lawyer4 = new Lawyer("Ivan Draganov", 13, 23);
        Lawyer lawyer5 = new Lawyer("Ivan Tasov", 13, 23);
        ArrayList<Lawyer> lawyers = new ArrayList<>();
        lawyers.add(lawyer1);
        lawyers.add(lawyer2);
        lawyers.add(lawyer3);
        lawyers.add(lawyer4);
        lawyers.add(lawyer5);

        Prosecutor prosecutor1 = new Prosecutor("Dragan Draganov", 20, 42);
        Prosecutor prosecutor2 = new Prosecutor("Dragan Tanev", 22, 46);
        ArrayList<Prosecutor> prosecutors = new ArrayList<>();
        prosecutors.add(prosecutor1);
        prosecutors.add(prosecutor2);

        Accuser accuser1 = new Accuser("Georgi Tinev", "Gabrovo", 24);
        Accuser accuser2 = new Accuser("Georgi Tonev", "Plovdiv", 34);
        Accuser accuser3 = new Accuser("Georgi Tanev", "Sofia", 29);
        Accuser accuser4 = new Accuser("Georgi Tenev", "Ruse", 31);
        Accuser accuser5 = new Accuser("Georgi Tynev", "Sofia", 42);
        ArrayList<Accuser> accusers = new ArrayList<>();
        accusers.add(accuser1);
        accusers.add(accuser2);
        accusers.add(accuser3);
        accusers.add(accuser4);
        accusers.add(accuser5);

        Defendant defendant1 = new Defendant("Nikolai Tinev", "Burgas", 32);
        Defendant defendant2 = new Defendant("Nikolai Tanev", "Sofia", 42);
        Defendant defendant3 = new Defendant("Nikolai Tonev", "Sliven", 22);
        Defendant defendant4 = new Defendant("Nikolai Tenev", "Varna", 42);
        Defendant defendant5 = new Defendant("Nikolai Tynev", "Pernik", 38);
        ArrayList<Defendant> defendants = new ArrayList<>();
        defendants.add(defendant1);
        defendants.add(defendant2);
        defendants.add(defendant3);
        defendants.add(defendant4);
        defendants.add(defendant5);

        Witness witness1 = new Witness("Svilev Ivanov", "Sofia", 25);
        Witness witness2 = new Witness("Pesho Ivanov", "Sliven", 25);
        Witness witness3 = new Witness("Vanko Ivanov", "Ruse", 25);
        Witness witness4 = new Witness("Tsanko Ivanov", "Sofia", 25);
        Witness witness5 = new Witness("Irina Georgieva", "Ruse", 25);
        Witness witness6 = new Witness("Irena Ivanova", "Sliven", 25);
        Witness witness7 = new Witness("Ivena Georgieva", "Sofia", 25);
        Witness witness8 = new Witness("Milena Petrova", "Plovdiv", 25);
        Witness witness9 = new Witness("Minka Minkova", "Plovdiv", 25);
        Witness witness10 = new Witness("Zara Zareva", "Ruse", 25);
        ArrayList<Witness> witnesses = new ArrayList<>();
        witnesses.add(witness1);
        witnesses.add(witness2);
        witnesses.add(witness3);
        witnesses.add(witness4);
        witnesses.add(witness5);
        witnesses.add(witness6);
        witnesses.add(witness7);
        witnesses.add(witness8);
        witnesses.add(witness9);
        witnesses.add(witness10);

        court.addLegalPersons(judges);
        court.addLegalPersons(jury);
        court.addLegalPersons(lawyers);
        court.addLegalPersons(prosecutors);

        CivilCase civilCase1 = createCivilCase(defendants, lawyers, witnesses, accusers);
        CivilCase civilCase2 = createCivilCase(defendants, lawyers, witnesses, accusers);
        CivilCase civilCase3 = createCivilCase(defendants, lawyers, witnesses, accusers);

        CriminalCase criminalCase1 = createCriminalCase(defendants, lawyers, witnesses, prosecutors);
        CriminalCase criminalCase2 = createCriminalCase(defendants, lawyers, witnesses, prosecutors);
        CriminalCase criminalCase3 = createCriminalCase(defendants, lawyers, witnesses, prosecutors);

        court.createCase(civilCase1);
        court.createCase(civilCase2);
        court.createCase(civilCase3);
        court.createCase(criminalCase1);
        court.createCase(criminalCase2);
        court.createCase(criminalCase3);

        court.showLegalPersonDetails();

        civilCase1.proceed();
        civilCase2.proceed();
        civilCase3.proceed();
        criminalCase1.proceed();
        criminalCase3.proceed();
        criminalCase2.proceed();

        court.showLegalPersonDetails();

        Gson gson = new Gson();
        String json = gson.toJson(court);
        try (PrintWriter writer = new PrintWriter("court.json")) {
            writer.println(json);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static CriminalCase createCriminalCase(List<Defendant> defendants, List<Lawyer> lawyers,
                                                   List<Witness> witnesses, List<Prosecutor> prosecutors) {
        Defendant defendant = defendants.get(randomNumber(0, 4));
        defendant.addLawyer(lawyers.get(randomNumber(0, 4)));
        defendant.addLawyer(lawyers.get(randomNumber(0, 4)));
        Prosecutor prosecutor = prosecutors.get(randomNumber(0, 1));
        HashSet<Witness> wits = new HashSet<>();
        wits.add(witnesses.get(randomNumber(0, 9)));
        wits.add(witnesses.get(randomNumber(0, 9)));
        wits.add(witnesses.get(randomNumber(0, 9)));
        wits.add(witnesses.get(randomNumber(0, 9)));
        wits.add(witnesses.get(randomNumber(0, 9)));

        return new CriminalCase(defendant, wits, prosecutor);
    }

    private static CivilCase createCivilCase(List<Defendant> defendants, List<Lawyer> lawyers,
                                             List<Witness> witnesses, List<Accuser> accusers) {
        Defendant defendant = defendants.get(randomNumber(0, 4));
        defendant.addLawyer(lawyers.get(randomNumber(0, 4)));
        defendant.addLawyer(lawyers.get(randomNumber(0, 4)));
        Accuser accuser = accusers.get(randomNumber(0, 4));
        accuser.addLawyer(lawyers.get(randomNumber(0, 4)));
        accuser.addLawyer(lawyers.get(randomNumber(0, 4)));
        HashSet<Witness> wits = new HashSet<>();
        wits.add(witnesses.get(randomNumber(0, 9)));
        wits.add(witnesses.get(randomNumber(0, 9)));
        wits.add(witnesses.get(randomNumber(0, 9)));

        return new CivilCase(defendant, wits, accuser);
    }

    public static int randomNumber(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }
}
