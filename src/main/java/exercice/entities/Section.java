package exercice.entities;

import java.time.LocalDate;

public class Section {

    private int id;
    private String sectionName;
    private String sectionCode;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    public Section(){};

    public Section(int id, String sectionName, String sectionCode, String description, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.sectionName = sectionName;
        this.sectionCode = sectionCode;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", sectionName='" + sectionName + '\'' +
                ", sectionCode='" + sectionCode + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
