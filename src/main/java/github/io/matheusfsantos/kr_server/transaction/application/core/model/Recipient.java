package github.io.matheusfsantos.kr_server.transaction.application.core.model;

public record Recipient(
    String name,
    String document,
    String bank,
    String agency,
    String account
) { }
