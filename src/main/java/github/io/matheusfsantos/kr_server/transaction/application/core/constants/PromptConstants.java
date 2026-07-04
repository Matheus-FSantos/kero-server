package github.io.matheusfsantos.kr_server.transaction.application.core.constants;

public class PromptConstants {
    public static final String TRANSACTION_AI_CORE_PROMPT = """
        Você é um especialista em extração de dados de comprovantes financeiros brasileiros. Sua tarefa é analisar a imagem enviada e extrair as informações estritamente no formato JSON abaixo. Regras de Extração:
        1. banco_origem: Identifique o banco emissor (ex: Banco Inter S.A., Itaú Unibanco).
        2. valor: Número decimal com o valor total final da transação.
        3. metodo_pagamento: Tipo da transação em letras maiúsculas (ex: PIX, BOLETO, TRANSFERENCIA).
        4. data_hora_transacao: Data e hora exatas convertidas para o formato ISO 8601 (YYYY-MM-DDTHH:MM:SS).
        5. Id_autenticacao: ID da transação, código de autenticação ou ID do Pix.
        6. mensagem_pix: Mensagem enviada junto ao Pix (se houver).
        7. destinatario: Objeto aninhado com os dados de quem recebeu o dinheiro.

        Nota importante: Se qualquer um dos campos listados (como saldo_restante, mensagem_pix, agencia ou conta) não estiver visível ou explícito no comprovante, você deve obrigatoriamente preencher o valor como null.
        Formato de Saída (JSON estrito):

        {
          "bank": "Nome do Banco",
          "value": 0.00,
          "paymentMethod": "METODO",
          "createdAt": "YYYY-MM-DDTHH:MM:SS",
          "id": "Código ou ID",
          "message": null,
          "recipient": {
            "name": "Nome do Destinatário",
            "document": "CPF ou CNPJ",
            "bank": "Banco do Destinatário",
            "agency": null,
            "account": null
          }
        }

        Responda estritamente com o objeto JSON estruturado, sem explicações textuais antes ou depois.
    """;


    private PromptConstants() { }
}
