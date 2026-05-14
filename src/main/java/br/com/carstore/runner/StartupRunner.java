package br.com.carstore.runner;

import br.com.carstore.dao.CarDao;
import br.com.carstore.model.RoleEntity;
import br.com.carstore.dto.CarDTO;
import br.com.carstore.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.carstore.model.UserEntity;
import br.com.carstore.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Set;

@Component
public class StartupRunner implements CommandLineRunner {

    private final CarDao carDao;

    public StartupRunner(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    public void run(String... args) throws Exception {
        carDao.save(new CarDTO("Onix", "Vermelho", "Chevrolet", "LTZ", "2020", "2021"));
        carDao.save(new CarDTO("Celta", "Cinza", "Chevrolet", "LT", "2008", "2009"));
        System.out.println(carDao.findAll());
    }

    @Bean
    public CommandLineRunner init(RoleRepository roleRepo, UserRepository userRepo, PasswordEncoder encoder) {
        return args -> {
            if (roleRepo.findByName("ROLE_ADMIN").isEmpty()) {
                roleRepo.save(new RoleEntity(null, "ROLE_ADMIN"));
            }
            if (roleRepo.findByName("ROLE_USER").isEmpty()) {
                roleRepo.save(new RoleEntity(null, "ROLE_USER"));
            }

            if (userRepo.findByUsername("admin").isEmpty()) {
                RoleEntity adminRole = roleRepo.findByName("ROLE_ADMIN").orElseThrow();
                RoleEntity userRole = roleRepo.findByName("ROLE_USER").orElseThrow();

                UserEntity admin = new UserEntity();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin"));
                admin.setRoles(Set.of(adminRole, userRole));

                userRepo.save(admin);
            }

            if (userRepo.findByUsername("user").isEmpty()) {
                RoleEntity userRole = roleRepo.findByName("ROLE_USER").orElseThrow();

                UserEntity user = new UserEntity();
                user.setUsername("user");
                user.setPassword(encoder.encode("user"));
                user.setRoles(Set.of(userRole));

                userRepo.save(user);
            }
        };
    }

}