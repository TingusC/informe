package es.etg.dam.psp.clases;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import es.etg.dam.psp.interfaces.Escritor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class EscritorDocumentos implements Escritor{

    String ruta;

    @Override
    public void escribir(String texto) throws IOException
    {
        File archivo = new File(this.ruta);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            writer.write(texto);
        }
    }  
}
