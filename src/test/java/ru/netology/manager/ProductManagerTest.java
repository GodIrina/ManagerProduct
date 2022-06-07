package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager productManager = new ProductManager(repository);
    Book firstBook = new Book(1, "Гарри Поттер и филосовский камень", 2000, "Джоан Роулинг");
    Book secondBook = new Book(2, "Финансист", 370, "Теодор Драйзер");
    Book thirdBook = new Book(3, "Скотный двор", 320, "Джордж Оруэлл");
    Book fourthBook = new Book(4, "Норвежский лес", 546, "Харуки Мураками");
    Smartphone firstSmartphone = new Smartphone(5, "Samsung", 7000, "Japan");
    Smartphone secondSmartphone = new Smartphone(6, "Nokia", 2000, "Korea");
    Smartphone thirdSmartphone = new Smartphone(7, "Samsung", 7000, "China");


    @Test
    void searchByNameBook() {
        productManager.add(firstBook);
        productManager.add(secondBook);
        productManager.add(thirdBook);
        productManager.add(fourthBook);
        productManager.add(firstSmartphone);
        productManager.add(secondSmartphone);
        productManager.add(thirdSmartphone);

        Product[] actual = productManager.searchBy("Гарри Поттер и филосовский камень");
        Product[] expected = new Product[]{firstBook};
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByDifferentClasses() {
        productManager.add(firstBook);
        productManager.add(secondBook);
        productManager.add(thirdBook);
        productManager.add(fourthBook);
        productManager.add(firstSmartphone);
        productManager.add(secondSmartphone);
        productManager.add(thirdSmartphone);

        Product[] actual = productManager.searchBy("Пираты");
        Product[] expected = new Product[0];
        assertArrayEquals(expected, actual);
    }


    @Test
    void shouldSearchBySomeProductFitRequest() {
        productManager.add(firstBook);
        productManager.add(secondBook);
        productManager.add(thirdBook);
        productManager.add(fourthBook);
        productManager.add(firstSmartphone);
        productManager.add(secondSmartphone);
        productManager.add(thirdSmartphone);

        Product[] actual = productManager.searchBy("Норвежский лес");
        Product[] expected = new Product[]{fourthBook};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReturnEmpty() {
        Product[] expected = {};
        Product[] actual = productManager.searchBy("книга");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReturnOneProducts() {
        productManager.add(thirdBook);
        Product[] expected = new Product[]{thirdBook};
        Product[] actual = productManager.searchBy("Скотный двор");
        assertArrayEquals(expected, actual);


    }

    @Test
    void shouldReturnTwoProducts() {
        productManager.add(firstSmartphone);
        productManager.add(thirdSmartphone);
        Product[] actual = productManager.searchBy("Samsung");
        Product[] expected = new Product[]{firstSmartphone, thirdSmartphone};
        assertArrayEquals(expected, actual);
    }
}