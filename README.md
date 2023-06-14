# javaexamples

## Exemplos
- spring com thread virtual do java 19
- flexypool, um proxy para os principais pool de conexoes como hikari por exemplo. Ele tira métricas e ajuda no dimensionamento do pool

## MDC
- O contexto de diagnóstico mapeado, fornece uma maneira de enriquecer as mensagens de log com informações que podem estar indisponíveis no escopo em que o log 
- realmente ocorre, mas que podem ser realmente úteis para rastrear melhor a execução do programa.
- para templates de logs https://logback.qos.ch/manual/layouts.html#writingYourOwnLayout

## Exemplo de consulta graphql
- http://localhost:8080/graphiql?path=/graphql
```
query {
    recentPosts(count: 10, offset: 0) {
        id
        title
        category
        author {
            id
            name
        }
    }
}

mutation {
  createPost(title: "teste",
    category: "caneta azul",
    authorId: "1") {
    id
    title
  }
}

```

## Aspectos
- classe java que modulariza um conjunto de preocupações, como log e gerenciamento de transações.
- também conhecidos como conselhos de classes, que podem ser chamados antes, depois do retorno de um objeto.
- anotações suportadas: @Before, @After, @AfterReturning, @AfeterThrowing e @Around
- exemplo:
```
    @Before("execution(* ArithmeticCalculator.add(..))")
    public void logBefore() {
        log.info("the method add() begins");
    }
```
- o log será gerado antes da chamada do metodo
- o pointcut, é a expressão dentro do @Before, corresponde a um conjunto de pontos de junção
- o * significa que o método pode ser privado, protected ou public e qualquer tipo de retorno
- os dois .. correspondem a qualquer número de argumentos
- a anotação @Around, e a combinação de todas as anteriores, obtem controle total do pointcut, podemos alterar args e o retorno do metodo.

### PointCut
- podemos referenciar classes que possuem uma determinada anotação, por exemplo:
```
@Pointcut("@within(com.apress.spring6recipes.calculator.LoggingRequired)")
public void loggingOperation () {}
```
- obs: o @ indica que é para anotação e sem indica para classes ou métodos
- ou
```
@Pointcut("@annotation(com.github.fabriciolfj.javaexamples.annotations.LogAop)")
```

### Agregando funcionalidades via aspectj
- um recurso interessante é fazer uma classes simiular que está herdando 2.
- no exemplo abaixo, uma classe agrega funcionalidade, de 2 interfaces (com suas correspondentes implementações)
- mas a classe em si, não é modificada.
```
@Aspect
@Component
public class CalculatorIntroduction {

    @DeclareParents(
            value = "com.github.fabriciolfj.javaexamples.service.impl.StandardArithmeticCalculator",
            defaultImpl = SimpleMaxCalculator.class
    )
    public MaxCalculator maxCalculator;

    @DeclareParents(
            value = "com.github.fabriciolfj.javaexamples.service.impl.StandardArithmeticCalculator",
            defaultImpl = SimpleMinCalculator.class
    )
    public MinCalculator minCalculator;
}
```
- a chamada seria
```

    private final ArithmeticCalculator calculator;

    @GetMapping
    public void test() {
        //calculator.add(1.2, 2);
        //calculator.mul(3, 3);
        //calculator.div(4, 2);
        var maxCalculator = (MaxCalculator) calculator;
        maxCalculator.max(1, 2);

        var minCalculator = (MinCalculator) calculator;
        minCalculator.min(4, 5);
    }
```
## scopes
- Singleton: É o escopo padrão. Quando um bean é definido como singleton, o contêiner do Spring cria uma única instância desse bean por aplicação e a reutiliza sempre que o bean é solicitado. Todas as solicitações de beans com escopo singleton retornarão a mesma instância.
- Prototype: Com esse escopo, o contêiner do Spring cria uma nova instância do bean sempre que é solicitada. Diferentes solicitações de beans com escopo prototype retornarão instâncias separadas.
- Request: Esse escopo está disponível apenas para aplicativos da web. Cada solicitação HTTP resulta na criação de uma nova instância do bean. O bean fica disponível apenas para a duração da solicitação HTTP.
- Session: Também disponível apenas para aplicativos da web, esse escopo cria um bean exclusivo para cada sessão do usuário. O bean permanece disponível durante a sessão do usuário. 
- Global Session: Esse escopo é semelhante ao escopo de sessão, mas é aplicado apenas em aplicativos da web com várias sessões. O bean fica disponível durante a sessão global, que abrange várias sessões de usuário.
- Application: Esse escopo é válido apenas para aplicativos da web que usam o contexto do aplicativo. O contêiner do Spring cria uma única instância do bean por aplicação e a reutiliza em todas as solicitações. 
- WebSocket: Esse escopo está disponível para aplicativos da web que usam o protocolo WebSocket. Cada conexão WebSocket tem um bean exclusivo associado a ela.


## SseEmitter
- quando quero enviar enventos ou um fluxo de stream ao cliente, via uma api rest

## Transacoes
- serie de ações tratadas como uma única unidade de trabalho, devem ser concluídas totalmente ou não ter efeito
- O conceito de transações pode ser descrito com quatro propriedades principais — atomicidade, consistência, isolamento e durabilidade (ACID) :
  - Atomicidade : Uma transação é uma operação atômica que consiste em uma série de ações. A atomicidade de uma transação garante que as ações sejam totalmente concluídas ou não tenham nenhum efeito. 
  - Consistência : Uma vez que todas as ações de uma transação foram concluídas, a transação é confirmada. Assim, seus dados e recursos estarão em um estado consistente em conformidade com as regras de negócios. 
  - Isolamento : como pode haver muitas transações processando com o mesmo conjunto de dados ao mesmo tempo, cada transação deve ser isolada das outras para evitar corrupção de dados. 
  - Durabilidade : Depois que uma transação é concluída, seu resultado deve ser durável para sobreviver a qualquer falha do sistema (imagine se a energia de sua máquina fosse cortada bem no meio da confirmação de uma transação). Normalmente, o resultado de uma transação é gravado no armazenamento persistente.