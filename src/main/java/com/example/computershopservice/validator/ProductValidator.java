package com.example.computershopservice.validator;

import com.example.computershopservice.entity.Product;
import com.example.computershopservice.exception.MissingDataException;

public class ProductValidator {

    public static void checkFields(Product product) {

        if (product.getSerialNumber() == null) {
            throw new MissingDataException("Поле с серийным номером должно быть заполнено.");
        }

        if (product.getProducer() == null) {
            throw new MissingDataException("Поле с производителем должно быть заполнено.");
        }

        if (product.getPrice() == null) {
            throw new MissingDataException("Поле с ценой должно быть заполнено.");
        }

        if (product.getAmount() == null) {
            throw new MissingDataException("Поле с количеством товара на складе должно быть заполнено.");
        }

    }
}
