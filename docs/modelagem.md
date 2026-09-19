# Modelo Original

## Prontuario

### Atributos

| Atributo         | Tipo   |
| ---------------- | ------ |
| diagnostico      | String |
| observacoes      | String |
| sintomas         | String |
| historico medico | String |

### Métodos

| Método                   | Retorno |
| ------------------------ | ------- |
| `registrarInformacoes()` | void    |
| `atualizarProntuario()`  | void    |
| `consultarProntuario()`  | void    |
| `adicionarDiagnostico()` | void    |

### Observações

* `sintomas` está definido como `String`, embora represente uma informação que pode conter múltiplos sintomas. É necessário definir se será armazenado como um único texto ou como uma coleção de sintomas.
* `historico medico` está definido como `String`, mas é necessário esclarecer o que exatamente representa esse histórico. Caso represente o histórico de consultas do paciente, uma estrutura como `List<Consulta>` pode representar melhor essa relação.
* `registrarInformacoes()` é muito genérico. Não está definido quais informações serão registradas nem onde essa operação será realizada.
* `atualizarProntuario()` é ambíguo, pois pode significar alteração dos dados do prontuário ou atualização dos dados persistidos no banco de dados.
* `consultarProntuario()` parece representar uma operação de consulta/recuperação de dados, o que pode ser responsabilidade de um serviço ou repositório, dependendo da arquitetura adotada.
* `adicionarDiagnostico()` possui uma responsabilidade mais específica e pode fazer sentido como comportamento do próprio `Prontuario`, caso o prontuário seja responsável por manter seus diagnósticos.

---

## Medico

### Atributos

| Atributo      | Tipo   |
| ------------- | ------ |
| nome          | String |
| crm           | String |
| especialidade | String |
| telefone      | String |
| email         | String |

### Métodos

| Método                  | Retorno |
| ----------------------- | ------- |
| `cadastrar()`           | void    |
| `atualizarDados()`      | void    |
| `consultarAgenda()`     | void    |
| `realizarConsulta()`    | void    |
| `registrarProntuario()` | void    |
| `emitirPrescricao()`    | void    |

### Observações

* `cadastrar()` apresenta uma dúvida de responsabilidade: o objeto `Medico` está cadastrando a si próprio? Em uma arquitetura separada em camadas, o cadastro tende a ser uma operação coordenada por um serviço, utilizando um repositório para persistência.
* `atualizarDados()` possui uma ambiguidade semelhante. É necessário definir se representa apenas a alteração dos atributos do objeto ou uma operação de atualização no banco de dados.
* `consultarAgenda()` pode representar uma consulta de dados relacionados ao médico. É necessário definir de onde a agenda será obtida e se essa operação pertence ao próprio `Medico` ou a um serviço.
* `realizarConsulta()` é ambíguo. "Realizar" pode significar iniciar a consulta, finalizá-la, alterar seu status, registrar informações ou realizar várias dessas operações.
* `registrarProntuario()` também precisa de uma definição mais precisa. Não está claro se o método cria um prontuário, registra informações em um prontuário existente ou apenas associa um prontuário ao paciente/consulta.
* `emitirPrescricao()` representa uma ação realizada pelo médico no contexto do sistema, mas é necessário definir qual objeto representa a prescrição e qual será a responsabilidade de cada classe nessa operação.

---

## Paciente

### Atributos

| Atributo       | Tipo   |
| -------------- | ------ |
| nome           | String |
| cpf            | String |
| dataNascimento | Date   |
| telefone       | String |
| email          | String |

### Métodos

| Método                 | Retorno |
| ---------------------- | ------- |
| `cadastrar()`          | void    |
| `atualizarDados()`     | void    |
| `agendarConsulta()`    | void    |
| `cancelarConsulta()`   | void    |
| `consultarHistorico()` | void    |

### Observações

* `cadastrar()` apresenta a mesma questão encontrada em `Medico.cadastrar()`: o paciente está sendo responsável pelo próprio cadastro ou o cadastro é uma operação do sistema?
* `atualizarDados()` precisa esclarecer se representa apenas a alteração dos dados do objeto ou também a persistência dessas alterações.
* `agendarConsulta()` representa uma ação realizada pelo paciente, mas o agendamento envolve outros elementos, como `Paciente`, `Medico` e `Consulta`. Por isso, pode ser mais adequado que a operação seja coordenada por um serviço.
* `cancelarConsulta()` possui uma questão semelhante: é necessário identificar qual consulta será cancelada e quais regras devem ser verificadas antes do cancelamento.
* `consultarHistorico()` parece representar uma operação de consulta de dados. É necessário definir como o histórico será obtido e onde essa responsabilidade ficará na arquitetura.
* De forma geral, os métodos de `Paciente` parecem representar principalmente **casos de uso realizados pelo paciente**, e não necessariamente comportamentos internos que pertencem à entidade `Paciente`.

---

## Consulta

### Atributos

| Atributo | Tipo   |
| -------- | ------ |
| data     | Date   |
| horario  | Time   |
| status   | String |
| motivo   | String |

### Métodos

| Método            | Retorno |
| ----------------- | ------- |
| `agendar()`       | void    |
| `cancelar()`      | void    |
| `reagendar()`     | void    |
| `realizar()`      | void    |
| `alterarStatus()` | void    |

### Observações

* `agendar()` pode fazer sentido como comportamento da própria `Consulta` caso uma consulta já existente esteja passando para um estado de agendada. Porém, se o agendamento for responsável por **criar uma nova consulta**, a operação pode ser mais adequada em um serviço.
* `cancelar()` representa uma mudança de estado da consulta e pode fazer sentido como comportamento da entidade, desde que existam regras para determinar quando uma consulta pode ser cancelada.
* `reagendar()` precisa definir o que será alterado: apenas `data` e `horario` ou também outras informações relacionadas ao agendamento.
* `realizar()` é ambíguo. Não está claro se significa iniciar a consulta, finalizar a consulta ou simplesmente alterar o status para algum estado específico.
* `alterarStatus()` é mais explícito quanto à intenção, mas também é necessário definir quais estados são permitidos e quais transições podem ocorrer.
* Existe uma possível sobreposição entre `realizar()` e `alterarStatus()`. Caso "realizar" apenas altere o status da consulta, pode haver duas operações representando a mesma mudança de estado.
* Os possíveis estados de `Consulta` também precisam ser definidos. Atualmente `status` é apenas um `String`, sem indicar quais valores são válidos.

---

# Problemas e pontos gerais encontrados

## 1. Mistura entre ações do usuário e responsabilidades das classes

Vários métodos parecem ter sido definidos a partir da pergunta:

> "O que o paciente/médico faz no sistema?"

em vez de:

> "Qual é a responsabilidade desta classe dentro do software?"

Por exemplo:

```text
Paciente → agendarConsulta()
Medico   → realizarConsulta()
Paciente → cancelarConsulta()
Medico   → registrarProntuario()
```

Essas ações fazem sentido quando descrevemos os **casos de uso do sistema**, mas isso não significa necessariamente que devam ser métodos diretamente nas respectivas entidades.

Uma alternativa seria separar:

```text
Paciente
   └── dados e comportamentos próprios

Consulta
   └── estado e regras da consulta

Prontuario
   └── informações e regras do prontuário

Services
   └── coordenação dos casos de uso

Repositories
   └── persistência e recuperação dos dados
```

---

## 2. Métodos excessivamente genéricos

Alguns nomes não deixam claro o que realmente acontece quando o método é chamado.

Exemplos:

* `registrarInformacoes()`
* `atualizarProntuario()`
* `realizarConsulta()`
* `realizar()`
* `consultarProntuario()`

Para cada um desses métodos, é necessário conseguir responder:

* O que exatamente ele faz?
* Quais dados recebe?
* Quais dados altera?
* Qual objeto é afetado?
* Ele altera o estado de uma entidade?
* Ele acessa o banco de dados?
* Ele apenas coordena outras operações?

Se essas perguntas não puderem ser respondidas, o método ainda não possui uma responsabilidade suficientemente definida.

---

## 3. Tipos de dados pouco definidos

Alguns atributos utilizam tipos que podem não representar adequadamente o domínio.

### `sintomas`

Atualmente:

```java
String sintomas;
```

Porém, um paciente pode apresentar vários sintomas.

Uma possibilidade seria:

```java
List<String> sintomas;
```

A decisão final depende de como os sintomas serão tratados no sistema.

### `historicoMedico`

Atualmente:

```java
String historicoMedico;
```

É necessário definir o significado desse atributo.

Se o histórico representar um conjunto de consultas, por exemplo:

```java
List<Consulta> historicoMedico;
```

pode representar melhor o relacionamento entre os dados.

Também é possível que o histórico não precise ser armazenado diretamente em `Prontuario`, podendo ser obtido a partir das consultas relacionadas ao paciente.

---

## 4. Persistência e regras de negócio estão misturadas

Alguns métodos podem estar confundindo três responsabilidades diferentes:

```text
alterar dados
     ↓
aplicar regras de negócio
     ↓
salvar/recuperar no banco
```

Essas operações podem ser separadas.

Uma possível organização seria:

```text
Service
   ↓
coordena o caso de uso
   ↓
Model
   ↓
representa os dados e regras próprias

Repository
   ↓
salva e recupera dados
   ↓
Banco de dados
```

Dessa forma, uma entidade como `Paciente` não precisaria necessariamente possuir métodos como `salvarNoBanco()` ou `consultarBanco()`.

---

# Questões que precisam ser definidas

Antes de considerar o modelo finalizado, algumas decisões precisam ser discutidas pelo grupo:

1. O que exatamente significa cada método?
2. Quais métodos representam comportamentos das entidades e quais representam casos de uso?
3. Quais operações serão responsabilidade dos `Services`?
4. Quais operações serão responsabilidade dos `Repositories`?
5. Como será representado o histórico médico?
6. Um paciente pode possuir várias consultas?
7. Uma consulta possui um paciente e um médico?
8. Quais são os estados possíveis de uma consulta?
9. Quais mudanças de estado são permitidas?
10. O prontuário pertence ao paciente, à consulta ou possui relação com ambos?
11. O que exatamente representa uma prescrição?
12. Os sintomas serão armazenados individualmente ou como texto?
13. Quais dados realmente precisam ser persistidos no banco?
14. Quais dados podem ser obtidos por meio de relacionamentos entre entidades?

---

# Modelo proposto

A definição do modelo proposto deve ser feita após as questões acima serem discutidas pelo grupo. O objetivo não é simplesmente alterar os métodos do diagrama original, mas definir claramente a responsabilidade de cada classe e separar:

* entidades/modelos;
* regras de negócio;
* casos de uso;
* persistência;
* apresentação/interface.
