package com.example.novidemoimg.controller;


import com.example.novidemoimg.model.FileUploadResponse;
import com.example.novidemoimg.model.Product;
import com.example.novidemoimg.service.FileUploadService;
import com.example.novidemoimg.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/students")
public class ProductController {

    private final ProductService service;
    private final ImageUploadController controller;

    @Autowired
    public ProductController(ProductService service, ImageUploadController controller) {
        this.service = service;
        this.controller = controller;
    }
    @GetMapping
    @Transactional
    public List<Product> getStudents() {

        List<Product> products;


        products = service.getProducts();


        return products;

    }
    @GetMapping("/{id}")
    @Transactional
    public Product getStudent(@PathVariable("id") Long studentNumber) {

        return service.getProduct(studentNumber);

    }
    @PostMapping
    public Product saveProduct(@RequestBody Product product) {

        return service.saveProduct(product);

    }
    @PutMapping("/{studentNumber}")
    public Product updateStudent(@PathVariable Long studentNumber, @RequestBody Product product) {

        return service.updateProduct(studentNumber, product);

    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") Long studentNumber) {

        service.deleteProduct(studentNumber);

    }

    @PostMapping("/{id}/photo")
    public void assignPhotoToStudent(@PathVariable("id") Long studentNumber,
                                     @RequestBody MultipartFile file) {

        FileUploadResponse photo = controller.singleFileUpload(file);

        service.assignPhotoToProduct(photo.getFileName(), studentNumber);

    }

}