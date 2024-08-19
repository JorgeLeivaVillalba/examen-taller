package examen.jorgeleiva.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "registro_operacion")
public class RegistroOperacion extends PanacheEntity {

    public enum FiguraGeometrica {
        CUADRADO, CIRCULO, RECTANGULO, TRIANGULO
    }

    public enum TipoOperacion {
        AREA, PERIMETRO
    }

    @Enumerated(EnumType.STRING)
    public FiguraGeometrica figura;

    @Enumerated(EnumType.STRING)
    public TipoOperacion operacion;

    public double resultado;

    public LocalDateTime fechaHora;

    public RegistroOperacion() {
        // Constructor por defecto
    }

    public RegistroOperacion(FiguraGeometrica figura, TipoOperacion operacion, double resultado) {
        this.figura = figura;
        this.operacion = operacion;
        this.resultado = resultado;
        this.fechaHora = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "RegistroOperacion{" +
                "id=" + id +
                ", figura=" + figura +
                ", operacion=" + operacion +
                ", resultado=" + resultado +
                ", fechaHora=" + fechaHora +
                '}';
    }
}
