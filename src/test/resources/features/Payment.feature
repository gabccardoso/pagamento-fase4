# language: pt

Funcionalidade: Payment

  Cenario: Criar Pagamento
    Quando registrar um novo pagamento
    Entao o pagamento é registrado com sucesso

  Cenario: Buscar Status do Pagamento
    Dado que já existe um pagamento
    Quando buscar esse pagamento
    Entao o pagamento retorna com sucesso

