package com.example.spring_mvc_thylemeaf_hospital;

import com.example.spring_mvc_thylemeaf_hospital.entities.Patient;
import com.example.spring_mvc_thylemeaf_hospital.repositories.PatientRepository;
import com.example.spring_mvc_thylemeaf_hospital.security.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

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
        patientRepository.save(new Patient(null, "Adam", new Date(), false, 122));
        patientRepository.save(new Patient(null, "ilyas", new Date(), true, 321));
        patientRepository.save(new Patient(null, "oumama", new Date(), false, 165));
        patientRepository.save(new Patient(null, "othmane", new Date(), true, 132));


    }
    //@Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager) {
        PasswordEncoder passwordEncoder=passwordEncoder();
        return args -> {
            UserDetails u1=jdbcUserDetailsManager.loadUserByUsername("user11");
        if(u1==null)
            jdbcUserDetailsManager.createUser(User.withUsername("user11").password(passwordEncoder.encode("1234")).roles("USER").build()
        );
            UserDetails u2=jdbcUserDetailsManager.loadUserByUsername("user22");
            if(u2==null)
            jdbcUserDetailsManager.createUser(User.withUsername("user22").password(passwordEncoder.encode("1234")).roles("USER").build()
            );
            UserDetails u3=jdbcUserDetailsManager.loadUserByUsername("admin2");
            if(u3==null)
            jdbcUserDetailsManager.createUser(User.withUsername("admin2").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build()
            );
        };
    }
//@Bean
CommandLineRunner commandLineRunnerUSerDetails(AccountService accountService){
        return args -> {
            accountService.addNewRole("USER");
            accountService.addNewRole("ADMIN");
            accountService.addNewUser("user1","1234","user1@gmail.com","1234");
            accountService.addNewUser("user2","1234","user2@gmail.com","1234");
            accountService.addNewUser("admin","1234","admin@gmail.com","1234");


            accountService.addRoleToUser("user1","USER");
            accountService.addRoleToUser("user2","USER");
            accountService.addRoleToUser("admin","USER");
            accountService.addRoleToUser("admin","ADMIN");


        };
}
@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

