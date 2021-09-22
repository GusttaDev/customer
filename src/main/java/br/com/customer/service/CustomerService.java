package br.com.customer.service;

import br.com.customer.model.entity.Customer;
import br.com.customer.model.request.CustomerRequest;
import br.com.customer.model.response.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CustomerService {

    CustomerResponse create(CustomerRequest customerRequest);

    Page<CustomerResponse> getAll(Pageable pageable);

    Optional<CustomerResponse> get(Long id);

    Optional<CustomerResponse> update(Long id, CustomerRequest customerRequest);

    void delete(Long id);
}
