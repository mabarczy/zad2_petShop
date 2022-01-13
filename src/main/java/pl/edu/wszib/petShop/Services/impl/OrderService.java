package pl.edu.wszib.petShop.Services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.petShop.Database.Database;
import pl.edu.wszib.petShop.Model.Order;
import pl.edu.wszib.petShop.Model.OrderPosition;
import pl.edu.wszib.petShop.Model.Product;
import pl.edu.wszib.petShop.Services.IOrderService;
import pl.edu.wszib.petShop.Session.SessionObject;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Resource
    SessionObject sessionObject;

    @Autowired
    Database database;

    @Override
    public void confirmOrder() {
        Order order = new Order(this.sessionObject.getUser(), this.sessionObject.getCart().getOrderPositions());
        this.database.addOrder(order);
        for (OrderPosition orderPosition : order.getOrderPositions()) {
            Optional<Product> productBox = database.getProductById(orderPosition.getProduct().getId());
            if(productBox.isPresent()) {
                productBox.get().setQuantity(productBox.get().getQuantity() - orderPosition.getQuantity());
            }
        }
        this.sessionObject.getCart().clearOrderPositions();
    }

    @Override
    public List<Order> getOrdersForCurrentUser() {
        return this.database.getOrdersByUserId(this.sessionObject.getUser().getId());
    }
}
