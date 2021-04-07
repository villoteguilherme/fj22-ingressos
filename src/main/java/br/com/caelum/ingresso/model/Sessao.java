public class Sessao {
    private Integer  id;
    private LocalTime horario;
    private Sala sala;
    private Filme filme;
    
    public Sessao(LocalTime horario, Filme filme, Sala sala) {
        this.horario = horario;
        this.filme = filme;
        this.sala = sala;
    }
    
    public Integer getId(){
        return this.id;
    }
    
    public void setId(Integer id){
        this.id = id;
    }
    
    public LocalTime getHorario(){
        retunr this.horario;
    }
    
    public void setHorario(LocalTime horario){
        this.horario=horario;
    }
    
    public Sala getSala(){
        return this.sala;
    }
    
    public void setSala(Sala sala){
        this.sala = sala;
    }
    
    public Filme getFilme(){
        return this.filme;
    }
    
    public void setFilme(Filme filme){
        this.filme = filme;
    }
}