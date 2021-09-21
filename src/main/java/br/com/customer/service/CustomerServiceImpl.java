package br.com.customer.service;

import br.com.customer.mapper.Mapper;
import br.com.customer.model.entity.Customer;
import br.com.customer.model.request.CustomerRequest;
import br.com.customer.model.response.CustomerResponse;
import br.com.customer.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService{

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private Mapper<CustomerRequest, Customer> requestMapper;

    @Autowired
    private Mapper<Customer, CustomerResponse> responseMapper;

    @Override
    public Customer create(CustomerRequest customerRequest) {
        LOGGER.info("Criando um registro do cliente");

        Assert.notNull(customerRequest, "Request invalida!");
        Customer customer = this.requestMapper.mapperTo(customerRequest);

        return customerRepository.save(customer);
    }

    @Override
    public Page<CustomerResponse> getAll(Pageable pageable) {
        LOGGER.info("Buscando todos os registros");

        return null;
    }

    @Override
    public Optional<CustomerResponse> get(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<CustomerResponse> update(Long id, CustomerRequest customerRequest) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }
}
