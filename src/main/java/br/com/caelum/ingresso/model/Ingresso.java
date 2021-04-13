package br.com.caelum.ingresso.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Duration;
import java.math.BigDecimal;
import java.math.RoundingMode;
import br.com.caelum.ingresso.model.descontos.Desconto;

public class Ingresso {
    
    private Sessao sessao;
    
    private BigDecimal preco;
    
    public Ingresso(Sessao sessao, Desconto tipoDeDesconto) {
        this.sessao = sessao;
        this.preco = tipoDeDesconto.aplicarDescontoSobre(sessao.getPreco());
    }
    
    public BigDecimal getPreco() {
        return preco.setScale(2, RoundingMode.HALF_UP);
    }
    
    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setSessao(Sessao sessao){
        this.sessao = sessao;
    }
    
    public Sessao getSessao(){
        return sessao;
    }
}