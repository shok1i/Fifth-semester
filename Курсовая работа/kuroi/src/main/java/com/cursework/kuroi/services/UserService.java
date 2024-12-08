package com.cursework.kuroi.services;

import com.cursework.kuroi.models.*;
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

        if (userRepository.getUser_ByUserEmail(email) != null) return false;

        // Натсройка пользовотеля по умолчанию
        user.setActive(true);
        user.getRoles().add(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        UserCollection userCollection = new UserCollection();
        userCollection.setUser(user);
        user.setUserCollection(userCollection);

        userRepository.save(user);
        return true;
    }

    public List<User> getUserByKeyWord(String keyword) {
        if (keyword != null) return userRepository.getUsers_ByKeyword(keyword);
        return userRepository.findAll();
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void changeUserBanStatus(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setActive(!user.isActive());
            userRepository.save(user);
        }
    }

    public void changeUserRoles(String userEmail, String newRole) {
        Set<String> roles = Arrays.stream(Role.values()).map(Enum::name).collect(Collectors.toSet());
        User user = userRepository.getUser_ByUserEmail(userEmail);

        user.getRoles().clear();

        if (roles.contains(newRole))
            user.getRoles().add(Role.valueOf(newRole));

        userRepository.save(user);
    }

    public User getUserByPrinciple(Principal principal) {
        if (principal == null) return new User();
        return userRepository.getUser_ByUserEmail(principal.getName());
    }

    @Transactional
    public boolean changeUserInformation(Principal principal, String userBIO, String userNickName, MultipartFile userImageFile, MultipartFile userBannerImage) throws IOException {
        User userFromDB = userRepository.getUser_ByUserEmail(getUserByPrinciple(principal).getUserEmail());

        // Изменение фото, если есть
        if (userImageFile.getSize() != 0) {
            Image image = new Image();
            if (userFromDB.getImage() != null)
                image = imageRepository.findById(userFromDB.getImage().getImageID()).orElse(null);

            image.setPath("img" + image.getImageID());
            image.setContentType(userImageFile.getContentType());
            image.setSize(userImageFile.getSize());
            image.setBytes(userImageFile.getBytes());
            image.setUserAvatar(userFromDB);

            userFromDB.setImage(image);
        }

        // Изменение фото, если есть
        if (userBannerImage.getSize() != 0) {
            Image image = new Image();
            if (userFromDB.getBanner() != null)
                image = imageRepository.findById(userFromDB.getBanner().getImageID()).orElse(null);

            image.setPath("img" + image.getImageID());
            image.setContentType(userBannerImage.getContentType());
            image.setSize(userBannerImage.getSize());
            image.setBytes(userBannerImage.getBytes());
            image.setUserBanner(userFromDB);

            userFromDB.setBanner(image);
        }

        if (userRepository.getUser_ByUserNickName(userNickName) == null || Objects.equals(userFromDB.getUserNickName(), userNickName))
            userFromDB.setUserNickName(userNickName);
        else
            return false;

        // Изменение имени
        userFromDB.setUserBIO(userBIO);

        return true;
    }


    public User getUserById(Long userID) {
        return userRepository.getUser_ByUserID(userID);
    }
}
