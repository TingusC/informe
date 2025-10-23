package es.etg.dam.psp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.etg.dam.psp.clases.EscritorDocumentos;
import es.etg.dam.psp.clases.LanzadorProcesos;
import es.etg.dam.psp.interfaces.Escritor;
import es.etg.dam.psp.interfaces.Lanzador;

public class Main {
    public static final String[] COMANDOS = {"ps", "df", "free"};
    public static final String INFORME = "informe.md";

    public static final String MSG_INICIO_DOC = "# INFORME ";
    public static final String MSG_RESULTADO = "### Resultado ";
    public static void main(String[] args) throws IOException {
        String salida;
        List<String> resultados = new ArrayList<>();
        for(String comando : COMANDOS)
        {
            Lanzador proceso = new LanzadorProcesos(comando, null,null);
            resultados.add(proceso.ejecutar());
        }
        salida = formatear(COMANDOS, resultados);
        Escritor escritor = new EscritorDocumentos(INFORME);
        escritor.escribir(salida);
    }

    
    public static String formatear(String[] procesos, List<String> resultados)
    {
        StringBuilder builder = new StringBuilder();

        builder.append(MSG_INICIO_DOC);
        
        for(int i = 0; i < procesos.length; i++)
        {
            builder.append(System.lineSeparator());
            builder.append(MSG_RESULTADO);
            builder.append(procesos[i]);
            builder.append(System.lineSeparator());
            builder.append(resultados.get(i));
        }
        return builder.toString();
    }
}