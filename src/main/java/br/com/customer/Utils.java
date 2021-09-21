package br.com.customer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static String formatCPF(String cpf) {

        Pattern pattern = Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})");
        Matcher matcher = pattern.matcher(cpf);

        if (matcher.matches()){
            cpf = matcher.replaceAll("$1.$2.$3-$4");
        }

        return cpf;
    }

    public static String maskAsteriskCPF(String cpf){

        var maskCPF = "";
        Pattern pattern = Pattern.compile("([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})");
        Matcher matcher = pattern.matcher(cpf);

        if (matcher.matches()){
            var topThree = cpf.substring(0, 3);
            var lastThree = cpf.substring(8, 11);

            maskCPF = cpf.replaceAll(topThree, "***").replaceAll(lastThree, "***");

        }

        return maskCPF;
    }
}
