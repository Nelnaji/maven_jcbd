package exercice.entities;

public class Student {


    private int id;
    private String firstName;
    private String lastName;
    private String matricule;
    private int sectionId;
    private Section section;
    //region constructor + get set
    public Student(){};

    private Student(int id, String firstname, String lastname, String matricule) {
        this.id = id;
        this.firstName = firstname;
        this.lastName = lastname;
        this.matricule = matricule;
    }

    public Student(int id, String firstname, String lastname, String matricule, int sectionId){
        this(id, firstname, lastname, matricule);
        this.sectionId = sectionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

//    endregion


    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", matricule='" + matricule + '\'' +
                ", sectionId=" + sectionId +
                ", section=" + section +
                '}';
    }

}
