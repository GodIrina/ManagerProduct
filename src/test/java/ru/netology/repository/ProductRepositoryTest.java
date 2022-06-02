package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Smartphone;
import ru.netology.domain.Product;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    ProductRepository repository = new ProductRepository();
    Book firstBook = new Book(1, "Гарри Поттер и филосовский камень", 2000, "Джоан Роулинг");
    Book secondBook = new Book(2, "Финансист", 370, "Теодор Драйзер");
    Book thirdBook = new Book(3, "1984", 320, "Джордж Оруэлл");
    Book fourthBook = new Book(4, "Норвежский лес", 546, "Хвруки Мураками");
    Smartphone firstSmartphone = new Smartphone(5, "Samsung", 5000, "Japan");
    Smartphone secondSmartphone = new Smartphone(6, "Nokia", 2000, "Korea");
    Smartphone thirdSmartphone = new Smartphone(7, "Xiaomi", 7000, "China");

    @Test
    void shouldAddOneBook() {
        repository.save(firstBook);

        Product[] expected = new Product[]{firstBook};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldAddOneSmartphone() {
        repository.save(firstSmartphone);

        Product[] expected = new Product[]{firstSmartphone};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }


    @Test
    void findAllProduct() {
        repository.save(firstBook);
        repository.save(secondBook);
        repository.save(thirdBook);
        repository.save(fourthBook);
        repository.save(firstSmartphone);
        repository.save(secondSmartphone);
        repository.save(thirdSmartphone);


        Product[] expected = new Product[]{firstBook, secondBook, thirdBook, fourthBook, firstSmartphone, secondSmartphone, thirdSmartphone};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void removeById() {
        int id = 4;
        repository.save(firstBook);
        repository.save(secondBook);
        repository.save(thirdBook);
        repository.save(fourthBook);

        repository.removeById(id);

        Product[] expected = new Product[]{firstBook, secondBook, thirdBook};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void removeByIdIfElementExists() {
        repository.save(firstBook);
        repository.save(secondBook);
        repository.save(thirdBook);
        repository.save(fourthBook);
        repository.save(firstSmartphone);
        repository.save(secondSmartphone);
        repository.save(thirdSmartphone);

        repository.removeById(6);

        Product[] expected = new Product[]{firstBook, secondBook, thirdBook, fourthBook, firstSmartphone, thirdSmartphone};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void removeByIdIfElementNotExists() {
        repository.save(firstBook);
        repository.save(secondBook);
        repository.save(thirdBook);
        repository.save(fourthBook);

        assertThrows(NotFoundException.class, () -> {
            repository.removeById(12);
        });
    }

}