package abstracao;

import aux_classe.Comparavel;
import aux_classe.HistoricoAlteracao;
import utils.JavaUtils;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class VerificarAlteracaoObj {

    /// Metodo de comparação de objetos
    public List<HistoricoAlteracao> comparableFields(Object object) throws IllegalAccessException {
        List<HistoricoAlteracao> camposAlterados = new ArrayList<>();
        Object objNew = this;
        Object objOld = object;

        System.out.println(getClass().getSimpleName());
        ///Percorre os campos da classe
        for (Field field : objNew.getClass().getDeclaredFields()) {
            /// Somente verifica aqueles que tem atribuido a anotação 'Column'
            if ((field.getAnnotation(Comparavel.class)) != null) {
                String campo = field.getName();
                Object novoValor = "";
                Object antigoValor = "";

                ///Verifica os campos declarados para a nova lote produto
                for (Field f : objNew.getClass().getDeclaredFields()) {
                    f.setAccessible(true);
                    if (f.getName().equals(campo)) {
                        novoValor = JavaUtils.valorAlteracao(f.get(objNew), f);
                        break;
                    }
                }


                ///Verifica os campos declarados para a antiga Lote produto
                for (Field f : objOld.getClass().getDeclaredFields()) {
                    f.setAccessible(true);
                    if (f.getName().equals(campo)) {
                        antigoValor = JavaUtils.valorAlteracao(f.get(objOld), f);
                        break;
                    }
                }

                ///Compara os valores e gerar o historico deles
                if (!Objects.equals(antigoValor, novoValor))
                    camposAlterados.add(new HistoricoAlteracao(
                            "Classe: " + this.getClass(),
                            campo,
                            antigoValor != null ? antigoValor.toString() : "",
                            novoValor != null ? novoValor.toString() : "",
                            new Date().getTime())
                    );

            }
        }

        return camposAlterados;
    }
}
