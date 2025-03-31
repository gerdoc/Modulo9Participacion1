package mx.unam.dgtic.practica1.controller;

import mx.unam.dgtic.practica1.model.Disco;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping( "/api/disco")
public class DiscoController
{
    private Map<Integer, Disco> discoMap;

    public DiscoController()
    {
        init( );
    }

    private void init()
    {
        discoMap = new HashMap<>();
        discoMap.put(1 , Disco.builder().id(1).anioLanzamiento(1960).nombre("The Beatles").codigo("123456789").genero("Rock").compania("ABC").build( ) );
        discoMap.put(2, Disco.builder().id(2).anioLanzamiento(1980).nombre("Back in Black").codigo("111222333").genero("Hard Rock").compania("Atlantic").build() );
        discoMap.put(3, Disco.builder().id(3).anioLanzamiento(1997).nombre("Sehnsucht").codigo("444555666").genero("Industrial Metal").compania("Motor Music").build());
        discoMap.put(4, Disco.builder().id(4).anioLanzamiento(1982).nombre("The Number of the Beast").codigo("777888999").genero("Heavy Metal").compania("EMI").build());
        discoMap.put(5, Disco.builder().id(5).anioLanzamiento(1991).nombre("Metallica").codigo("123987456").genero("Heavy Metal").compania("Elektra").build());
        discoMap.put(6 , Disco.builder().id(6).anioLanzamiento(1986).nombre("Master of Puppets").codigo("654321789").genero("Metal").compania("Elektra").build( ) );
        discoMap.put(7 , Disco.builder().id(7).anioLanzamiento(1991).nombre("Metallica (The Black Album)").codigo("987654321").genero("Metal").compania("Vertigo").build( ) );
        discoMap.put(8 , Disco.builder().id(8).anioLanzamiento(1982).nombre("Walk Among Us").codigo("123456987").genero("Punk").compania("Ruby").build( ) );
        discoMap.put(9 , Disco.builder().id(9).anioLanzamiento(1997).nombre("American Psycho").codigo("456789123").genero("Punk").compania("Geffen").build( ) );
        discoMap.put(11, Disco.builder().id(11).anioLanzamiento(1991).nombre("Nevermind").codigo("9182736451").genero("Grunge").compania("DGC").build());
        discoMap.put(12, Disco.builder().id(12).anioLanzamiento(1973).nombre("The Dark Side of the Moon").codigo("5647382910").genero("Progressive Rock").compania("Harvest").build());
        discoMap.put(13, Disco.builder().id(13).anioLanzamiento(1982).nombre("Thriller").codigo("1029384756").genero("Pop").compania("Epic").build());
        discoMap.put(14, Disco.builder().id(14).anioLanzamiento(2000).nombre("Hybrid Theory").codigo("6758493021").genero("Nu Metal").compania("Warner Bros.").build());
        discoMap.put(15, Disco.builder().id(15).anioLanzamiento(1977).nombre("Rumours").codigo("3847561920").genero("Rock").compania("Warner Bros.").build());
        discoMap.put(16, Disco.builder().id(16).anioLanzamiento(1975).nombre("Bohemian Rhapsody").codigo("987654321").genero("Rock").compania("XYZ").build());
        discoMap.put(17, Disco.builder().id(17).anioLanzamiento(1980).nombre("Back in Black").codigo("876543219").genero("Rock").compania("DEF").build());
        discoMap.put(18, Disco.builder().id(18).anioLanzamiento(1991).nombre("Nevermind").codigo("765432198").genero("Grunge").compania("GHI").build());
        discoMap.put(19, Disco.builder().id(19).anioLanzamiento(2000).nombre("Hybrid Theory").codigo("654321987").genero("Nu Metal").compania("JKL").build());
        discoMap.put(20, Disco.builder().id(20).anioLanzamiento(2003).nombre("Elephant").codigo("543219876").genero("Alternative").compania("MNO").build());
        discoMap.put(21, Disco.builder().id(21).anioLanzamiento(2010).nombre("The Suburbs").codigo("432198765").genero("Indie Rock").compania("PQR").build());
    }

    @GetMapping(value = "/ping")
    public String ping()
    {
        return "Ok";
    }

    @GetMapping(value = "/")
    public ResponseEntity<Map<Integer, Disco>> getDiscos()
    {
        if( discoMap == null || discoMap.isEmpty( ) )
        {
            return ResponseEntity.notFound( ).build( );
        }
        return new ResponseEntity<>( discoMap, HttpStatus.OK );
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Disco> getDiscoById(@PathVariable Integer id)
    {
        if( discoMap == null || discoMap.isEmpty( )  || !discoMap.containsKey( id ) )
        {
            return ResponseEntity.notFound( ).build( );
        }
        return new ResponseEntity<>( discoMap.get( id ), HttpStatus.OK );
    }

    @PostMapping("/")
    public ResponseEntity<Disco> addBook(@RequestBody  Disco disco )
    {
        int max = discoMap.keySet().stream().max(Integer::compareTo).orElse(0);
        disco.setId( max + 1 );
        discoMap.put( disco.getId(), disco );
        return new ResponseEntity( disco, HttpStatus.CREATED );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disco> actualizarDisco(@PathVariable Integer id, @RequestBody Disco disco)
    {
        if( discoMap.get(id) == null)
        {
            return ResponseEntity.notFound( ).build( );
        }
        discoMap.remove(id);
        discoMap.put(id, disco);
        return ResponseEntity.ok( discoMap.get( id ) );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Disco> patchDisco(@PathVariable Integer id,  @RequestBody Disco disco)
    {
        Disco discoOriginal = discoMap.get( id );
        if( discoOriginal == null)
        {
            return ResponseEntity.notFound( ).build( );
        }
        discoOriginal.setAnioLanzamiento( disco.getAnioLanzamiento() );
        discoOriginal.setCodigo( disco.getCodigo() );
        discoOriginal.setCompania( disco.getCompania() );
        discoOriginal.setGenero( disco.getGenero() );
        discoOriginal.setNombre( disco.getNombre() );
        return ResponseEntity.ok( discoMap.get( id ) );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Disco> eliminarDisco(@PathVariable Integer id)
    {
        Disco disco = discoMap.get( id );
        if( disco == null)
        {
            return ResponseEntity.notFound( ).build( );
        }
        discoMap.remove(id);
        return ResponseEntity.ok( disco );
    }

    @GetMapping("/creditos")
    public ResponseEntity<List<String>> creditos()
    {
        List<String>list = new ArrayList<>();
        list.add("Autor: Brandon Noé Roldan Granados");
        list.add("Autor: Luis Angel Maya Parrazales ");
        list.add("Autor: José Antonio Lopez Perez");
        list.add("Autor: Issac Celis Vargas");
        list.add("Autor: Germán Gutiérrez Galán");
        return ResponseEntity.ok( list );
    }

}
