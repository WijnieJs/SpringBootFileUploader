package com.example.novidemoimg.service;
import com.example.novidemoimg.exception.RecordNotFoundException;
import com.example.novidemoimg.model.FileUploadResponse;
import com.example.novidemoimg.model.Product;
import com.example.novidemoimg.repository.FileUploadRepository;
import com.example.novidemoimg.repository.ProductRepository;
import com.example.novidemoimg.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService {
    private final ProductRepository repository;

    private final FileUploadRepository uploadRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, FileUploadRepository uploadRepository) {
        this.repository = productRepository;
        this.uploadRepository = uploadRepository;
    }

    public List<Product> getProducts() {

        return repository.findAll();

    }


    public Product getProduct(Long studentNumber) {

        Optional<Product> student = repository.findById(studentNumber);

        if (student.isPresent()) {

            return student.get();

        } else {

            throw new RecordNotFoundException("Student does not exist");

        }

    }

    public void assignPhotoToProduct(String name, Long studentNumber) {
        Optional<Product> optProduct = repository.findById(studentNumber);

        Optional<FileUploadResponse> fileUploadResponse = uploadRepository.findByFileName(name);

        if(optProduct.isPresent() && fileUploadResponse.isPresent()) {

            FileUploadResponse photo = fileUploadResponse.get();
            Product product = optProduct.get();
            product.setFile(photo);
            repository.save(product);
        }
    }

    public void deleteProduct(Long studentNumber) {
        repository.deleteById(studentNumber);
    }

    public Product updateProduct(Long studentNumber, Product p) {
        Optional<Product> optionalProduct = repository.findById(studentNumber);
        Product prod = optionalProduct.get();

        if(p.getDescription() != null) {
            prod.setDescription(p.getDescription());
        }
        if(p.getStudentNumber() != null) {
            prod.setStudentNumber(p.getStudentNumber());
        }
        if(p.getTitle() != null) {
            prod.setTitle(p.getTitle());
        }
       if(p.getFile() != null) {
           prod.setFile(p.getFile());
       }
       return repository.save(prod);
    }

    public Product saveProduct(Product product) {
        return repository.save(product);

    }
}
