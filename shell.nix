{ pkgs ? import <nixpkgs> {} }:

pkgs.mkShell {
  name = "hashtable-java-env";
  
  buildInputs = with pkgs; [
    # Java Development Kit
    jdk17                    # Java 17 LTS (versão estável e amplamente suportada)
    
    # Development Tools
    git                      # Controle de versão
    gnumake                  # Para Makefiles se necessário
    
    # Documentation and Report Generation
    typst                    # Para gerar o relatório em PDF
    
    # Utilities
    tree                     # Visualizar estrutura de diretórios
    which                    # Encontrar executáveis
    file                     # Identificar tipos de arquivo
    unzip                    # Descompactar arquivos
    curl                     # Download de arquivos
    wget                     # Download de arquivos (alternativa)
  ];

  # Variáveis de ambiente
  shellHook = ''
    echo "🚀 Ambiente Java para Hash Table inicializado!"
    
    # Configurar JAVA_HOME se necessário
    export JAVA_HOME="${pkgs.jdk17}/lib/openjdk"
    
    # Adicionar diretório bin ao PATH se existir
    if [ -d "./bin" ]; then
      export PATH="./bin:$PATH"
    fi
    
    # Aliases úteis
    alias compile="javac -d bin src/*.java"
    alias run="java -cp bin HashTableMain"
    alias clean="rm -rf bin/*.class"
    alias report="typst compile docs/relatorio.typ && echo 'Relatório gerado: docs/relatorio.pdf'"
    
    echo "Aliases disponíveis:"
    echo "   • compile  - Compila o código Java"
    echo "   • run      - Executa o programa principal"
    echo "   • clean    - Remove arquivos compilados"
    echo "   • report   - Gera relatório PDF"
    echo ""
    echo "Compilando e executando:"
    echo "   1. Execute: compile"
    echo "   2. Execute: run"
    echo ""
  '';

  # Configurações específicas do Java
  JAVA_OPTS = "-Xmx512m -Xms256m";
}
