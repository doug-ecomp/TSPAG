/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ag;

/**
 * Fiz essa classe para armazenar as coordenadas vindo da leitura do arquivo, 
 * dessa forma também fica melhor para fazer o cálculo das distâncias entre as cidades
 *
 * @author Rejanio
 */
public class Cromossomo {
    float x,y;
    public Cromossomo(float x , float y){
        this.x=x;
        this.y=y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    
}
