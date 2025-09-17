# Implementação de Tabela Hash em Java

## Descrição

Este projeto é uma implementação de uma Tabela Hash (Hash Table) a partir do zero em Java. A estrutura de dados utiliza tratamento de colisões por encadeamento, onde cada bucket da tabela é uma lista encadeada.

O objetivo principal é demonstrar o funcionamento de uma tabela hash, incluindo a função de hash, inserção, busca, remoção de elementos e o tratamento de colisões.

## Funcionalidades

-   **Tabela Hash Genérica**: Implementação com buckets e função de hash.
-   **Tratamento de Colisões**: Utiliza encadeamento com listas encadeadas (`LinkedList`).
-   **Operações Básicas**:
    -   `insert(key, value)`: Insere ou atualiza um par chave-valor.
    -   `search(key)`: Busca um valor associado a uma chave.
    -   `remove(key)`: Remove um par chave-valor.
-   **Validações**:
    -   Chaves devem ter entre 10 e 25 caracteres.
    -   Valores devem ter entre 40 e 100 caracteres.
-   **Estatísticas**: Exibe informações como:
    -   Total de elementos.
    -   Número de buckets vazios.
    -   Tamanho da maior cadeia (pior caso de colisão).
    -   Fator de carga da tabela.
-   **Demonstração**: Inclui uma classe principal (`HashTableMain`) que demonstra todas as funcionalidades.

## Como Executar

Este projeto foi configurado para ser executado em um ambiente Nix, que garante que todas as dependências estejam disponíveis.

### Pré-requisitos

-   [Nix](https://nixos.org/download.html) instalado em seu sistema.

### Passos

1.  **Inicialize o Ambiente Nix**:
    Abra o terminal na raiz do projeto e execute:
    ```bash
    nix-shell
    ```
    Este comando irá baixar e configurar todas as dependências definidas no arquivo `shell.nix`.

2.  **Compile o Código**:
    Dentro do `nix-shell`, use o alias `compile`:
    ```bash
    compile
    ```
    Isso irá compilar os arquivos `.java` do diretório `src` e colocar os `.class` no diretório `bin`.

3.  **Execute a Demonstração**:
    Use o alias `run` para executar o programa principal:
    ```bash
    run
    ```
    A saída mostrará a inserção de elementos, o conteúdo da tabela, estatísticas, buscas e remoções.

### Comandos Adicionais

-   **Limpar Arquivos Compilados**:
    ```bash
    clean
    ```
-   **Gerar Relatório (se `typst` estiver configurado)**:
    ```bash
    report
    ```

## Estrutura do Projeto

```
/
├── src/
│   └── HashTableMain.java  # Contém a implementação e a demonstração
├── bin/
│   └── *.class             # Arquivos Java compilados
├── docs/
│   ├── relatorio.typ       # Código-fonte do relatório em Typst
│   └── relatorio.pdf       # Relatório gerado
├── shell.nix               # Arquivo de configuração do ambiente Nix
└── README.md               # Este arquivo
```

## Demonstração

O método `main` em `HashTableMain.java` realiza os seguintes passos:
1.  Insere 11 pares chave-valor na tabela, demonstrando a distribuição e o tratamento de colisões.
2.  Exibe o conteúdo completo da tabela e suas estatísticas.
3.  Busca por chaves existentes e inexistentes.
4.  Remove alguns elementos e exibe novamente o estado da tabela e as estatísticas.
5.  Testa as validações de tamanho para chaves e valores.