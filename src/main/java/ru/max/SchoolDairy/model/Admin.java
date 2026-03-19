package ru.max.SchoolDairy.model;

public class Admin extends User{
    // + все поля user
    private String department;
    private String accessLevel;

    public Admin(){
        super();
    }

    public Admin(Long id, String fullName, String login, String password, String department, String accessLevel) {
        super(id, fullName, login, password);
        this.department = department;
        this.accessLevel = accessLevel;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }
}
