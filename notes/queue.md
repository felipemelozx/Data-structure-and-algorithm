# Queue

A **Queue** (ou fila) é uma estrutura de dados que segue o princípio **FIFO** (First In, First Out), ou seja, o primeiro elemento a entrar é o primeiro a sair, assim como uma fila na vida real. Ela é amplamente utilizada em diversas aplicações, como:

- **Processamento de tarefas**: Sistemas que gerenciam envio de e-mails ou processamento de pedidos.
- **Filas de impressão**: Documentos são organizados em fila e impressos na ordem de chegada.
- **Controle de acessos simultâneos**: Limita o número de requisições atendidas por um servidor, organizando as demais em fila.
- **Mensageria assíncrona**: Em sistemas distribuídos, filas (como RabbitMQ ou Kafka) permitem comunicação desacoplada entre componentes. Por exemplo, ao realizar uma compra, uma mensagem é colocada na fila, e serviços como envio de e-mails ou faturamento a consomem assincronamente, melhorando escalabilidade e desempenho.

## Tipos de Queue em Java

A `Queue` em Java é uma **interface**, não podendo ser instanciada diretamente. Para utilizá-la, existem implementações específicas, cada uma com características próprias:

### 1. `LinkedList` (Fila FIFO)
A `LinkedList` pode ser usada como uma implementação de `Queue`, pois permite adicionar e remover elementos de forma eficiente nas extremidades.

- **Como usar**: Utilize `add()` para inserir elementos no final da fila e `poll()` para remover do início, respeitando o princípio FIFO.
- **Vantagens**:
  - Inserção e remoção nas extremidades têm complexidade **O(1)**.
  - Baseada em lista encadeada, é flexível para manipulações.
- **Desvantagens**:
  - **Não é thread-safe**, exigindo sincronização adicional em ambientes concorrentes.
  - Menos eficiente em termos de memória comparada a outras opções.

### 2. `PriorityQueue` (Fila com Prioridade)
A `PriorityQueue` também implementa a interface `Queue`, mas organiza os elementos por **prioridade**, definida por um `Comparator` ou pelo método `compareTo()`, em vez de seguir a ordem de inserção.

- **Como usar**: Similar à `Queue`, mas os elementos são retirados com base na prioridade (menor ou maior valor, conforme definido).
- **Vantagens**:
  - Ideal para cenários que exigem priorização, como agendamento de tarefas ou filas com níveis de urgência.
- **Desvantagens**:
  - Não segue o FIFO estritamente, pois a ordem depende da prioridade.
  - Operações `add()` e `poll()` têm complexidade **O(log n)** devido à reordenação interna.

### 3. `ArrayDeque` (Fila de Alto Desempenho)
A `ArrayDeque` é uma implementação de `Queue` baseada em um array dinâmico, oferecendo maior eficiência em comparação com a `LinkedList` em muitos casos.

- **Como usar**: Usa `add()` para inserir no final e `poll()` para remover do início, similar à `LinkedList`.
- **Vantagens**:
  - Melhor desempenho em memória e tempo de execução, com operações **O(1)** para inserção e remoção nas extremidades.
  - Mais eficiente que `LinkedList` devido à ausência de referências de lista encadeada.
- **Desvantagens**:
  - Não é thread-safe, requerendo sincronização em ambientes concorrentes.

### 4. `ConcurrentLinkedQueue` (Fila Thread-Safe)
A `ConcurrentLinkedQueue` é uma implementação **thread-safe** e **não bloqueante**, ideal para ambientes concorrentes onde múltiplas threads acessam a fila simultaneamente.

- **Como usar**: Similar às demais, usa `add()` e `poll()`, mas pode ser compartilhada entre threads sem sincronização explícita.
- **Vantagens**:
  - Perfeita para sistemas multithreaded, com acesso eficiente e sem bloqueios.
  - Baseada em lista encadeada lock-free, garantindo segurança e escalabilidade.
- **Desvantagens**:
  - Maior complexidade e possível custo de desempenho em cenários de baixa concorrência, onde implementações mais simples seriam suficientes.

## Fila em Sistemas Reais com RabbitMQ (Exemplo com Spring Boot)

Em sistemas distribuídos, filas de mensagens como **RabbitMQ** são usadas para desacoplar componentes e garantir comunicação assíncrona. O fluxo básico é:

- **Produtor**: Envia mensagens para a fila (ex.: sistema que registra pedidos).
- **Fila**: Armazena mensagens até serem processadas.
- **Consumidor**: Processa as mensagens da fila (ex.: serviço que envia e-mails).

Esse modelo aumenta a escalabilidade e resiliência, pois produtores e consumidores operam de forma independente.

### Exemplo com Spring Boot e RabbitMQ

#### Dependências (`pom.xml`):

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```

## Configuração do RabbitMQ

```java
@Configuration
public class RabbitConfig {

    @Bean
    public Queue queue() {
        return new Queue("minhaFila", false);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("minhaExchange");
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("minhaRoutingKey");
    }
}
```
## Produtor

```java
@Service
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend("minhaExchange", "minhaRoutingKey", message);
    }
}
```
**Consumidor:**

```java
@Service
public class Consumer {

    @RabbitListener(queues = "minhaFila")
    public void receiveMessage(String message) {
        System.out.println("Mensagem recebida: " + message);
    }
}
```

**Fila vs. Lista**

### Fila (Queue)

*   Siga o princípio FIFO (First In, First Out)
*   Ideal para cenários onde a ordem de chegada é importante (ex.: envio de e-mails)

### Lista (List)

*   Permite acesso e remoção em qualquer posição
*   Útil para casos como carrinhos de compras, onde a ordem de remoção é irrelevante

**Por que não usar List como fila?**

*   Desempenho: Remover o primeiro elemento de uma List (remove(0)) tem complexidade O(n), enquanto em uma Queue (como LinkedList ou 
ArrayDeque) é O(1).
*   Semântica: A Queue garante o comportamento FIFO, enquanto a List não.

**Queue vs. Stack**

### Fila (Queue)

*   FIFO (First In, First Out)
*   Ideal para processos que respeitam a ordem de chegada (ex.: fila de impressão)

### Pilha (Stack)

*   LIFO (Last In, First Out)
*   Útil para processos que priorizam o último elemento (ex.: histórico de navegação)

**Quando usar cada uma?**

*   Queue: Quando a ordem de chegada é importante (ex.: atendimento de clientes).
*   Stack: Quando a ordem inversa é necessária (ex.: funções recursivas).

**Desempenho e Complexidade**

### LinkedList e ArrayDeque

*   add() e poll(): Complexidade O(1)
*   Conclusão: Ideais para filas simples com alta eficiência em inserções e remoções.

### PriorityQueue

*   add() e poll(): Complexidade O(log n) devido à reordenação por prioridade.
*   Conclusão: Útil para filas com priorização, mas menos eficiente que LinkedList ou ArrayDeque.

### ConcurrentLinkedQueue

*   add() e poll(): Complexidade O(1) em média, mas com overhead em ambientes concorrentes.
*   Conclusão: Excelente para sistemas multithreaded, mas pode ser excessiva em cenários simples.

**Stack (exemplo com LinkedList)**

*   push() e pop(): Complexidade O(1).
*   Conclusão: Eficiente, mas usada para LIFO, não FIFO.
