package br.com.customer.service;

import br.com.customer.exception.BusinessException;
import br.com.customer.mapper.Mapper;
import br.com.customer.model.entity.Customer;
import br.com.customer.model.enums.Message;
import br.com.customer.model.request.CustomerRequest;
import br.com.customer.model.response.CustomerResponse;
import br.com.customer.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.util.Assert.notNull;


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
    public CustomerResponse create(CustomerRequest customerRequest) {
        LOGGER.info("Criando um registro do cliente");
        notNull(customerRequest, "Request invalida!");
        Customer customerReq = this.requestMapper.mapperTo(customerRequest);

        validateCpfAlreadyExists(customerReq.getCpf());

        return customerRepository.save(customerReq).map(customer -> this.responseMapper.mapperTo(customer));
    }


    @Override
    public Page<CustomerResponse> getAll(Pageable pageable) {
        LOGGER.info("Buscando todos os registros");
        notNull(pageable, "página invalida!");
        return customerRepository.findAll(pageable).map(customer -> this.responseMapper.mapperTo(customer));
    }

    @Override
    public Optional<CustomerResponse> get(Long id) {
        LOGGER.info("Buscando um registro");
        findCustomerByIdOrThrowNotFound(id);
        return customerRepository.findById(id).map(this.responseMapper::mapperTo);
    }

    @Override
    public Optional<CustomerResponse> update(Long id, CustomerRequest customerRequest) {
        LOGGER.info("Atualizando o regsitro");

        Customer customerUpdate = this.requestMapper.mapperTo(customerRequest);

        findCustomerByIdOrThrowNotFound(id);

        return customerRepository.findById(id).map(customer -> {
            customer.setName(customerUpdate.getName());
            customer.setBirthDate(customerUpdate.getBirthDate());

            return this.responseMapper.mapperTo(customerRepository.saveAndFlush(customer));
        });
    }

    @Override
    public void delete(Long id) {
        LOGGER.info("Removendo regsitro");

        try{
            customerRepository.deleteById(id);
        }catch (Exception e){
            LOGGER.error("Erro ao remove o registro {} erro{}", id, e);
        }
    }

    private void validateCpfAlreadyExists(String cpf) {
        Optional<Customer> customer = customerRepository.findByCpf(cpf);

        if(customer.isPresent()){
            throw Message.CPF_ALREADY_EXISTS.asBusinessException();
        }
    }

    public Customer findCustomerByIdOrThrowNotFound(Long id) {
        return customerRepository.findById(id).orElseThrow(Message.CUSTOMER_NOT_FOUND::asBusinessException);
    }
}
