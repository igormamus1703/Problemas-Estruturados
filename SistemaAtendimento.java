
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
 public class SistemaAtendimento {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pilha historicoSolicitacoes = new Pilha();
        Fila filaAtendimento = new Fila();

        // Inicializando a fila de atendimento com os dados fornecidos
        Cliente[] clientes = {
            new Cliente("CLI001", "Maria Silva", "Dúvida sobre produto"),
            new Cliente("CLI002", "João Souza", "Reclamação de serviço"),
            new Cliente("CLI003", "Ana Costa", "Solicitação de reembolso"),
            new Cliente("CLI004", "Pedro Alves", "Informações de entrega"),
            new Cliente("CLI005", "Carla Dias", "Agendamento de visita"),
            new Cliente("CLI006", "Lucas Martins", "Alteração de pedido"),
            new Cliente("CLI007", "Patrícia Rocha", "Cancelamento de contrato"),
            new Cliente("CLI008", "Rafael Lima", "Renovação de assinatura"),
            new Cliente("CLI009", "Fernanda Gomes", "Suporte para instalação"),
            new Cliente("CLI010", "Carlos Eduardo", "Pedido de orçamento")
        };

        for (Cliente cliente : clientes) {
            filaAtendimento.enqueue(cliente);
        }

        int opcao;
        do {
            System.out.println("Sistema de Gerenciamento de Atendimento ao Cliente");
            System.out.println("1. Adicionar solicitação ao histórico");
            System.out.println("2. Remover solicitação do histórico");
            System.out.println("3. Exibir histórico de solicitações");
            System.out.println("4. Adicionar cliente à fila de atendimento");
            System.out.println("5. Atender próximo cliente");
            System.out.println("6. Exibir fila de atendimento");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    System.out.print("Digite o ID da solicitação: ");
                    String idSolicitacao = scanner.nextLine();
                    System.out.print("Digite a descrição da solicitação: ");
                    String descricaoSolicitacao = scanner.nextLine();
                    String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
                    historicoSolicitacoes.push(new Solicitacao(idSolicitacao, descricaoSolicitacao, dataHora));
                    break;
                case 2:
                    Solicitacao solicitacaoRemovida = historicoSolicitacoes.pop();
                    if (solicitacaoRemovida != null) {
                        System.out.println("Solicitação removida: " + solicitacaoRemovida);
                    }
                    break;
                case 3:
                    System.out.println("Histórico de solicitações:");
                    historicoSolicitacoes.mostrarHistorico();
                    break;
                case 4:
                    System.out.print("Digite o ID do cliente: ");
                    String idCliente = scanner.nextLine();
                    System.out.print("Digite o nome do cliente: ");
                    String nomeCliente = scanner.nextLine();
                    System.out.print("Digite o motivo do atendimento: ");
                    String motivoAtendimento = scanner.nextLine();
                    filaAtendimento.enqueue(new Cliente(idCliente, nomeCliente, motivoAtendimento));
                    break;
                case 5:
                    Cliente clienteAtendido = filaAtendimento.dequeue();
                    if (clienteAtendido != null) {
                        System.out.println("Cliente atendido: " + clienteAtendido);
                    }
                    break;
                case 6:
                    System.out.println("Fila de atendimento:");
                    filaAtendimento.mostrarFila();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 0);

        scanner.close();
    }

    
}
