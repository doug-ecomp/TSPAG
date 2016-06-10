/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ag;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Classe que vai servir para gravar as saídas em um arquivo para depois construir o gráfico
 * @author Rejanio E Douglas 
 */
public class Gravador {
    BufferedWriter buffWrite;
    
    // BufferedWriter buffWrite = new BufferedWriter(new FileWriter("../ag/src/ag/saida1.txt"));
    public Gravador(String lugar) throws IOException{
     buffWrite = new BufferedWriter(new FileWriter(lugar));
    }
    
    public void gravar(String string) throws IOException{
    buffWrite.write(string);
    }
    public void fechar() throws IOException{
    buffWrite.close();
    }
}
