package fr.epita.assistants.exceptions;

public class Student {
    protected String name;
    protected int age;
    protected String major;

    public Student(String name, int age, String major) throws InvalidAgeException, InvalidMajorException, InvalidNameException {
        if (!name.matches("^[^012346579]*$"))
            throw new InvalidNameException(name);
        this.name = name;

        if (age >= 130 || age <= 0)
            throw new InvalidAgeException(age);
        this.age = age;

        String tmp = major.toUpperCase();
        if (!tmp.equals("IMAGE") &&
                !tmp.equals("GISTRE") &&
                !tmp.equals("SRS") &&
                !tmp.equals("SCIA") &&
                !tmp.equals("MTI") &&
                !tmp.equals("TCOM") &&
                !tmp.equals("SIGL") &&
                !tmp.equals("GITM") &&
                !tmp.equals("ICE") &&
                !tmp.equals("SANTE") &&
                !tmp.equals("SSIE") &&
                !tmp.equals("IF") &&
                !tmp.equals("STARTUP") &&
                !tmp.equals("Q"))
            throw new InvalidMajorException(major);

        this.major = tmp;
    }

    @Override
    public String toString() {
        return "Name: " + name +", Age: " + age + ", Major: " + major;
    }
}
