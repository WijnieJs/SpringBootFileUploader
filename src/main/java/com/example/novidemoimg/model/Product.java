package com.example.novidemoimg.model;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "user_sequence"),
                    @Parameter(name = "initial_value", value = "1003"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private Long studentNumber;

    private String title;
    private String description;
    private int price;

    @OneToOne
    FileUploadResponse file;

    public Product() {}

    public Product(String title, String description, int price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public Product(Long studentNumber, String title, String description, int price) {
        this.studentNumber = studentNumber;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public Long getStudentNumber() {
        return studentNumber;
    }
    public void setStudentNumber(Long studentNumber) {
        this.studentNumber = studentNumber;
    }

    public FileUploadResponse getFile() {
        return file;
    }
    public void setFile(FileUploadResponse file) {
        this.file = file;
    }

    public String getTitle() { return title;    }
    public void setTitle(String title) { this.title = title;  }

    public String getDescription() {  return description; }
    public void setDescription(String description) { this.description = description; }

    public int getPrice() { return price;}
    public void setPrice(int price) { this.price = price; }
}
