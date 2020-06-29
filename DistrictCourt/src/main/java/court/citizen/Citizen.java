package court.citizen;

public abstract class Citizen {
    private String fullName;
    private String address;
    private int age;

    public Citizen(String fullName, String address, int age) {
        super();
        this.setFullName(fullName);
        this.setAddress(address);
        this.setAge(age);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if (fullName.length() < 8) {
            throw new IllegalArgumentException(
                    "Full name must be at least 8 symbols.");
        }

        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address.length() < 2) {
            throw new IllegalArgumentException(
                    "Court name must be at least 2 symbols.");
        }

        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException(
                    "Citizen age must be at least 18 years old");
        }

        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("full name: %s; address: %s; age: %d",
                this.fullName, this.address, this.age);
    }

}

