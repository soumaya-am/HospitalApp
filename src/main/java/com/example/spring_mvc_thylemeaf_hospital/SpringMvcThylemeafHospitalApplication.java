package com.example.spring_mvc_thylemeaf_hospital;

import com.example.spring_mvc_thylemeaf_hospital.entities.Patient;
import com.example.spring_mvc_thylemeaf_hospital.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class SpringMvcThylemeafHospitalApplication implements CommandLineRunner {
    @Autowired
  private PatientRepository patientRepository;
    public static void main(String[] args) {

        SpringApplication.run(SpringMvcThylemeafHospitalApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        /*//en utilisant constructeur sans paramètre
        Patient patient=new Patient();
        patient.setId(null);
        patient.setNom("Mohammed");
        patient.setDateNaissance(new Date());
        patient.setMalade(false);
        patient.setScore(23);
        //Constructeur avec paramètre
        Patient patient1=new Patient(null,"soumaya",new Date(),false,123);
        //en utilisant Builder
        Patient patient2=Patient.builder()
                .nom("imane")
                .dateNaissance(new Date())
                .score(65)
                .malade(true)
                .build();*/
        patientRepository.save(new Patient(null,"Mohammed",new Date(),false,25));
        patientRepository.save(new Patient(null,"ilyas",new Date(),true,2));
        patientRepository.save(new Patient(null,"oumama",new Date(),false,12));
        patientRepository.save(new Patient(null,"othmane",new Date(),true,10));


    }
}
