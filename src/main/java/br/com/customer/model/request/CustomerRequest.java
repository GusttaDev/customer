package br.com.customer.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class CustomerRequest {

    @NotNull(message = "Nome não pode estar nulo")
    @Pattern(regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇÑ ]+$", message = "Nome não pode conter caracteres especiais ou números!")
    @Pattern(regexp = "^((.)(?!\\2{3}))+$", message = "Nome não pode conter 4 caracteres iguais em sequência!")
    @Size(min = 3, max = 100, message = "Campo 'name' deve conter entre 3 a 100 caracteres")
    private String name;

    @NotNull(message = "Cpf não pode estar nulo")
    @Size(min = 11, max = 11, message = "CPF deve ter 11 dígitos")
    @CPF(message = "Informe um CPF válido")
    private String cpf;

    @Past
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

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
