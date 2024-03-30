package fr.epita.assistants.mycompany;

public class Engineer extends Employee implements Assignable{

    private String project;

    public Engineer(String firstName, String lastName) {
        super(firstName, lastName);
        this.project = null;
    }

    @Override
    public void sayMyJob() {
        System.out.println(this + " is an engineer.");
    }

    @Override
    public void addProject(String project) {
        this.project = project;
    }

    @Override
    public boolean hasProject(String project) {
        return this.project != null && this.project.equals(project);
    }

    @Override
    public void listProjects() {
        if (project != null) {
            System.out.println(this + "'s current project is " + project + ".");
        }
    }
}
