package com.esprit.coworkingspaceback.roua.serviceImpl;

import com.esprit.coworkingspaceback.roua.email.EmailSender;
import com.esprit.coworkingspaceback.roua.entities.Role;
import com.esprit.coworkingspaceback.roua.entities.User;
import com.esprit.coworkingspaceback.roua.repo.UserRepo;
import com.esprit.coworkingspaceback.roua.services.UserService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepo userRepo ;
    @Autowired
    private PasswordEncoder passwordEncoder ;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private EmailSender emailSender ;



    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        createAdminAccount();
    }

    public void createAdminAccount(){
        User adminAccount = userRepo.findByRole(Role.ADMIN);
        if(adminAccount==null) {
            adminAccount = new User();
            adminAccount.setFirstName("admin");
            adminAccount.setLastName("admin");
            adminAccount.setEmail("admin@gmail.com");
            adminAccount.setPassword(passwordEncoder.encode("admin"));
            adminAccount.setNumTel(20369845);
            adminAccount.setRole(Role.ADMIN);

            userRepository.save(adminAccount);
        }
        log.info("admin account created successfuly");

    }
    @Override
    public User updateUser(Long id,
                           @RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password,
                           @RequestParam("numTel") int numTel
                           ) throws IOException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setNumTel(numTel);

        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

        userRepository.delete(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("user not found"));
    }


    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }


    @Override
    public List<User> searchByFirstName(String firstName) {
        return userRepository.findByFirstNameContaining(firstName);
    }

    @Override
    public Optional<String> findEmailById(Long id) {
        return userRepository.findEmailById(id);
    }


    @Override
    public Boolean checkUserEnabled(String email) {
        return null;
    }

    @Override
    public String forgotPassword(String email) throws MessagingException {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        emailSender.sendSetPasswordEmail(email);

        return "Please check your email to set a new password ";
    }

    @Override
    public String setPassword(String email, String newPassword) {
        Optional<User> optionalUser = userRepo.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String encryptedPassword =passwordEncoder.encode(newPassword);
            user.setPassword(encryptedPassword);
            userRepo.save(user);

            return "Password updated successfully.";
        } else {
            return "No user found with that email address.";
        }
    }

    @Override
    public List<Object[]> getUsersRegistrationStats() {
        return null;
    }
}
