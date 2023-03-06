<?php

class JobPosting {
    private $title;
    private $description;
    private $company;
    private $location;
    private $requiredSkills;

    public function __construct($title, $description, $company, $location, $requiredSkills) {
        $this->title = $title;
        $this->description = $description;
        $this->company = $company;
        $this->location = $location;
        $this->requiredSkills = $requiredSkills;
    }

    public function getTitle() {
        return $this->title;
    }

    public function getDescription() {
        return $this->description;
    }

    public function getCompany() {
        return $this->company;
    }

    public function getLocation() {
        return $this->location;
    }

    public function getRequiredSkills() {
        return $this->requiredSkills;
    }
}

class JobPostingDatabase {
    private $jobPostings;

    public function __construct() {
        $this->jobPostings = array();
    }

    public function addJobPosting($jobPosting) {
        array_push($this->jobPostings, $jobPosting);
    }

    public function getJobPostings() {
        return $this->jobPostings;
    }
}

class JobPostingSearchEngine {
    public function searchJobPostings($jobPostingDatabase, $keyword, $location) {
        $jobPostings = $jobPostingDatabase->getJobPostings();
        $matchingJobPostings = array();

        foreach ($jobPostings as $jobPosting) {
            if (stripos($jobPosting->getTitle(), $keyword) !== false
                    || stripos($jobPosting->getDescription(), $keyword) !== false
                    || stripos($jobPosting->getCompany(), $keyword) !== false) {
                if (strcasecmp($jobPosting->getLocation(), $location) == 0) {
                    array_push($matchingJobPostings, $jobPosting);
                }
            }
        }

        return $matchingJobPostings;
    }
}

// Criando uma base de dados de postagens de emprego
$jobPostingDatabase = new JobPostingDatabase();

// Adicionando postagens de emprego à base de dados
$requiredSkills1 = array("Java", "Spring Framework");
$jobPosting1 = new JobPosting("Desenvolvedor Java", "Descrição da vaga para desenvolvedor Java", "Empresa A", "São Paulo", $requiredSkills1);
$jobPostingDatabase->addJobPosting($jobPosting1);

$requiredSkills2 = array("JavaScript", "React");
$jobPosting2 = new JobPosting("Desenvolvedor Front-end", "Descrição da vaga para desenvolvedor Front-end", "Empresa B", "Rio de Janeiro", $requiredSkills2);
$jobPostingDatabase->addJobPosting($jobPosting2);

// Pesquisando por postagens de emprego que correspondem a uma palavra-chave e localização
$jobPostingSearchEngine = new JobPostingSearchEngine();
$matchingJobPostings = $jobPostingSearchEngine->searchJobPostings($jobPostingDatabase, "Java", "São Paulo");

// Exibindo as postagens de emprego correspondentes
foreach ($matchingJobPostings as $jobPosting) {
    echo $jobPosting->getTitle() . " - " . $jobPosting->getDescription() . "<br>";
}
