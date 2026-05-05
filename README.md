Theo Cafeteria — Teste Técnico Gototem

Aplicativo Android que simula um fluxo simples de pedido em um totem de autoatendimento.

O usuário pode navegar entre produtos, adicionar itens ao carrinho, visualizar o total e finalizar o pedido.

Funcionalidades implementadas:

Tela inicial com botão "Iniciar pedido"
Lista de produtos por categoria
Adição de itens ao carrinho
Remoção e ajuste de quantidade (+ / -)
Exibição do valor total
Tela de checkout com resumo do pedido
Finalização com mensagem de sucesso
Como rodar o projeto:

Clonar o repositório: https://github.com/gabrielMachado-design/TheoCafeteria/tree/master
Abrir no Android Studio
Sincronizar o Gradle
Executar em um emulador ou dispositivo físico
Decisões técnicas:

Utilização do padrão MVVM para separação de responsabilidades
Uso de StateFlow para gerenciamento de estado reativo
Implementação de um Repository Singleton para manter o estado do carrinho entre telas
Uso de Hilt para injeção de dependência
Reutilização de um único adapter para carrinho e checkout, reduzindo duplicação de código
Uso de extension function para formatação de moeda (toCurrency)
Organização da UI por feature (cart, product, checkout)
Gerenciamento de estado:

O estado do carrinho é centralizado no CartRepository, permitindo que todas as telas (produtos, carrinho e checkout) compartilhem os mesmos dados em tempo real.

O que faria diferente ou melhoraria:

Persistência de dados (ex: Room)
Testes unitários
Melhorias de UI/UX e animações
Implementação de combos personalizados
Melhor tratamento de estados (loading, erro)
