package com.cursework.kuroi.services;

import com.cursework.kuroi.models.Image;
import com.cursework.kuroi.models.Product;
import com.cursework.kuroi.models.User;
import com.cursework.kuroi.repositories.ProductRepository;
import com.cursework.kuroi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<Product> listProducts(String title) {
        if (title != null) return productRepository.findByTitle(title);
        return productRepository.findAll();
    }

    public void saveProducts(Principal principal, Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        product.setUser(getUserByPrincipal(principal));
        Image image1, image2, image3;

        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            product.addImageToProduct(image1);
        }

        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            product.addImageToProduct(image2);
        }

        if (file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            product.addImageToProduct(image3);
        }
        
        log.info("Saving new product. Title: {};", product.getTitle());

        Product productFromDB = productRepository.save(product);
        productFromDB.setPreviewImageId(productFromDB.getImages().getFirst().getId());
        productRepository.save(product);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    private Image toImageEntity(MultipartFile file1) throws IOException {
        Image image = new Image();
        image.setName(file1.getName());
        image.setPath(file1.getOriginalFilename());
        image.setContentType(file1.getContentType());
        image.setSize(file1.getSize());
        image.setContent(file1.getBytes());
        return image;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product takeProductsById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
