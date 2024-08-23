package examen.jorgeleiva;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import examen.jorgeleiva.entity.RegistroOperacion;
import examen.jorgeleiva.model.Circulo;
import examen.jorgeleiva.model.Cuadrado;
import examen.jorgeleiva.model.Rectangulo;
import examen.jorgeleiva.model.Triangulo;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;

@OpenAPIDefinition(     //Define la información general de la API
        info = @Info(
                title = "API de Cálculos Geométricos",
                version = "1.0.0",
                contact = @Contact(name = "Jorge Leiva", email = "jorgeleiva.994@gmail.com")
        )
)
@Path("/geometria")
@Produces(MediaType.APPLICATION_JSON) // Exportar JSON
@Consumes(MediaType.APPLICATION_JSON) // Leer JSON
@Tag(name = "Geometría ", description = "  API para realizar operaciones de área y perímetro para figuras geométricas") //Etiqueta usada para agrupar y describir endpoints
public class GeometriaResource {

    // Cuadrado - Área
    @POST
    @Path("/area/cuadrado") //Ruta
    @Transactional //Transaccion para modificar BD con JPA
    @Operation(summary = "Calcula el área de un cuadrado", description = "Devuelve el área de un cuadrado, dado su lado.") //Doc Swagger - Titulo
    @Parameter(name = "Lado", description = "El lado del cuadrado", required = true) // Doc - Parametros de entrada
    @APIResponse( //respuesta API
            responseCode = "200",
            description = "Área calculada con éxito",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Double.class))
    )
    @APIResponse(responseCode = "400", description = "Solicitud inválida")
    public Response calcularAreaCuadrado(@QueryParam("lado") double lado) { // @QueryParam para pasar el valor en la URL
        Cuadrado cuadrado = new Cuadrado(lado);
        double area = cuadrado.calcularArea();
        RegistroOperacion registro = new RegistroOperacion(RegistroOperacion.FiguraGeometrica.CUADRADO, RegistroOperacion.TipoOperacion.AREA, area);
        registro.persist(); //sql
        return Response.ok(Map.of("data", Map.of("area", area + " u2"))).build(); //Respuesta en la consola
    }

    // Cuadrado - Perímetro
    @POST
    @Path("/perimetro/cuadrado")
    @Transactional
    @Operation(summary = "Calcula el perímetro de un cuadrado", description = "Devuelve el perímetro de un cuadrado, dado su lado.")
    @Parameter(name = "Lado", description = "El lado del cuadrado", required = true)
    @APIResponse(
            responseCode = "200",
            description = "Perímetro calculado con éxito",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Double.class))
    )
    @APIResponse(responseCode = "400", description = "Solicitud inválida")
    public Response calcularPerimetroCuadrado(@QueryParam("lado") double lado) {
        Cuadrado cuadrado = new Cuadrado(lado);
        double perimetro = cuadrado.calcularPerimetro();
        RegistroOperacion registro = new RegistroOperacion(RegistroOperacion.FiguraGeometrica.CUADRADO, RegistroOperacion.TipoOperacion.PERIMETRO, perimetro);
        registro.persist();
        return Response.ok(Map.of("data", Map.of("perimetro", perimetro + " u"))).build();
    }

    // Círculo - Área
    @POST
    @Path("/area/circulo")
    @Transactional
    @Operation(summary = "Calcula el área de un círculo", description = "Devuelve el área de un círculo, dado su radio.")
    @Parameter(name = "Radio", description = "El radio del círculo", required = true)
    @APIResponse(
            responseCode = "200",
            description = "Área calculada con éxito",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Double.class))
    )
    @APIResponse(responseCode = "400", description = "Solicitud inválida")
    public Response calcularAreaCirculo(@QueryParam("radio") double radio) {
        Circulo circulo = new Circulo(radio);
        double area = circulo.calcularArea();
        RegistroOperacion registro = new RegistroOperacion(RegistroOperacion.FiguraGeometrica.CIRCULO, RegistroOperacion.TipoOperacion.AREA, area);
        registro.persist();
        return Response.ok(Map.of("data", Map.of("area", area + " u2"))).build();
    }

    // Círculo - Perímetro
    @POST
    @Path("/circunferencia/circulo")
    @Transactional
    @Operation(summary = "Calcula la circunferencia de un círculo", description = "Devuelve la circunferencia de un círculo, dado su radio.")
    @Parameter(name = "Radio", description = "El radio del círculo", required = true)
    @APIResponse(
            responseCode = "200",
            description = "Circunferencia calculada con éxito",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Double.class))
    )
    @APIResponse(responseCode = "400", description = "Solicitud inválida")
    public Response calcularPerimetroCirculo(@QueryParam("radio") double radio) {
        Circulo circulo = new Circulo(radio);
        double perimetro = circulo.calcularPerimetro();
        RegistroOperacion registro = new RegistroOperacion(RegistroOperacion.FiguraGeometrica.CIRCULO, RegistroOperacion.TipoOperacion.PERIMETRO, perimetro);
        registro.persist();
        return Response.ok(Map.of("data", Map.of("perimetro", perimetro + " u"))).build();
    }

    // Rectángulo - Área
    @POST
    @Path("/area/rectangulo")
    @Transactional
    @Operation(summary = "Calcula el área de un rectángulo", description = "Devuelve el área de un rectángulo, dado su largo y ancho.")
    @Parameter(name = "Largo", description = "El largo del rectángulo", required = true)
    @Parameter(name = "Ancho", description = "El ancho del rectángulo", required = true)
    @APIResponse(
            responseCode = "200",
            description = "Área calculada con éxito",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Double.class))
    )
    @APIResponse(responseCode = "400", description = "Solicitud inválida")
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
    @Transactional
    @Operation(summary = "Calcula el perímetro de un rectángulo", description = "Devuelve el perímetro de un rectángulo, dado su largo y ancho.")
    @Parameter(name = "Largo", description = "El largo del rectángulo", required = true)
    @Parameter(name = "Ancho", description = "El ancho del rectángulo", required = true)
    @APIResponse(
            responseCode = "200",
            description = "Perímetro calculado con éxito",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Double.class))
    )
    @APIResponse(responseCode = "400", description = "Solicitud inválida")
    public Response calcularPerimetroRectangulo(@QueryParam("largo") double largo, @QueryParam("ancho") double ancho) {
        Rectangulo rectangulo = new Rectangulo(largo, ancho);
        double perimetro = rectangulo.calcularPerimetro();
        RegistroOperacion registro = new RegistroOperacion(RegistroOperacion.FiguraGeometrica.RECTANGULO, RegistroOperacion.TipoOperacion.PERIMETRO, perimetro);
        registro.persist();
        return Response.ok(Map.of("data", Map.of("perimetro", perimetro + " u"))).build();
    }

    // Triángulo - Área
    @POST
    @Path("/area/triangulo")
    @Transactional
    @Operation(summary = "Calcula el área de un triángulo", description = "Devuelve el área de un triángulo, dado su base y altura.")
    @Parameter(name = "Base", description = "La base del triángulo", required = true)
    @Parameter(name = "Altura", description = "La altura del triángulo", required = true)
    @APIResponse(
            responseCode = "200",
            description = "Área calculada con éxito",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Double.class))
    )
    @APIResponse(responseCode = "400", description = "Solicitud inválida")
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
    @Transactional
    @Operation(summary = "Calcula el perímetro de un triángulo", description = "Devuelve el perímetro de un triángulo, dado sus tres lados.")
    @Parameter(name = "Lado A", description = "El lado A del triángulo", required = true)
    @Parameter(name = "Lado B", description = "El lado B del triángulo", required = true)
    @Parameter(name = "Lado C", description = "El lado C del triángulo", required = true)
    @APIResponse(
            responseCode = "200",
            description = "Perímetro calculado con éxito",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Double.class))
    )
    @APIResponse(responseCode = "400", description = "Solicitud inválida")
    public Response calcularPerimetroTriangulo(@QueryParam("ladoA") double ladoA, @QueryParam("ladoB") double ladoB, @QueryParam("ladoC") double ladoC) {
        Triangulo triangulo = new Triangulo(0, 0, ladoA, ladoB, ladoC); // La base y altura no son necesarios para el cálculo del perímetro
        double perimetro = triangulo.calcularPerimetro();
        RegistroOperacion registro = new RegistroOperacion(RegistroOperacion.FiguraGeometrica.TRIANGULO, RegistroOperacion.TipoOperacion.PERIMETRO, perimetro);
        registro.persist();
        return Response.ok(Map.of("data", Map.of("perimetro", perimetro + " u"))).build();
    }
}

/*Los parámetros para las solicitudes HTTP se reciben a través de @QueryParam, lo cual permite que los datos de entrada se proporcionen como parámetros en la URL.*/