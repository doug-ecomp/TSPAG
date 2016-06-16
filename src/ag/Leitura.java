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
    public float[][] lendo(){
        String[] numeros = null;
        ArrayList arrayTemp;
        Scanner reader = new Scanner(fileR).useDelimiter("#");
        arrayTemp = new ArrayList();
        
        while(reader.hasNext()){
             arrayTemp.add(reader.next());   
    }
        float [][] vetor = new float [14][2];
        int t=0;
        for(int x=0;x<14;x++){
            for(int y=0;y<2;y++){
                vetor[x][y]=Float.valueOf((String)arrayTemp.get(t)).floatValue();
                t++;
                System.out.printf(vetor[x][y]+"\n");
            }
        }
       return vetor;
    }

}
