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
    
    public static int [][]  GStarter(int [][] populacao){
        for (int j=0;j<50;j++){
            for(int i = 0; i<21;i++){
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
    
    
    public void ConvertDec(int[][] populacao){
        for (int j=0;j<50;j++){
            int fit = 0;
            for(int i =21;i>=0;i--){
                fit = fit + populacao[j][i]^i;
            }
            populacao[j][22] = fit;
        }
    }

public static void main(String[] args) {
        // TODO code application logic here
    int[][] populacao = null; 
    populacao = GStarter(populacao);
    
    }


}
    

  
