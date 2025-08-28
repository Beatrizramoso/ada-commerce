import java.time.LocalDate;
import java.util.*;

public class Cliente {
    private final UUID id;
    private String nome;
    private String documento;
    private String email;

    public Cliente(String nome, String documento, String email) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.documento = documento;
        this.email = email;
    }
}
