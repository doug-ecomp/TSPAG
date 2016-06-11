/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ag;

/**
 *
 * @author aluno
 */
public class AG {
    
    
    public void run(int l, int c){
        float[][] populacao = null; 
        populacao = GeraPopulacao(l, c);
        boolean valor_maximo = false;
        for(int i = 0; i < l; i++)
            Fitness(populacao[i]);
        
       // while(valor_maximo){
            int [] popolacao_inter = Torneio(populacao, l, c, 20);
            
            
            
       // }
    }
    
    public void GeraFilhos(float [][] populacao, int l, int c, int [] pop_inter, int porcentagem){
        int count_filhos = 0;
        int qtd_filhos = (int) ((float)pop_inter.length*((float)porcentagem/100));
        float [] filho;
        while(count_filhos<qtd_filhos){
            int index1;
            int index2;
            if(pop_inter.length>1){
                for(int i = 0; i < 2; i++){
                    
                    do{
                        index1 = (int)(Math.random()*pop_inter.length);
                        index2 = (int)(Math.random()*pop_inter.length);
                    }while((index1==index2));

                    filho = Cruzamento(populacao[pop_inter[index1]], populacao[pop_inter[index2]], 70);
                    if(filho!=null){
                        
                    }
                }
            }
            
            
            
            
            count_filhos++;
        }
    }
    
    public float [] Cruzamento(float []pai1, float []pai2, int taxa ){
        int r = (int)(Math.random()*100);
        if(r<=taxa){
            float [] filho = new float[pai1.length];
            for(int i = 0; i < filho.length-1; i++){
                if(i < (pai1.length/2))
                    filho[i] = pai1[i];
                else
                    filho[i] = pai2[i];
            }
            Fitness(filho);
            return filho;
        }
        else
            return null;
        
    } 
    
    public float [] Mutacao(float []pai, int taxa){
        int r = (int)(Math.random()*100);
        if(r<=taxa){
            int indice = (int) (Math.random()*pai.length-2);
            float [] filho = pai;
            if(filho[indice]==0)
                filho[indice]=1;
            else
                filho[indice] = 0;

            Fitness(filho);
            return filho;
        } 
        else
            return null;
        
    }
    
    public float [][]  GeraPopulacao(int l, int c){
        float [][] populacao = new float [l][c];
        for (int j=0;j<=l-1;j++){
            for(int i = 0; i<=c-2; i++){
                int a=(int) (1+Math.random()*2);
                if(a==1){
                    populacao[j][i]=1;
                }
                else if(a==2){
                    populacao[j][i]=0;
                }

            }
        }
    
    return populacao;
    }
    
    
    public float ConvertDec(float[] cromossomo){
        float fit = 0;
            for(int i = cromossomo.length-2; i>=0; i--){
                int exp = (cromossomo.length-2)-i;
                fit = (float)fit + ((float)cromossomo[i]*(float)(Math.pow(2, exp)));
            }
            
            return fit;
    }

    public void Fitness (float[] cromossomo){
       
        cromossomo[cromossomo.length-1]= -1.0f + (ConvertDec(cromossomo) * 3.0f/4194303f) ;
         
    }
    
    public int[] Torneio(float [][] populacao, int l, int c, int size){
        int [] indice = new int [size];
        int count = 0;
        int index;
        while (count<size){
            int a1;
            int a2;
            do{
                a1=(int) (Math.random()*(l-1));
                a2=(int) (Math.random()*(l-1));
            }while(a2==a1);
            
            if(populacao[a1][c-1]>populacao[a2][c-1])
                index = a1;
            else
                index = a2;
            if(!Check(indice, count, index)){
                indice[count] = index;
                count++;
            }
        }
        return indice;
    }
    
    public boolean Check(int [] vector, int count, int indice){
        for(int i = 0; i < count; i++){
            if(vector[i]==indice)
                return true;
        }
        
        return false;
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        (new AG()).run(50, 23);
        
    }


}
    

  
