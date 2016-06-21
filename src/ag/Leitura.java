/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ag;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Rejanio
 */
public class Leitura  {
     
      private  FileReader fileR;
    public Leitura() throws FileNotFoundException, IOException {
       
        fileR = new FileReader("../Tsp/src/tsp/burma14.tsp");
        
    }
/**
 * Função que ler o arquivo no caminho indicado no construtor
 * @return retorna o vetor com as coordenadas de cada cidade, o detalhe é que a primeira coluna deve ser composta pelo numero da cidade
 * 
 */
    public ArrayList lendo(){
        ArrayList cromossomo = new ArrayList();
        
                
        String[] numeros = null;
        ArrayList arrayTemp;
        Scanner reader = new Scanner(fileR).useDelimiter("#");
        arrayTemp = new ArrayList();
        
        while(reader.hasNext()){
             arrayTemp.add(reader.next());   
    }
        float [][] vetor = new float [14][2];
        //int t=0;
        //for(int x=0;x<14;x++){
            //for(int y=0;y<2;y++){
                //vetor[x][y]=Float.valueOf((String)arrayTemp.get(t)).floatValue();
                //t++;
                //System.out.printf(vetor[x][y]+"\n");
            //}
        //}
        int temporario=0;
        float v1,v2;
        int x1=0,x2=1;
        
        //System.out.printf("tamanho " +arrayTemp.get(27));
        //System.out.printf("\n");
        //Nova forma de  percorrer o arquivo e armazenar as informações lindas, serão guardas em 
        // um ArrayList dessa forma ficará mais fácil para calcular as distâncias entre as cidades. 
        while(temporario < arrayTemp.size()-2){
            //System.out.printf("valor de t: "+temporario+" valor de x1 : "+x1+" valor de x2 : "+x2+ "\n");
            Cromossomo elemento;
            v1=Float.valueOf((String)arrayTemp.get(x1)).floatValue();
            
            v2=Float.valueOf((String)arrayTemp.get((x2))).floatValue();
            elemento= new Cromossomo(v1,v2);
            cromossomo.add(elemento);
            x1=x1+2;
            x2=x2+2;
            temporario+=2;
        }
       return cromossomo;
    }

}
