package aux_classe;

import java.util.Date;

public class HistoricoAlteracao {

    private String local;
    private String field;
    private String oldValue;
    private String newValue;
    private Long idOrigem;
    private Date dataAlteracao;


    public HistoricoAlteracao(String local, String field, String oldValue, String newValue,
                              Long idOrigem) {
        this.local = local;
        this.field = field;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.idOrigem = idOrigem;
        this.dataAlteracao = new Date();
    }


    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Long getIdOrigem() {
        return idOrigem;
    }

    public void setIdOrigem(Long idOrigem) {
        this.idOrigem = idOrigem;
    }

    @Override
    public String toString() {
        return "HistoricoAlteracao{" +
                "local='" + local + '\'' +
                ", campo ='" + field + '\'' +
                ", valor comparação ='" + oldValue + '\'' +
                ", valor principal ='" + newValue + '\'' +
                ", origem=" + idOrigem +
                '}';
    }
}
