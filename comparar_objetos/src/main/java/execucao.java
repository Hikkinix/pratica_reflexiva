import aux_classe.HistoricoAlteracao;
import entidade.Filme;
import utils.JavaUtils;

import java.io.File;
import java.util.List;

public class execucao {
    public static void main(String[] args) {
        Filme filmeA = new Filme("Ainda Estou Aq", 2024, "drama");
        Filme filmeB = new Filme("Ainda Estou Aqui", 2024, "drama");

        try {
            System.out.println("INICIO---");
            List<HistoricoAlteracao> alteracaos = filmeA.comparableFields(filmeB);

            if (JavaUtils.isEmpty(alteracaos)) {
                System.out.println("nenhuma alteração");
            } else {
                for (HistoricoAlteracao alteracao : alteracaos) {
                    System.out.println(alteracao.toString());
                }
            }
            System.out.println("FIM---");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
