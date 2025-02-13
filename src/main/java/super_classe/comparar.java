package super_classe;

import utils.JavaUtils;

import javax.persistence.Column;
import java.lang.reflect.Field;

public class comparar {

    /// Metodo de comparação de objetos
    public List<HistoricoAlteracao> comparableFields(Object object) throws IllegalAccessException {
        List<HistoricoAlteracao> camposAlterados = new ArrayList<>();
        Object vendaNew = this;
        Object vendaOld = object;

        ///Percorre os campos da classe
        for (Field field : Object.class.getDeclaredFields()) {
            /// Somente verifica aqueles que tem atribuido a anotação 'Column'
            if ((field.getAnnotation(Column.class)) != null) {
                String campo = field.getName();
                Object novoValor = "";
                Object antigoValor = "";

                ///Verifica os campos declarados para a nova lote produto
                for (Field f : vendaNew.getClass().getDeclaredFields()) {
                    f.setAccessible(true);
                    if (f.getName().equals(campo)) {
                        novoValor = JavaUtils.valorAlteracao(f.get(vendaNew), f);
                        break;
                    }
                }


                ///Verifica os campos declarados para a antiga Lote produto
                for (Field f : vendaOld.getClass().getDeclaredFields()) {
                    f.setAccessible(true);
                    if (f.getName().equals(campo)) {
                        antigoValor = JsfUtil.valorAlteracao(f.get(vendaOld), f);
                        break;
                    }
                }

                ///Compara os valores e gerar o historico deles
                if (!Objects.equals(antigoValor, novoValor))
                    camposAlterados.add(new HistoricoAlteracao(
                            "Venda: " + this.getVenId(),
                            campo,
                            antigoValor != null ? antigoValor.toString() : "",
                            novoValor != null ? novoValor.toString() : "",
                            this.getVenId())
                    );

            }
        }

        return camposAlterados;
    }
}
