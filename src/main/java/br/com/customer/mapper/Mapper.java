package br.com.customer.mapper;

public interface Mapper<A, B> {
    B mapperTo(A input);
}
