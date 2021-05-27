package gilbert.jwork_android;

public class Job {

    private int id;
    private String name;
    private int fee;
    private String category;
    private Recruiter recruiter;

    public Job(int id, String name, int fee, String category, Recruiter recruiter){
        this.id = id;
        this.name = name;
        this.fee = fee;
        this.category = category;
        this.recruiter = recruiter;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFee() {
        return fee;
    }

    public String getCategory() {
        return category;
    }

    public Recruiter getRecruiter() {
        return recruiter;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRecruiter(Recruiter recruiter) {
        this.recruiter = recruiter;
    }

    @Override
    public String toString()
    {
        return "= Job ===============================" +
                "\nId     : " + id +
                "\nName      : " + name +
                "\nFee          : " + fee +
                "\nCategory   : " + category +
                "\nRecruiter      : " + recruiter +
                "\n==========================================";
    }
}