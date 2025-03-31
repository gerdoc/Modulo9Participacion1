package mx.unam.dgtic.practica1.model;

import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Disco
{
    private Integer id;
    private String nombre;
    private String genero;
    @Size(min = 10, max = 10)
    private String codigo;
    private Integer anioLanzamiento;
    private String compania;
}
