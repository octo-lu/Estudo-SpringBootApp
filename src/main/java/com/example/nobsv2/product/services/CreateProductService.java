package com.example.nobsv2.product.services;


import ch.qos.logback.core.util.StringUtil;
import com.example.nobsv2.Command;
import com.example.nobsv2.exceptions.ErrorMesages;
import com.example.nobsv2.exceptions.ProductNotValidException;
import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.validators.ProductValidator;
import io.micrometer.common.util.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CreateProductService implements Command<Product, ProductDTO> {

    private final ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(CreateProductService.class);

    public CreateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(Product product) {
        //validation
        //ProductValidator.execute(product);
        logger.info("Executing " + getClass() + "product " + product);

        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductDTO(savedProduct));
    }


}
