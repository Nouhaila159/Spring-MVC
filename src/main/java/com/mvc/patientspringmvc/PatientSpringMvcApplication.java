package com.mvc.patientspringmvc;

import com.mvc.patientspringmvc.entities.Patient;
import com.mvc.patientspringmvc.repositories.PatientRepository;
import com.mvc.patientspringmvc.security.service.AccountService;
import jakarta.validation.constraints.Null;
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
public class PatientSpringMvcApplication {

    public static void main(String[] args) {

        SpringApplication.run(PatientSpringMvcApplication.class, args);
    }
    //@Bean
    CommandLineRunner start(PatientRepository patientRepository) {
        return args -> {
            patientRepository.save(new Patient(null, "Nouhaila", new Date(), false, 222));
            patientRepository.save(new Patient(null, "Ohtman",new Date(),false,130));
            patientRepository.save(new Patient(null, "Fatna",new Date(),false,255));
            patientRepository.save(new Patient(null, "Rania",new Date(),false,340));
            patientRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });
        };
    }
    //@Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
        PasswordEncoder passwordEncoder=passwordEncoder();
        return args -> {
            UserDetails u1= jdbcUserDetailsManager.loadUserByUsername("user11");
            UserDetails u2= jdbcUserDetailsManager.loadUserByUsername("user22");
            UserDetails u3= jdbcUserDetailsManager.loadUserByUsername("admin2");
            if (u1== null)
                jdbcUserDetailsManager.createUser(
                        User.withUsername("user11").password(passwordEncoder.encode("1234")).roles("USER").build()
                );
            if (u2== null)
                jdbcUserDetailsManager.createUser(
                        User.withUsername("user22").password(passwordEncoder.encode("1234")).roles("USER").build()
                );
            if (u3== null)
                jdbcUserDetailsManager.createUser(
                        User.withUsername("admin2").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build()
                );
        };
    }

    //@Bean
    CommandLineRunner commandLineRunnerUserDetails(AccountService accountService){
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
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
