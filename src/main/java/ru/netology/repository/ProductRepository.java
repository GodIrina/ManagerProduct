package ru.netology.repository;

import ru.netology.domain.Product;
import ru.netology.domain.NotFoundException;

public class ProductRepository {
    private Product[] items = new Product[0];

    public void save(Product item) {
        int length = items.length + 1;
        Product[] tmp = new Product[length];
        System.arraycopy(items, 0, tmp, 0, items.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = item;
        items = tmp;
    }

    public Product[] findAll() {
        Product[] tmp = new Product[items.length];
        System.arraycopy(items, 0, tmp, 0, items.length);
        return tmp;
    }

    public Product findById(int id) {
        for (Product item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void removeById(int id) {
        int length = items.length - 1;
        Product[] tmp = new Product[length];

        if (findById(id) == null) {
            throw new NotFoundException("Продукт с id: " + id + " не найден.");
        }
        int index = 0;
        for (Product item : items) {
            if (item.getId() != id) {
                tmp[index] = item;
                index++;
            }
        }
        items = new Product[tmp.length];
        System.arraycopy(tmp, 0, items, 0, tmp.length);
    }
}
