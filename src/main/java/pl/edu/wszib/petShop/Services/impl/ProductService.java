package pl.edu.wszib.petShop.Services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.petShop.Database.Database;
import pl.edu.wszib.petShop.Model.Product;
import pl.edu.wszib.petShop.Services.IProductService;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    Database database;

    @Override
    public List<Product> getAllProducts() {
        return database.getProducts();
    }
}
