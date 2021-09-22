package br.com.customer.util;

import br.com.customer.utils.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UtilTest {

    @Test
    public void formatCPF_whenInformedACpfWithoutDot_shouldReturnCpfWithDots(){
        var cpf = "12345678910";
        var formattedCpf = Utils.formatCPF(cpf);

        assertThat(formattedCpf).isEqualTo("123.456.789-10");
    }

    @Test
    public void maskAsteriskCPF_whenInformedACpfWithDot_shouldReturnCpfWithAsterisk(){
        var cpf = "123.456.789-10";
        var maskCpf = Utils.maskAsteriskCPF(cpf);

        assertThat(maskCpf).isEqualTo("***.456.***-10");
    }

}
