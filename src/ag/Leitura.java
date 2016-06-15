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
import java.util.Scanner;
/**
 *
 * @author Rejanio
 */
public class Leitura  {
    String nome = "../Tsp/src/tsp/burma14.tsp";
      FileReader arq;
      BufferedReader lerArq = new BufferedReader(arq);

    public Leitura() throws FileNotFoundException, IOException {
        this.arq = new FileReader(nome);
    }
  String linha = lerArq.readLine(); // lê a primeira linha

 //falta colocar o restante do código

}
