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


## Tratamento de erros com Spring MVC usando RFC-7807
- Como o JSON se tornou o padrão de fato de comunicação na Web, agora também existe um padrão (no momento ainda proposto) para retornar respostas de erro ao cliente. Isso é RFC-7807, que pode ser mais conhecido como API de detalhes do problema para HTTP. Esse padrão descreve uma resposta na qual é possível dizer o que há de errado com a solicitação recebida ou o que aconteceu no servidor.

## SseEmitter
- quando quero enviar enventos ou um fluxo de stream ao cliente, via uma api rest

## Transacoes
- serie de ações tratadas como uma única unidade de trabalho, devem ser concluídas totalmente ou não ter efeito
- O conceito de transações pode ser descrito com quatro propriedades principais — atomicidade, consistência, isolamento e durabilidade (ACID) :
  - Atomicidade : Uma transação é uma operação atômica que consiste em uma série de ações. A atomicidade de uma transação garante que as ações sejam totalmente concluídas ou não tenham nenhum efeito. 
  - Consistência : Uma vez que todas as ações de uma transação foram concluídas, a transação é confirmada. Assim, seus dados e recursos estarão em um estado consistente em conformidade com as regras de negócios. 
  - Isolamento : como pode haver muitas transações processando com o mesmo conjunto de dados ao mesmo tempo, cada transação deve ser isolada das outras para evitar corrupção de dados. 
  - Durabilidade : Depois que uma transação é concluída, seu resultado deve ser durável para sobreviver a qualquer falha do sistema (imagine se a energia de sua máquina fosse cortada bem no meio da confirmação de uma transação). Normalmente, o resultado de uma transação é gravado no armazenamento persistente.
- o @EnableTransactionManagement habilita no spring uma forma de escolher qual implementação utilizar para a funcionalidade de transacao, PROXY ou ASPECJ.

## Isolamento
- default -> nivel padrã do banco de dados READ_COMMITTED
- READ_UNCOMMITTED -> permite que a leitura seja feita em alterações não confirmadas por outras transações (podem ocorrer problemas de leitura suja, leitura não repetivel ou fantasma)
- REPEATABLE_READ (aloca um registro)-> Garante que uma transação possa ler valores idênticos de um campo várias vezes. Durante esta transação, as atualizações feitas por outras transações neste campo são proibidas. Os problemas de leitura suja e leitura não repetível podem ser evitados, mas o problema de leitura fantasma ainda pode ocorrer.
- SERIALIZÁVEL (pode alocar uma tabela) -> Garante que uma transação possa ler linhas idênticas de uma tabela várias vezes. Durante esta transação, inserções, atualizações e exclusões feitas por outras transações nesta tabela são proibidas. Todos os problemas de simultaneidade podem ser evitados, mas o desempenho será baixo.


# Spring batch
- ideal para processamento em lotes, como um bot por ex
- toda a regra fica dentro de uma interface chamada JobRepository
- alguns componentes dentro do jobrepository:
 - jobinstances -> que recebe o jobparameters
 - jobexecutions -> e a instance da jobinstances
 - StepExecution -> as etapadas para processamento, pode ter proximas etapas ou não
- alguns objetos utilizados para configuração:
 - jobrepository -> lida com a persistência e recuperação para os modelos de domínio que envolvem as stepas, tasks e assim por diante
 - jobregistry -> armazenamento central de informações sobre um determinado trabalho e controla o quadro geral sobre todos os works no sistema.
 - taskExecutorJob -> mecanismo para iniciar jobs em lote.


# JMX com spring
- o java management extensions é uma tenclogia para gerenciar e monitorar recursos do sistema. Esses recursos são representados como beans gerenciados.
- podemos exportar Mbeans para o jmx, afim dele executar operações, por exemplo:
 - com a anotação @EnableMBeanExport, detecta todos os beans que possuem a anotação @ManagedResource
 - apos detectar, estes apareceram no servidor jmx e seus métodos podem ser chamados.
 - abaixo um exemplo de uma classe onde o método replicate() aparecerá no servidor jmx(jconsole por ex)

```
@ManagedResource
public class JMXFileReplicator implements FileReplicator {
    private String srcDir;
    private String destDir;
    private FileCopier fileCopier;

    @ManagedAttribute(description = "obter diretorio de origem")
    public String getSrcDir() {
        return srcDir;
    }

    @Override
    @ManagedOperation(description = "replicar arquivos")
    public synchronized void replicate() throws IOException {
        var files = Path.of(srcDir);
        try (var fileList = Files.list(files)) {
            fileList.filter(Files::isRegularFile)
                    .forEach(it -> fileCopier.copyFile(it, Path.of(destDir)));
        }
    }
}
```
- podemos tambem enviar notificações ao servidor jmx e tambem ouvir
- para enviar devemos implementar a interface NotificationPublisherAware
- para ouvir as mensagens, devemos implementar o NotificationListener, registrar no mbeanexporter e filtrar o tipo de bean que queremos

### JFR (java flight recorder)
- ferramenta para registrar informações de diagnóstico e criação de perfil sobre um aplicativo em execução em uma jvm
- para iniciar a gravação dos eventos no jfr, temos algumas opções, entre elas o argumento:
```
java -XX:StartFlightRecording:filename=recording.jfr,duration=30s -jar app.jar
```

### Metricas
- são estatísticas sobre um comportamento do tempo de execução do seu app, por exemplo: medidores, contadores e cronômetros.

#### mais alguns conceitos relacionados a metricas
- observação: e a gravação real de algo que está acontecendo em seu app (quem trata e um ObservationHandler)
  cada obs tem uma implementação de observation context, que contém todos os metadas relevantes. Por ex: no caso de solicitação http terá os métodos http, status s etc.
- keyvalues: são fornecedios para uma implementação observationConvention, que está vinculada a uma implementação específica de observation context.
  key values são chamados de baixa cardinalidade, quando há um número baixo e limitado de valores possíveis (como verbo http)
  key values são chamados de alta cardinalidade, quando há um valor alto, como url por ex.