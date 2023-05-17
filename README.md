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