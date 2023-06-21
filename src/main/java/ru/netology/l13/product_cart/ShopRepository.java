package ru.netology.l13.product_cart;

public class ShopRepository {
    private Product[] products = new Product[0];

    public Product[] findAll() {
        return products;
    }

    public Product findById(int id) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].getId() == id) {
                return products[i];
            }
        }
        return null;
    }

    /**
     * Вспомогательный метод для имитации добавления элемента в массив
     *
     * @param current — массив, в который мы хотим добавить элемент
     * @param product — элемент, который мы хотим добавить
     * @return — возвращает новый массив, который выглядит, как тот, что мы передали,
     * но с добавлением нового элемента в конец
     */
    private Product[] addToArray(Product[] current, Product product) {
        if (findById(product.getId()) != null) {
            throw new AlreadyExistsException("Продукт с id " + product.getId() + " уже существует в базе");
        } else {
            Product[] tmp = new Product[current.length + 1];
            for (int i = 0; i < current.length; i++) {
                tmp[i] = current[i];
            }
            tmp[tmp.length - 1] = product;
            return tmp;
        }
    }

    /**
     * Метод добавления товара в репозиторий
     *
     * @param product — добавляемый товар
     */
    public void add(Product product) {
        products = addToArray(products, product);
    }

    // Этот способ мы рассматривали в теории в теме про композицию
    public void removeByID(int id) {
        Product[] tmp = new Product[products.length - 1];
        if (findById(id) == null) {
            throw new NotFoundException("Продукта с id " + id + "нет в базе продуктов");
        } else {
            int copyToIndex = 0;
            for (Product product : products) {
                if (product.getId() != id) {
                    tmp[copyToIndex] = product;
                    copyToIndex++;
                }
            }
            products = tmp;
        }
    }
}
