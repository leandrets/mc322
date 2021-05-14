# Lab06 - O Mundo de Wumpus

## Arquivos Java do Jogo
Para acionar o jogo, entre na pasta `bin` e execute `java mc322.lab06.AppMundoWumpus path`, onde o argumento `path` é o caminho para o seu arquivo CSV de entrada, ou utilize os arquivos oferecidos em `../data/teste*.csv`. 
Você também pode tirar os indicadores de comentário nas linhas 8 e 9 do arquivo `AppMundoWumpus.java` e acionar o jogo com `../data/caverna.csv` como o argumento `path`. Isso irá gerar uma caverna aleatória toda vez que você acionar o jogo.  
(src/mc322/lab06)[src/mc322/lab06]

## Destaques de Arquitetura
### Polimorfismo na Criação dos Componentes
```
public boolean montarCaverna(...) {
    Componente componente = null;
    ...
    else if (conteudo.equals("O")) {
        componente = new Ouro(i, j, conteudo);
        numOuro++;
    }	
    else if (conteudo.equals("B")) {
        componente = new Buraco(i, j, conteudo);
        numBuraco++;
    }
    else if (conteudo.equals("W")) {
        componente = new Wumpus(i, j, conteudo);
        numWumpus++;
    } 
    ...
}
```
Nesse recorte do método montarCaverna(), em que `conteudo` é a String da segunda coluna do arquivo de entrada, vemos que a adição de um futuro novo Componente (por exemplo, uma classe Teleporte) seria de fácil implementação: bastaria adicionar um novo `else if`, sem ter que fazer grandes mudanças nas outras classes. Ressalta-se também o intenso uso do polimorfismo, já que os componentes são declarados na classe Componente, mas instanciados nas classes herdeiras (Ouro, Buraco etc).
