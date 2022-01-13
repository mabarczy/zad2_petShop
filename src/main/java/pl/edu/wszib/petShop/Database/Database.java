package pl.edu.wszib.petShop.Database;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import pl.edu.wszib.petShop.Model.Order;
import pl.edu.wszib.petShop.Model.Product;
import pl.edu.wszib.petShop.Model.User;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class Database {
    private List<Product> products = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    private Database() {
        products.add(new Product(1,"Mokra karma dla kota", 6.50, 6));
        products.add(new Product(2,"Mokra karma dla psa", 6.50, 7));
        products.add(new Product(3,"Zabawkowa mysz", 11.99, 10));
        products.add(new Product(4,"Kosc do gryzienia", 7.80, 8));
        products.add(new Product(5,"Kuweta", 50.00, 1));
        products.add(new Product(6,"Smycz", 19.99, 4));

        users.add(new User(1,"Ala", DigestUtils.md5Hex("ala1")));
        users.add(new User(2,"Beata", DigestUtils.md5Hex("beata2")));
        users.add(new User(3,"Celina", DigestUtils.md5Hex("celina3")));
    }


    public List<Product> getProducts() {
        return products;
    }

    public Optional<User> getUserByLogin(String login){
        for(User user : users){
            if(user.getLogin().equals(login)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public void addUser(User user){
        users.add(user);
    }
    public Optional<Product> getProductById(int id){
        for(Product product : products){
            if(product.getId() == id){
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }

    public void addOrder(Order order){orders.add(order);}

    public List<Order> getOrdersByUserId(int id){
        List<Order> returnedOrders = new ArrayList<>();
        for(Order order : orders){
            if(order.getUser().getId() == id){
                returnedOrders.add(order);
            }
        }
        return returnedOrders;
    }
}
