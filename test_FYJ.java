import java.util.ArrayList;
import java.util.List;

public class JobPosting {
    private String title;
    private String description;
    private String company;
    private String location;
    private List<String> requiredSkills;

    public JobPosting(String title, String description, String company, String location, List<String> requiredSkills) {
        this.title = title;
        this.description = description;
        this.company = company;
        this.location = location;
        this.requiredSkills = requiredSkills;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public List<String> getRequiredSkills() {
        return requiredSkills;
    }
}

public class JobPostingDatabase {
    private List<JobPosting> jobPostings;

    public JobPostingDatabase() {
        this.jobPostings = new ArrayList<>();
    }

    public void addJobPosting(JobPosting jobPosting) {
        jobPostings.add(jobPosting);
    }

    public List<JobPosting> getJobPostings() {
        return jobPostings;
    }
}

public class JobPostingSearchEngine {
    public List<JobPosting> searchJobPostings(JobPostingDatabase jobPostingDatabase, String keyword, String location) {
        List<JobPosting> jobPostings = jobPostingDatabase.getJobPostings();
        List<JobPosting> matchingJobPostings = new ArrayList<>();

        for (JobPosting jobPosting : jobPostings) {
            if (jobPosting.getTitle().toLowerCase().contains(keyword.toLowerCase())
                    || jobPosting.getDescription().toLowerCase().contains(keyword.toLowerCase())
                    || jobPosting.getCompany().toLowerCase().contains(keyword.toLowerCase())) {
                if (jobPosting.getLocation().equalsIgnoreCase(location)) {
                    matchingJobPostings.add(jobPosting);
                }
            }
        }

        return matchingJobPostings;
    }
}

public class Main {
    public static void main(String[] args) {
        // Criando uma base de dados de postagens de emprego
        JobPostingDatabase jobPostingDatabase = new JobPostingDatabase();

        // Adicionando postagens de emprego à base de dados
        List<String> requiredSkills1 = new ArrayList<>();
        requiredSkills1.add("Java");
        requiredSkills1.add("Spring Framework");
        JobPosting jobPosting1 = new JobPosting("Desenvolvedor Java", "Descrição da vaga para desenvolvedor Java", "Empresa A", "São Paulo", requiredSkills1);
        jobPostingDatabase.addJobPosting(jobPosting1);

        List<String> requiredSkills2 = new ArrayList<>();
        requiredSkills2.add("JavaScript");
        requiredSkills2.add("React");
        JobPosting jobPosting2 = new JobPosting("Desenvolvedor Front-end", "Descrição da vaga para desenvolvedor Front-end", "Empresa B", "Rio de Janeiro", requiredSkills2);
        jobPostingDatabase.addJobPosting(jobPosting2);

        // Pesquisando por postagens de emprego que correspondem a uma palavra-chave e localização
        JobPostingSearchEngine jobPostingSearchEngine = new JobPostingSearchEngine();
        List<JobPosting> matchingJobPostings = jobPostingSearchEngine.searchJobPostings(jobPostingDatabase, "Java", "São Paulo");

        // Exibindo as postagens de emprego correspondentes
        for (JobPosting jobPosting : matchingJobPostings) {
            System.out.println(jobPosting.getTitle() + " - " + jobPosting.get