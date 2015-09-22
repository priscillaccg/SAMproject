package controle;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Priscilla
 */
public class CopiaArquivo {
    
   static void copiar(File fonte, File destino) throws IOException{
        InputStream in = new FileInputStream(fonte);
        OutputStream out = new FileOutputStream(destino);
    
        byte[] buf = new byte[1024];
        int len;
        while((len = in.read(buf)) > 0){
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }
}