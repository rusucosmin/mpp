package ro.ubb.bookstore.common.model.validators;

import ro.ubb.bookstore.common.model.Order;

public class OrderValidator implements Validator<Order> {
    @Override
    public void validate(Order entity) throws OrderException {
        if(entity.getCnt() == null)
            throw new OrderException("Order cnt should not be null");
        if(entity.getBookID() == null)
            throw new OrderException("Order bookID should not be null");
        if(entity.getClientID() == null)
            throw new OrderException("Order clientID should not be null");
        if(entity.getCnt() <= 0)
            throw new OrderException("Order cnt should be strictly positive");
    }
}
