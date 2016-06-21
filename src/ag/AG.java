/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ag;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author aluno
 */
public class AG {
    
    private float [][] matriz_adj = {{0f, 0.2f, 0.3f, 0.4f},
                                    {0.2f, 0f, 0.7f, 1.2f},
                                    {0.3f, 0.7f, 0f, 0.8f},
                                    {0.4f, 1.2f, 0.8f, 0f}};
    private float [] fitness_values;
    private int [][] populacao;
    private final int geracoes = 10;
    private int solucao_otima; //Indice da melhor solucao. Atualizado em cada geração;
    
    private DecimalFormat df;
    private DecimalFormatSymbols symbols;
    
    public AG(int pop_size, int city_amount){
        
        symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        df = new DecimalFormat();
	df.setMaximumFractionDigits(4);
	df.setMinimumFractionDigits(4);
        df.setDecimalFormatSymbols(symbols);
        
        fitness_values = new float [pop_size];
        Arrays.fill(fitness_values, -1);
        populacao = GeraPopulacao(pop_size, city_amount);
        solucao_otima = 0;
        
    }
    
    
    
    public void run(int pop_size, int city_amount){
        
        boolean valor_maximo = false;
        for(int i = 0; i < pop_size; i++)
            Fitness(populacao[i]);
        
       // while(valor_maximo){
            int [] popolacao_inter = Torneio(pop_size,  20);
            
            
            
       // }
    }
    /**
     * Retorna a matriz com todos os pais e filhos em apenas uma matriz 
     * @param populacao
     * @param l
     * @param c
     * @param populacao_gerada
     * @param linha
     * @param coluna
     * @return 
     */
    
    public float[][] agrupar_tabelas(float[][] populacao, int l,int c, float[][]populacao_gerada,int linha,int coluna ){
       int tamanho_linha=l+linha;
       int indice=0;
       float [][] populacao_total=new float[tamanho_linha][c];
       
       for(int i=0;i<l;i++){
           for(int j=0;j<c;c++){
               populacao_total[j][c]=populacao[i][j];
           
           }
       }
       for(int i=l-1;i<tamanho_linha;i++){
           for(int j=0;j<coluna;j++){
               populacao_total[i][j]= populacao_gerada[indice][j];
           }
           
       }
        
       return populacao_total;
    }
    
    public float [][] Reproduzir(float [][] populacao, int l, int c, int [] pop_inter, int porcentagem){
        int qtd_filhos = (int) ((float)pop_inter.length*((float)porcentagem/100));
        float [] filho;
        //a matriz população para uma nova matriz com a quantidade de tamanho da populacao gera (país e filhos gerados)
        float [][] populacao_gerada=new float [14][23];
        
        int indice=0;
        
        while(qtd_filhos>0){
            int index1;
            int index2;
            if(pop_inter.length>1){
                for(int i = 0; i < 2; i++){
                    
                    do{
                        index1 = (int)(Math.random()*pop_inter.length);
                        index2 = (int)(Math.random()*pop_inter.length);
                    }while((index1==index2));

//                    filho = Cruzamento(populacao[pop_inter[index1]], populacao[pop_inter[index2]], 70);
//                    // copia o filho gerado para a matriz populacao gerada, que contém apenas os gilhos gerados
//                    if(filho!=null){
//                        System.arraycopy(filho, 0, populacao_gerada[indice], 0, filho.length);
//                        indice++;
//                      
//                    }
//                    else if(filho== null){
//                        System.out.println("Filho com o resultado null");
//                    }
                }
            }
            
            
            
            
            qtd_filhos--;
        }
        return populacao_gerada;
    }
    
    public int [] Cruzamento(int []pai1, int []pai2, int taxa ){
        int r = (new Random()).nextInt(taxa+1);
        
        if(r<=taxa){
            int [] filho = new int[pai1.length];
            Arrays.fill(filho, -1);
            
            int [] genes_do_pai1 = new int [pai1.length/2];
            int [] indices_genes_pai1 = new int [pai1.length/2];
            int [] indices_no_pai2 = new int[pai1.length/2];
            for (int i = 0; i < genes_do_pai1.length; i++) {
                int aux1;
                do{
                    aux1 = (new Random()).nextInt(pai1.length);
                }while(BuscaElemento(genes_do_pai1, pai1[aux1])!=-1);
                genes_do_pai1[i] = pai1[aux1];
                indices_genes_pai1[i] = aux1;
            }
            
            int j, ii, x;//insertion sort
            int y;
            for ( j = 1; j < indices_genes_pai1.length; j++) 
            {
                 x = indices_genes_pai1[j];
                 y = genes_do_pai1[j];
                 ii = j-1;
                 while(ii >= 0 && indices_genes_pai1[ii] > x )
                 {
                         indices_genes_pai1[ii+1] = indices_genes_pai1[ii];
                         genes_do_pai1[ii+1] = genes_do_pai1[ii];
                         ii--;
                 }
                 indices_genes_pai1[ii + 1] = x;
                 genes_do_pai1[ii + 1] = y;
            }
            
            for (int i = 0; i < genes_do_pai1.length; i++) {
                indices_no_pai2[i] = BuscaElemento(pai2, genes_do_pai1[i]);
            }
            
            Arrays.sort(indices_no_pai2);
            
            for (int i = 0; i < genes_do_pai1.length; i++) {
                filho[indices_no_pai2[i]] = genes_do_pai1[i];
            }
            
            for(int gene: filho)
                System.out.print(gene+" ");
            System.out.println("");
            
            for(int i = 0; i < pai2.length; i++){
                if(BuscaElemento(filho, pai2[i])==-1){
                    int aux2 = BuscaElemento(filho, -1);
                    filho[aux2] = pai2[i];
                }
            }
            for(int gene: filho)
                System.out.print(gene+" ");
            //Fitness(filho);
            return filho;
        }
        else
            return null;
        
    } 
    
    public int [] Mutacao(int []pai, int taxa){
        int r = (new Random()).nextInt(taxa+1);
        if(r<=taxa){
            int indice1;
            int indice2;
            do{
                indice1 = (new Random()).nextInt(pai.length);
                indice2 = (new Random()).nextInt(pai.length);
            }while(indice1==indice2);
            
            int [] filho = pai;
            int aux = filho[indice1];
            filho[indice1] = filho[indice2];
            filho[indice2] = aux;

            //Fitness(filho);
            return filho;
        } 
        else
            return null;
        
    }
    
    public int BuscaElemento(int [] cromossomo, int gene){
        for(int i = 0; i < cromossomo.length; i++){
            if(cromossomo[i]==gene)
                return i;
        }
        return -1;
    } 
    
    public int [][]  GeraPopulacao(int pop_size, int city_amount){
        int [][] population = new int [pop_size][city_amount];
        for(int [] cromossomo:population)
            Arrays.fill(cromossomo, -1);
        
        for (int j=0; j < pop_size; j++){
            for(int i = 0; i < city_amount; i++){
                int gene;
                do{
                    gene=(new Random()).nextInt(city_amount);
                } while(BuscaElemento(population[j], gene)!=-1);
                population[j][i] = gene;
                System.out.print(gene+" ");
            }
            fitness_values[j] = Fitness(population[j]);
            System.out.print(j+" fit: "+fitness_values[j]+"   ");
            if(fitness_values[j]>fitness_values[solucao_otima]){
                solucao_otima = j;
                System.out.print(j+" ");
            }
            System.out.println("soluc ot: "+solucao_otima);
        }
        return population;
    }
    
    public float Fitness (int [] cromossomo){
        float fitness = 0f;
        for (int i = 0; i < cromossomo.length-1; i++) {
            fitness = fitness + matriz_adj[cromossomo[i]][cromossomo[i+1]];
            fitness = Float.parseFloat(df.format(fitness)); //Deixar no formato .00 (duas casas decimais
        }
        
        fitness = fitness + matriz_adj[cromossomo[cromossomo.length-1]][cromossomo[0]];
        fitness = Float.parseFloat(df.format(fitness)); //Deixar no formato .00 (duas casas decimais
        return fitness;
    }
    
    public int[] Torneio( int pop_size, int size){
        if(size%2!=0)
            size--;
        
        int [] indice = new int [size];
        Arrays.fill(indice, -1);
        int count = 0;
        int index;
        while (count<size){
            int a1;
            int a2;
            do{
                a1=(new Random()).nextInt(pop_size);
                a2=(new Random()).nextInt(pop_size);
            }while(a2==a1);
            
            if(fitness_values[a1]>fitness_values[a2])
                index = a1;
            else
                index = a2;
            if(BuscaElemento(indice, index)==-1){
                indice[count] = index;
                count++;
            }
        }
        return indice;
    }
    
    public double calcularDistancia(double cidade1_x,double cidade1_y, double cidade2_x,double cidade2_y){
             double distancia = Math.sqrt( Math.pow( (cidade1_x - cidade2_x),2 ) +
                               Math.pow( (cidade1_y - cidade2_y),2 ) );
             return distancia;
    
    }
    
    public static void main(String[] args) {
//        // TODO code application logic here
        
        AG genetic;
        genetic = new AG(10,4);
        int [] pai1 = {1, 3, 2,0};
        int [] pai2 = {3, 2, 5, 0, 4, 1};
//        float aux = (new AG()).Fitness(pai1, matriz_adj);
//        (new AG()).run(50, 23);
//        char a[] = new char[1];
//        int b = a[0];
//        System.out.println(b);
//        if(a[0]=='\u0000')
//            System.out.println(true);
        
    }


}
    

  
