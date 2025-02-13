package utils;

import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.MaskFormatter;
import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.*;
import java.util.*;


import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.MaskFormatter;
import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.*;
import java.util.*;

public class JavaUtils {

    public static boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static boolean isEmpty(List s) {
        return s == null || s.isEmpty();
    }

    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    public static String convertToMoney(BigDecimal valor) {
        if (valor == null) return "R$ 00,00";
        DecimalFormat decFormat = new DecimalFormat("#,###,##0.00");
        return "R$ " + decFormat.format(valor.setScale(2, RoundingMode.HALF_DOWN));
    }

    public static String convertToMoney(String valor) {
        if (valor == null) return "R$: 00,00";
        return convertToMoney(new BigDecimal(valor));
    }


    public static Object valorAlteracao(Object obj, Field f) {
        Object valor = obj;
        if (f.getType().equals(Date.class) && valor != null) {
            valor = new SimpleDateFormat("dd/MM/yyyy").format((Date) valor);
        }
        if (f.getType().equals(BigDecimal.class) && valor != null
                && !(f.getName().toLowerCase().contains("qtd")
                || f.getName().toLowerCase().contains("quantidade"))) {
            valor = convertToMoney(valor.toString());
        }
        if (f.getType().equals(BigDecimal.class) && valor != null
                && (f.getName().toLowerCase().contains("qtd")
                || f.getName().toLowerCase().contains("quantidade"))) {
            valor = new BigDecimal(valor.toString()).setScale(4, RoundingMode.UNNECESSARY);
        }
        return valor;
    }


    public static Object valorAlteracao2(Object obj, Field f) {
        Object valor = obj;
        if (f.getType().equals(String.class) && valor != null) {
            valor = "'" + removeCaractereEspecial(valor.toString()).replaceAll("\"", " ") + "'";
        }

        if (f.getType().equals(Date.class) && valor != null) {
            valor = "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) valor) + "'";
        }

        if (f.getType().equals(BigDecimal.class) && valor != null) {
            valor = new BigDecimal(valor.toString()).setScale(4, RoundingMode.UNNECESSARY);
        }
        return valor;
    }

    public static String removeCaractereEspecial(String input) {
        if (JavaUtils.isNotEmpty(input)) {
            input = Normalizer.normalize(input, Normalizer.Form.NFD);
            input = input.replaceAll("[^\\p{ASCII}]", "");
            input = input.replaceAll("(\\*)|(#)", "");
            input = input.replaceAll("'", "");
            input = input.replaceAll("\r?\n|\r", " ");
            //HOUVE CASOS QUE OS METODOS ACIMA NAO REMOVERAM OS ACENTUOS, A CAMADA ABAIXO FOI IMPLEMENTADA PARA REFORÇAR A REMOÇÃO (MESMO SENDO "REDUNDANTE")
            input = input.replaceAll("[ÂÀÁÄÃ]", "A");
            input = input.replaceAll("[âãàáä]", "a");
            input = input.replaceAll("[ÊÈÉË]", "E");
            input = input.replaceAll("[êèéë]", "e");
            input = input.replaceAll("ÎÍÌÏ", "I");
            input = input.replaceAll("îíìï", "i");
            input = input.replaceAll("[ÔÕÒÓÖ]", "O");
            input = input.replaceAll("[ôõòóö]", "o");
            input = input.replaceAll("[ÛÙÚÜ]", "U");
            input = input.replaceAll("[ûúùü]", "u");
            input = input.replaceAll("Ç", "C");
            input = input.replaceAll("ç", "c");
            input = input.replaceAll("[ýÿ]", "y");
            input = input.replaceAll("Ý", "Y");
            input = input.replaceAll("ñ", "n");
            input = input.replaceAll("Ñ", "N");
            input = input.replaceAll("&", "E");
            return input.toUpperCase().trim();
        }
        return "";
    }

}
