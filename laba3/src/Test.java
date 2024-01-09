import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ECommerceSystemTest {

    @Test
    void testAddAndRemoveProductFromCart() {
        Product product1 = new Product(1, "Laptop", 999.99);
        Product product2 = new Product(2, "Smartphone", 499.99);

        Cart cart = new Cart();

        cart.addProduct(product1);
        cart.addProduct(product2);

        assertEquals(2, cart.getProducts().size());

        cart.removeProduct(product1);

        assertEquals(1, cart.getProducts().size());
        assertEquals(product2, cart.getProducts().get(0));
    }

    @Test
    void testPlaceOrder() {
        Product product1 = new Product(1, "Laptop", 999.99);
        Product product2 = new Product(2, "Smartphone", 499.99);

        Cart cart = new Cart();
        cart.addProduct(product1);
        cart.addProduct(product2);

        Order order = new Order(1, cart.getProducts());

        assertEquals(OrderStatus.PENDING, order.getStatus());

        order.setStatus(OrderStatus.PROCESSING);

        assertEquals(OrderStatus.PROCESSING, order.getStatus());
    }

    @Test
    void testGetOrderStatus() {
        Product product1 = new Product(1, "Laptop", 999.99);
        Product product2 = new Product(2, "Smartphone", 499.99);

        Cart cart = new Cart();
        cart.addProduct(product1);
        cart.addProduct(product2);

        Order order = new Order(1, cart.getProducts());

        assertEquals(OrderStatus.PENDING, order.getStatus());
    }
}
