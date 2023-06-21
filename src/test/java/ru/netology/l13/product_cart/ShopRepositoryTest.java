package ru.netology.l13.product_cart;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {
    ShopRepository cart = new ShopRepository();
    Product product1 = new Product(34, "Вкусняшка для кошек Мнямс", 234);
    Product product2 = new Product(48, "Мячик", 487);
    Product product3 = new Product(4, "Травка (овёс)", 123);
    Product product4 = new Product(4, "Наклейка \"Лапка\"", 40);
    Product product5 = new Product(792, "Корм сухой \"Farmina\" беззерновой", 7999);
    Product product6 = new Product(71, "Лабиринт для кошек", 719);

    @BeforeEach
    public void setup() {
        cart.add(product3);
        cart.add(product5);
        cart.add(product1);
    }


    @Test
    public void shouldDeleteExistingElement() {

        cart.removeByID(792);
        Product[] expected = {product3, product1};
        Product[] actual = cart.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowNotFoundExp() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            cart.removeByID(16);
        });
    }

    @Test
    public void shouldAddNewElement() {
        cart.add(product6);

        Product[] expected = {product3, product5, product1, product6};
        Product[] actual = cart.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowAlreadyExists() {
        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            cart.add(product4);
        });
    }
}
