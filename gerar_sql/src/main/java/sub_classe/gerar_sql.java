package sub_classe;

import utils.JavaUtils;

import javax.persistence.Column;
import javax.persistence.Id;
import java.lang.reflect.Field;

public class gerar_sql {

    public String prepareToInsert() throws Exception {

        StringBuilder insertCampos = new StringBuilder();
        StringBuilder insertValores = new StringBuilder();
        StringBuilder insertComplete = new StringBuilder();
        for (Field field : this.getClass().getDeclaredFields()) {
            Object valor = null;
            /// Somente verifica aqueles que tem atribuido a anotação 'Column'
            if ((field.getAnnotation(Column.class)) != null && (field.getAnnotation(Id.class)) == null) {
                String campo = field.getName();
                String campoSql = (field.getAnnotation(Column.class)).name();
                if (JavaUtils.isNotEmpty(insertCampos.toString())) {
                    insertCampos.append(",");
                }
                insertCampos.append(campoSql);

                ///Verifica os campos declarados para a nova lote produto
                for (Field f : this.getClass().getDeclaredFields()) {
                    f.setAccessible(true);
                    if (f.getName().equals(campo)) {
                        valor = JavaUtils.valorAlteracao2(f.get(this), f);
                        break;
                    }
                }
                if (JavaUtils.isNotEmpty(insertValores.toString())) {
                    insertValores.append(",");
                }
                insertValores.append(valor);
            }


        }

        insertComplete.append("insert into ").append(this.getClass().getSimpleName())
                .append("(").append(insertCampos).append(")");
        insertComplete.append(" values (").append(insertValores).append(");\n");

        System.out.println(insertComplete.toString());

        return insertComplete.toString();
    }
}
