package entidade;

import abstracao.VerificarAlteracaoObj;
import aux_classe.Comparavel;

import javax.persistence.*;

public class Filme extends VerificarAlteracaoObj {

    @Comparavel()
    private Long id;
    @Comparavel()
    private String titulo;
    @Comparavel()
    private Integer ano;
    @Comparavel()
    private String genero;

    public Filme(String titulo, Integer ano, String genero) {
        this.titulo = titulo;
        this.ano = ano;
        this.genero = genero;
    }

    public Filme() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
