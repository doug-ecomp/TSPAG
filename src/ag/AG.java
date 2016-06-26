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
    
    public static final int taxa_cruzamento =  70;
    public static final int taxa_mutacao =  5;
    public static final int taxa_elitismo =  5;
    public static final int taxa_selecao = 40;
    public static final int qtd_geracoes = 10;
    
    
    private float [][] matriz_adj = {{0f, 0.2f, 0.3f, 0.4f},
                                    {0.2f, 0f, 0.7f, 1.2f},
                                    {0.3f, 0.7f, 0f, 0.8f},
                                    {0.4f, 1.2f, 0.8f, 0f}};
    private float [] fitness_values;
    private int [][] populacao;
    private ArrayList <Integer> elite;
    private final int geracoes = 10;
    private int solucao_otima; //Indice da melhor solucao. Atualizado em cada geração;
    private int pop_size;
    private int city_amount;
    
    private DecimalFormat df;
    private DecimalFormatSymbols symbols;
    
    public AG(int pop_size, int city_amount){
        this.pop_size = pop_size;
        this.city_amount = city_amount;
        
        symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        df = new DecimalFormat();
	df.setMaximumFractionDigits(2);
	df.setMinimumFractionDigits(2);
        df.setDecimalFormatSymbols(symbols);
        
        fitness_values = new float [pop_size];
        Arrays.fill(fitness_values, -1);
        elite = new ArrayList<>();
        
        populacao = GeraPopulacao();
        solucao_otima = 0;
        
    }
    
    
    
    public void run(){
        int geracao = 0;
        
        while(geracao<qtd_geracoes){
            int [] popolacao_inter = Torneio();
            ArrayList<int []> filhos =  Reproduzir(popolacao_inter);
            
            
            
       }
    }
    
    
    public void Substituicao(ArrayList<int []> populacao_gerada){
        int indice;
        for(int [] filho: populacao_gerada){
            do{
                indice = (new Random()).nextInt(pop_size);
            } while(elite.contains(indice));
            populacao[indice] = filho;
            float fitness = Fitness(filho);
            fitness_values[indice] = fitness;
            AtualizaElite(indice, fitness);
        }
        
        
    }
    public void AtualizaElite(int indice, float fitness){
        for (int i = 0; i < elite.size(); i++) {
            if(fitness>fitness_values[elite.get(i)]){
                elite.remove(i);
                elite.add(i, indice);
                break;
            }  
        }
        
        
    }
    public ArrayList<int []> Reproduzir(int [] pop_inter){
        int [] filho;
        int qtd_filhos;
        //a matriz população para uma nova matriz com a quantidade de tamanho da populacao gera (país e filhos gerados)
        ArrayList<int []> populacao_gerada = new ArrayList<>();
        
        int indice=0;
        
        do{
            for(int i = 0, j = pop_inter.length-1; i < pop_inter.length/2; i++, j--){

                filho = Cruzamento(populacao[pop_inter[i]], populacao[pop_inter[j]]);
                if(filho!=null){
                    populacao_gerada.add(filho);
                }
                else {
                    System.out.println("Filho com o resultado null");
                }
            }
        }while(populacao_gerada.isEmpty());

        for (int i = 0; i < populacao_gerada.size(); i++) {
            int [] sun =  Mutacao(populacao_gerada.get(i));
            if (sun!=null){
                populacao_gerada.remove(i);
                populacao_gerada.add(i, sun);
            }
        }
        
        return populacao_gerada;
    }
    
    public int [] Cruzamento(int []pai1, int []pai2){
        int r = (new Random()).nextInt(taxa_cruzamento+1);
        
        if(r<=taxa_cruzamento){
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
    
    public int [] Mutacao(int []pai){
        int r = (new Random()).nextInt(taxa_mutacao+1);
        if(r<=taxa_mutacao){
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
    
    public int [][]  GeraPopulacao(){
        int qtd_elite = (int) Math.floor((float)pop_size*( (float)(taxa_elitismo/100) ) ); //usar floor ao inves de round 
                                                                                           //pq se a populacao for muito 
                                                                                           //pequena arredondar por resultar 
                                                                                           //em 0 individuos na elite
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
        
        for(int i = 0; i < pop_size; i++){
            Fitness(populacao[i]);
            if(qtd_elite>0){
                
            }
            
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
    
    public int[] Torneio(){
        int qtd = (int) ((float)pop_size*( (float)(taxa_selecao/100) ) );
        
        if(qtd%2!=0) //faz a qtd ser par
            qtd--;
        
        int [] indice = new int [qtd];
        Arrays.fill(indice, -1);
        int count = 0;
        int index;
        while (count<qtd){
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
    
    public float calcularDistancia(float cidade1_x,float cidade1_y, float cidade2_x,float cidade2_y){
             float distancia =(float) Math.sqrt( Math.pow( (cidade1_x - cidade2_x),2 ) +
                               Math.pow( (cidade1_y - cidade2_y),2 ) );
             return distancia;
    
    }
    /**
     * Função que retorna a matriz de adjacência recebendo o um arrayList de cromossomo onde 
     * cada genes faz é uma cidade com as coordenadas X e Y,vindas do arquivo de leitura.
     *  
     * @param cromossomo contém todas as cidades 
     * @param l tamanho da matriz adjacência que no caso vai ser 14 que é quantidade de cidade logo linha recebe 14
     * @param c tamanho da matriz adjacência que no caso vai ser 14 que é quantidade de cidade logo coluna recebe 14
     * @return o retorno é a matriz adjacência com todos as distancias de uma cidade para outra. 
     */
   public float [][]matrizAdjacencia (ArrayList cromossomo,int l, int c){
       float [][] matriz = new float [l][c];
       // percorrendo a matriz e armazenando as distância 
       for(int linha=0;linha<l ;linha++){
       for(int coluna=0;coluna<c;coluna++){
           //zerando a diagonal principal 
           if(linha==coluna){
               matriz[linha][coluna]=0;
           }
           else{
               // faz o cash do objeto recebido da lista e percorrer passando para a função calcular
               // a dist^ncia entre as cidades. 
               Cromossomo gene_1=(Cromossomo)cromossomo.get(linha);
               Cromossomo gene_2=(Cromossomo)cromossomo.get(coluna);
               float numero =calcularDistancia(gene_1.getX(),gene_1.getY(),gene_2.getX(),gene_2.getY());
               // caso dê algum número negativo. 
               matriz[linha][coluna]=Math.abs(numero);
           }
       }
   }
       return matriz;
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
    

  
