package github.io.matheusfsantos.kr_server.transaction.application.core.model;

public record Transaction(
    String id,
    String bank,
    String value,
    String paymentMethod,
    String createdAt,
    String message,
    Recipient recipient
) { }
