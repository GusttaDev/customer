package br.com.customer.mapper;

import br.com.customer.model.entity.Customer;
import br.com.customer.model.request.CustomerRequest;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomerRequestMapper implements Mapper<CustomerRequest, Customer>{

    @Override
    public Customer mapperTo(CustomerRequest input) {

        if(Objects.isNull(input)){
            return null;
        }

        Customer customer = new Customer();
        customer.setName(input.getName());
        customer.setCpf(input.getCpf());
        customer.setBirthDate(input.getBirthDate());

        return customer;
    }
}
