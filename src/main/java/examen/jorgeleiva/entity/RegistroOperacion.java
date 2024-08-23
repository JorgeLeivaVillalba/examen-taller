package examen.jorgeleiva.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

//Entidad que representa el registro de una operacion a la BD
@Entity //entidad JPA
@Table(name = "registro_operacion")
public class RegistroOperacion extends PanacheEntity {

    // Enumeración para los tipos de figuras geométricas soportadas
    public enum FiguraGeometrica {
        CUADRADO, CIRCULO, RECTANGULO, TRIANGULO
    }

    // Enumeración para los tipos de operaciones soportadas
    public enum TipoOperacion {
        AREA, PERIMETRO
    }

    @Column(nullable = false) //campo requerido
    @Enumerated(EnumType.STRING) // Almacena los datos como String
    public FiguraGeometrica figura;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public TipoOperacion operacion;

    @Column(nullable = false)
    public double resultado;


    public RegistroOperacion() {
        // Constructor por defecto requerido por el JPA
    }

    public RegistroOperacion(FiguraGeometrica figura, TipoOperacion operacion, double resultado) {
        this.figura = figura;
        this.operacion = operacion;
        this.resultado = resultado;
    }
}
