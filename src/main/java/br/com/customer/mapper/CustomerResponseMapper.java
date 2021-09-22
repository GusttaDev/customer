package br.com.customer.mapper;

import br.com.customer.Utils;
import br.com.customer.model.entity.Customer;
import br.com.customer.model.response.CustomerResponse;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomerResponseMapper implements Mapper<Customer, CustomerResponse>{

    @Override
    public CustomerResponse mapperTo(Customer input) {

        if(Objects.isNull(input)){
            return null;
        }

        CustomerResponse response = new CustomerResponse();

        response.setId(input.getId());
        response.setName(input.getName());

        var formattedCpfWithDots = Utils.formatCPF(input.getCpf());
        var maskAsteriskCPF = Utils.maskAsteriskCPF(formattedCpfWithDots);

        response.setCpf(maskAsteriskCPF);
        response.setBirthDate(input.getBirthDate());

        return response;
    }
}
