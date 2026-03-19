package ru.max.SchoolDairy.model;

public class Schedule {
    private Long id;
    private Integer dayOfWeek;
    private Integer lessonNumber;
    private String classroom;
    private String weekType;

    private SchoolClass schoolClass;
    private Subject subject;
    private Teacher teacher;

    public Schedule() {}

    public Schedule(Long id, Integer dayOfWeek, Integer lessonNumber,
                    String classroom, String weekType) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.lessonNumber = lessonNumber;
        this.classroom = classroom;
        this.weekType = weekType;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(Integer dayOfWeek) { this.dayOfWeek = dayOfWeek; }

    public Integer getLessonNumber() { return lessonNumber; }
    public void setLessonNumber(Integer lessonNumber) { this.lessonNumber = lessonNumber; }

    public String getClassroom() { return classroom; }
    public void setClassroom(String classroom) { this.classroom = classroom; }

    public String getWeekType() { return weekType; }
    public void setWeekType(String weekType) { this.weekType = weekType; }

    public SchoolClass getSchoolClass() { return schoolClass; }
    public void setSchoolClass(SchoolClass schoolClass) { this.schoolClass = schoolClass; }

    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }

    public Teacher getTeacher() { return teacher; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }
}
