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
1.14