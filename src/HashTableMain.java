class Node {
    private String key;
    private String value;
    private Node next;
    
    public Node(String key, String value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
    
    // Getters e Setters
    public String getKey() { return key; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
    public Node getNext() { return next; }
    public void setNext(Node next) { this.next = next; }
}

class LinkedList {
    private Node head;
    
    public LinkedList() {
        this.head = null;
    }
    
    public void insert(String key, String value) {
        Node current = head;
        
        while (current != null) {
            if (current.getKey().equals(key)) {
                current.setValue(value); // Atualizar valor existente
                return;
            }
            current = current.getNext();
        }
        
        Node newNode = new Node(key, value);
        newNode.setNext(head);
        head = newNode;
    }
    
    public String search(String key) {
        Node current = head;
        while (current != null) {
            if (current.getKey().equals(key)) {
                return current.getValue();
            }
            current = current.getNext();
        }
        return null; // Chave não encontrada
    }
    
    public boolean remove(String key) {
        if (head == null) return false;
        
        if (head.getKey().equals(key)) {
            head = head.getNext();
            return true;
        }
        
        Node current = head;
        while (current.getNext() != null) {
            if (current.getNext().getKey().equals(key)) {
                current.setNext(current.getNext().getNext());
                return true;
            }
            current = current.getNext();
        }
        return false; // Chave não encontrada
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public String getElements() {
        if (head == null) return "Vazio";
        
        StringBuilder sb = new StringBuilder();
        Node current = head;
        while (current != null) {
            sb.append("[").append(current.getKey()).append(" -> ")
              .append(current.getValue()).append("]");
            if (current.getNext() != null) {
                sb.append(" -> ");
            }
            current = current.getNext();
        }
        return sb.toString();
    }
}

class HashTable {
    private LinkedList[] buckets;
    private int numBuckets;
    
    public HashTable(int size) {
        this.numBuckets = size;
        this.buckets = new LinkedList[size];
        
        for (int i = 0; i < size; i++) {
            buckets[i] = new LinkedList();
        }
    }
    
    private int hash(String key) {
        int sum = 0;
        for (char c : key.toCharArray()) {
            sum += (int) c;
        }
        return sum % numBuckets;
    }
    
    private boolean isValidKey(String key) {
        return key != null && key.length() >= 10 && key.length() <= 25;
    }
    
    private boolean isValidValue(String value) {
        return value != null && value.length() >= 40 && value.length() <= 100;
    }
    
    public boolean insert(String key, String value) {
        if (!isValidKey(key)) {
            System.out.println("Erro: Chave deve ter entre 10 e 25 caracteres.");
            return false;
        }
        if (!isValidValue(value)) {
            System.out.println("Erro: Valor deve ter entre 40 e 100 caracteres.");
            return false;
        }
        
        int index = hash(key);
        buckets[index].insert(key, value);
        System.out.println("Inserido: [" + key + "] no bucket " + index);
        return true;
    }
    
    public String search(String key) {
        if (!isValidKey(key)) {
            System.out.println("Erro: Chave deve ter entre 10 e 25 caracteres.");
            return null;
        }
        
        int index = hash(key);
        return buckets[index].search(key);
    }
    
    public boolean remove(String key) {
        if (!isValidKey(key)) {
            System.out.println("Erro: Chave deve ter entre 10 e 25 caracteres.");
            return false;
        }
        
        int index = hash(key);
        boolean removed = buckets[index].remove(key);
        if (removed) {
            System.out.println("Removido: [" + key + "] do bucket " + index);
        } else {
            System.out.println("Chave não encontrada: [" + key + "]");
        }
        return removed;
    }
    
    public void display() {
        System.out.println("\n=== CONTEÚDO DA TABELA HASH ===");
        for (int i = 0; i < numBuckets; i++) {
            System.out.println("Bucket " + i + ": " + buckets[i].getElements());
        }
        System.out.println("================================\n");
    }
    
    public void showStats() {
        int totalElements = 0;
        int emptyBuckets = 0;
        int maxChainLength = 0;
        
        for (int i = 0; i < numBuckets; i++) {
            if (buckets[i].isEmpty()) {
                emptyBuckets++;
            } else {
                String elements = buckets[i].getElements();
                int chainLength = elements.split(" -> ").length;
                if (chainLength > maxChainLength) {
                    maxChainLength = chainLength;
                }
                totalElements += chainLength;
            }
        }
        
        System.out.println("=== ESTATÍSTICAS ===");
        System.out.println("Total de elementos: " + totalElements);
        System.out.println("Buckets vazios: " + emptyBuckets + "/" + numBuckets);
        System.out.println("Maior cadeia: " + maxChainLength + " elementos");
        System.out.println("Fator de carga: " + String.format("%.2f", (double)totalElements/numBuckets));
        System.out.println("===================\n");
    }
}

public class HashTableMain {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(7);
        
        System.out.println("=== DEMONSTRAÇÃO DA TABELA HASH ===\n");
        
        String[][] testData = {
            {"chave12345", "Este é um valor de teste com mais de quarenta caracteres para atender aos requisitos"},
            {"abcdefghij", "Outro valor de exemplo que precisa ter pelo menos quarenta caracteres para ser válido"},
            {"teste123ab", "Valor número três com tamanho adequado conforme especificado nos requisitos do exercício"},
            {"hashTable1", "Quarto valor de demonstração que deve conter no mínimo quarenta caracteres válidos"},
            {"linkedList", "Quinto exemplo de valor para testar a funcionalidade da tabela hash implementada"},
            {"javaString", "Sexto valor usado para demonstrar colisões e o funcionamento das listas encadeadas"},
            {"bucketTest", "Sétimo valor de teste para verificar a distribuição nos diferentes buckets da tabela"},
            {"colisaoABC", "Oitavo valor criado especificamente para gerar colisões na função hash implementada"},
            {"estrutura1", "Nono valor de exemplo para completar os testes necessários da implementação"},
            {"finalValue", "Décimo e último valor de teste para demonstrar todas as funcionalidades implementadas"},
            {"extraChave", "Valor adicional para demonstrar mais colisões e testar a robustez da implementação"}
        };
        
        System.out.println("1. INSERINDO ELEMENTOS:\n");
        for (String[] pair : testData) {
            hashTable.insert(pair[0], pair[1]);
        }
        
        hashTable.display();
        hashTable.showStats();
        
        System.out.println("2. BUSCANDO ELEMENTOS:\n");
        String[] searchKeys = {"chave12345", "linkedList", "inexistente", "finalValue"};
        
        for (String key : searchKeys) {
            String result = hashTable.search(key);
            if (result != null) {
                System.out.println("Encontrado [" + key + "]: " + result.substring(0, 30) + "...");
            } else {
                System.out.println("Não encontrado [" + key + "]");
            }
        }
        
        System.out.println("\n3. REMOVENDO ELEMENTOS:\n");
        String[] removeKeys = {"teste123ab", "hashTable1", "inexistente"};
        
        for (String key : removeKeys) {
            hashTable.remove(key);
        }
        
        hashTable.display();
        hashTable.showStats();
        
        System.out.println("4. TESTANDO VALIDAÇÕES:\n");
        
        hashTable.insert("curta", "Este valor tem mais de quarenta caracteres mas a chave é muito pequena");
        hashTable.insert("estaChaveEMuitoLongaParaSerValida", "Este valor tem mais de quarenta caracteres mas a chave é muito longa");
        hashTable.insert("chaveValida", "Valor curto");
        
        StringBuilder longValue = new StringBuilder();
        for (int i = 0; i < 110; i++) {
            longValue.append("x");
        }
        hashTable.insert("outraChave", longValue.toString());
        
        System.out.println("\n=== DEMONSTRAÇÃO CONCLUÍDA ===");
    }
}
