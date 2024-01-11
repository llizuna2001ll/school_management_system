package com.digital_zone.userservice.services;

import com.digital_zone.userservice.dtos.AddEmailRequest;
import com.digital_zone.userservice.dtos.UserRequest;
import com.digital_zone.userservice.dtos.UserResponse;
import com.digital_zone.userservice.entities.User;
import com.digital_zone.userservice.enums.Roles;
import com.digital_zone.userservice.exceptions.UserNotFoundException;
import com.digital_zone.userservice.repositories.UserRepository;
import com.digital_zone.userservice.restclient.GradeServiceRestClient;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import net.bytebuddy.utility.RandomString;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final JavaMailSender mailSender;
    private final PasswordEncoder passwordEncoder;
    private final GradeServiceRestClient gradeServiceRestClient;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public UserServiceImpl(UserRepository userRepository, JavaMailSender mailSender, PasswordEncoder passwordEncoder, GradeServiceRestClient gradeServiceRestClient) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
        this.passwordEncoder = passwordEncoder;
        this.gradeServiceRestClient = gradeServiceRestClient;
    }


    @Override
    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();

        List<UserResponse> userResponses = new ArrayList<>();

        for (User user : users){
            userResponses.add(UserResponse.toUserResponse(user));
        }

        return userResponses;
    }

    @Override
    public UserResponse getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return UserResponse.toUserResponse(user);
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        User user = userRepository.save(User.toUser(userRequest));
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(Roles.ROLE_STUDENT);
        String registrationDate = LocalDateTime.now().format(formatter);
        String noDashesRegistration = registrationDate.replace("-","");
        user.setRegistrationDate(registrationDate);
        user.setGrade("N/A");
        user.setUsername("etd-" + user.getId() + "-" +noDashesRegistration);
        user.setCreationTime(LocalDateTime.now());
        user.setVerificationCode(RandomString.make(64));
        return UserResponse.toUserResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserResponse addEmail(AddEmailRequest emailRequest, String siteURL) throws MessagingException, UnsupportedEncodingException {
        User user = userRepository.findByUsername(emailRequest.username());
        user.setEmail(emailRequest.email());
        sendVerificationEmail(user, siteURL);
        return UserResponse.toUserResponse(userRepository.save(user));
    }


    private void sendVerificationEmail(User user, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "heptagrammers@gmail.com";
        String senderName = "Digital_Zone_Mundiapolis";
        String subject = "Merci de vérifier votre email";
        String content = "Mr. " + user.getLastName() + ",<br>"
                + "<p style='font-size: 16px; color: #333;'>Veuillez cliquer sur le lien ci-dessous pour vérifier votre email</p>"
                + "<p style='margin-top: 20px;'><a href=\"[[URL]]\" target=\"_self\" style='display: inline-block; padding: 10px 20px; background-color: #008CBA; color: white; text-decoration: none; border-radius: 5px;'>VERIFY</a></p>"
                + "<p style='color: #777;'>Merci,<br>"
                + "Votre Ecole</p>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getUsername());
        String verifyURL = siteURL + "/api/v1/users/verify?code=" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);
    }

    @Override
    public boolean verifyEmail(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);

        if (user == null)
            return false;
        else if (user.isEnabled())
            return true;
        else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepository.save(user);
            return true;
        }
    }

    @Override
    public List<UserResponse> getUsersByGrade(String grade) {
        List<User> users = userRepository.findByGrade(grade);

        List<UserResponse> userResponses = new ArrayList<>();

        for (User user : users){
            userResponses.add(UserResponse.toUserResponse(user));
        }

        return userResponses;
    }

    @Override
    public UserResponse assignGrade(String grade, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        user.setGrade(grade);
        userRepository.save(user);
        gradeServiceRestClient.incrementStudentCount(grade);
        return UserResponse.toUserResponse(user);
    }

    @Override
    public UserResponse unassignGrade(String grade, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        user.setGrade(grade);
        userRepository.save(user);
        gradeServiceRestClient.decrementStudentCount(grade);
        return UserResponse.toUserResponse(user);
    }

}
