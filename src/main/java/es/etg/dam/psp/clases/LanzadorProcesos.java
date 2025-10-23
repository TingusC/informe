package es.etg.dam.psp.clases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import es.etg.dam.psp.interfaces.Lanzador;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class LanzadorProcesos implements Lanzador{

    String comando;
    String parametro;
    String[] datos;

    @Override
    public String ejecutar() throws IOException{

        String[] comandoFinal = {this.comando};

        if(parametro != null)
        {
            String[] comandoParam = {this.comando, this.parametro};
            comandoFinal = comandoParam;
        }

        Process process = Runtime.getRuntime().exec(comandoFinal);

        if(datos != null)
        {
            OutputStream out = process.getOutputStream();
            try(PrintWriter writer = new PrintWriter(new OutputStreamWriter(out)))
            {
                for(String dato : datos)
                {
                    writer.write(dato);
                    writer.write(System.lineSeparator());
                }
            }
        }

        StringBuilder builder = new StringBuilder();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream())))
        {
            String linea;
            while((linea = reader.readLine()) != null)
            {
                builder.append(linea);
                builder.append(System.lineSeparator());
            }
        }
        return builder.toString();
    }
    
}
