package examen.jorgeleiva;

import examen.jorgeleiva.entity.RegistroOperacion;
import examen.jorgeleiva.model.Circulo;
import examen.jorgeleiva.model.Cuadrado;
import examen.jorgeleiva.model.Rectangulo;
import examen.jorgeleiva.model.Triangulo;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType; //importar json
import jakarta.ws.rs.core.Response;

import java.util.Map;

@Path("/geometria")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GeometriaResource {

    // Cuadrado - Área
    @POST
    @Path("/area/cuadrado")
    public Response calcularAreaCuadrado(@QueryParam("lado") double lado) {
        Cuadrado cuadrado = new Cuadrado(lado);
        double area = cuadrado.calcularArea();
        RegistroOperacion registro = new RegistroOperacion(RegistroOperacion.FiguraGeometrica.CUADRADO, RegistroOperacion.TipoOperacion.AREA, area);
        registro.persist();
        return Response.ok(Map.of("data", Map.of("area", area + " u2"))).build();
    }

    // Cuadrado - Perímetro
    @POST
    @Path("/perimetro/cuadrado")
    public Response calcularPerimetroCuadrado(@QueryParam("lado") double lado) {
        Cuadrado cuadrado = new Cuadrado(lado);
        double perimetro = cuadrado.calcularPerimetro();
        return Response.ok(Map.of("data", Map.of("perimetro", perimetro + " u"))).build();
    }

    // Círculo - Área
    @POST
    @Path("/area/circulo")
    public Response calcularAreaCirculo(@QueryParam("radio") double radio) {
        Circulo circulo = new Circulo(radio);
        double area = circulo.calcularArea();
        RegistroOperacion registro = new RegistroOperacion(RegistroOperacion.FiguraGeometrica.CIRCULO, RegistroOperacion.TipoOperacion.AREA, area);
        registro.persist();
        return Response.ok(Map.of("data", Map.of("area", area + " u2"))).build();
    }

    // Círculo - Perímetro
    @POST
    @Path("/perimetro/circulo")
    public Response calcularPerimetroCirculo(@QueryParam("radio") double radio) {
        Circulo circulo = new Circulo(radio);
        double perimetro = circulo.calcularPerimetro();
        return Response.ok(Map.of("data", Map.of("perimetro", perimetro + " u"))).build();
    }

    // Rectángulo - Área
    @POST
    @Path("/area/rectangulo")
    public Response calcularAreaRectangulo(@QueryParam("largo") double largo, @QueryParam("ancho") double ancho) {
        Rectangulo rectangulo = new Rectangulo(largo, ancho);
        double area = rectangulo.calcularArea();
        RegistroOperacion registro = new RegistroOperacion(RegistroOperacion.FiguraGeometrica.RECTANGULO, RegistroOperacion.TipoOperacion.AREA, area);
        registro.persist();
        return Response.ok(Map.of("data", Map.of("area", area + " u2"))).build();
    }

    // Rectángulo - Perímetro
    @POST
    @Path("/perimetro/rectangulo")
    public Response calcularPerimetroRectangulo(@QueryParam("largo") double largo, @QueryParam("ancho") double ancho) {
        Rectangulo rectangulo = new Rectangulo(largo, ancho);
        double perimetro = rectangulo.calcularPerimetro();
        return Response.ok(Map.of("data", Map.of("perimetro", perimetro + " u"))).build();
    }

    // Triángulo - Área
    @POST
    @Path("/area/triangulo")
    public Response calcularAreaTriangulo(@QueryParam("base") double base, @QueryParam("altura") double altura) {
        Triangulo triangulo = new Triangulo(base, altura, 0, 0, 0); // Los lados no son necesarios para el cálculo del área
        double area = triangulo.calcularArea();
        RegistroOperacion registro = new RegistroOperacion(RegistroOperacion.FiguraGeometrica.TRIANGULO, RegistroOperacion.TipoOperacion.AREA, area);
        registro.persist();
        return Response.ok(Map.of("data", Map.of("area", area + " u2"))).build();
    }

    // Triángulo - Perímetro
    @POST
    @Path("/perimetro/triangulo")
    public Response calcularPerimetroTriangulo(@QueryParam("ladoA") double ladoA, @QueryParam("ladoB") double ladoB, @QueryParam("ladoC") double ladoC) {
        Triangulo triangulo = new Triangulo(0, 0, ladoA, ladoB, ladoC); // La base y altura no son necesarios para el cálculo del perímetro
        double perimetro = triangulo.calcularPerimetro();
        return Response.ok(Map.of("data", Map.of("perimetro", perimetro + " u"))).build();
    }
}
/*Los parámetros para las solicitudes HTTP se reciben a través de @QueryParam, lo cual permite que los datos de entrada se proporcionen como parámetros en la URL.
La respuesta sigue el formato JSON esperado, con la clave "data" y el valor correspondiente al resultado calculado, ya sea área o perímetro.*/