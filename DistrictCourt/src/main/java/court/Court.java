package court;

import court.cases.Case;
import court.jurists.Judge;
import court.jurists.Jury;
import court.jurists.LegalPerson;

import java.util.*;

public class Court<P extends LegalPerson, C extends Case> {

    private String name;
    private String address;
    private List<P> legalPersons;
    private TreeSet<P>lp;
    private HashSet<C> cases;

    public Court(String name, String address) {
        this.name = name;
        this.address = address;
        this.legalPersons = new ArrayList<>();
        this.cases = new HashSet<>();
        this.lp = new TreeSet<>(Comparator.comparing(LegalPerson::getName));
    }

    public void addLegalPersons(List<P> legalPersons) {
        if (legalPersons == null) {
            throw new NullPointerException("Enter valid list of legal persons");
        }
        this.legalPersons.addAll(legalPersons);
        this.lp.addAll(legalPersons);
    }

    public void createCase(C baseCase) {
        if (baseCase == null) {
            throw new NullPointerException("Enter valid valid case");
        }
        while (true) {
            LegalPerson lp = this.legalPersons.get(Demo.randomNumber(0, 19));
            if (lp instanceof Judge) {
                baseCase.setJudge((Judge) lp);
                break;
            }
        }

        int juryCount = baseCase.getType() == Case.Type.CIVIL_CASE ? 3 : 10;
        int currentCount = 0;
        while (currentCount <= juryCount) {
            for (LegalPerson lp : this.legalPersons) {
                if (lp instanceof Jury) {
                    baseCase.addJury((Jury) lp);
                    currentCount++;
                }
            }
        }
        this.cases.add(baseCase);
    }

    public void showLegalPersonDetails(){
        for (P p : lp) {
            System.out.println(p.getName() + ": " + p.getNumberOfCases());
        }
        System.out.println("===============================");
    }
}
