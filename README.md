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
``