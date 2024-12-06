package com.cursework.kuroi.services;

import com.cursework.kuroi.models.Image;
import com.cursework.kuroi.models.User;
import com.cursework.kuroi.models.enums.Role;
import com.cursework.kuroi.repositories.ImageRepository;
import com.cursework.kuroi.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean addUser(User user) {
        String email = user.getUserEmail();

        if (userRepository.findByUserEmail(email) != null) return false;

        // Натсройка пользовотеля по умолчанию
        user.setActive(true);
        user.getRoles().add(Role.ROLE_ADMIN);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return true;
    }

    public List<User> getUserByKeyWord(String keyword) {
        if (keyword != null) return userRepository.findByKeyword(keyword);
        return userRepository.findAll();
    }

    public void changeUserBanStatus(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (user.isActive()) {
                user.setActive(false);
                log.info("User banned with id: {}", id);
            } else {
                user.setActive(true);
                log.info("User unbanned with id: {}", id);
            }
            userRepository.save(user);
        }
    }

    public void changeUserRoles(User user, Map<String, String> form) {
        Set<String> roles = Arrays.stream(Role.values()).map(Enum::name).collect(Collectors.toSet());
        user.getRoles().clear();
        for (String role : form.keySet()) {
            if (roles.contains(role)) {
                user.getRoles().add(Role.valueOf(role));
            }
        }
        userRepository.save(user);
    }

    public User getUserByPrinciple(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByUserEmail(principal.getName());
    }

    @Transactional
    public boolean changeUserInformation(Principal principal, String userBIO, String userNickName, MultipartFile file) throws IOException {
        User userFromDB =  userRepository.findByUserEmail(getUserByPrinciple(principal).getUserEmail());

        // TODO: Сделать удаление фотографии если у пользователя она уже есть
        // Изменение фото, если есть
        if (file.getSize() != 0) {
            Image image = new Image();
            if (userFromDB.getImage() != null)
                image = imageRepository.findById(userFromDB.getImage().getId()).orElse(null);

            image.setPath("img" + image.getId());
            image.setContentType(file.getContentType());
            image.setSize(file.getSize());
            image.setBytes(file.getBytes());
            image.setUser(userFromDB);

            userFromDB.setImage(image);
        }

        if (userRepository.findByUserNickName(userNickName) == null || Objects.equals(userFromDB.getUserNickName(), userNickName))
            userFromDB.setUserNickName(userNickName);
        else
            return false;

        // Изменение имени
        userFromDB.setUserBIO(userBIO);

        return true;
    }
}
