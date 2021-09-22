package br.com.customer.model.entity;

import br.com.customer.mapper.Mapper;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.function.Function;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "CPF", nullable = false)
    private String cpf;
    @Column(name = "BIRTH_DATE", nullable = false)
    private LocalDate birthDate;

    public <R> R map(Function<Customer, R> func){
        return func.apply(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
