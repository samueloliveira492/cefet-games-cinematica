/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.games.movement.behavior;

import br.cefetmg.games.movement.AlgoritmoMovimentacao;
import br.cefetmg.games.movement.Direcionamento;
import br.cefetmg.games.movement.Pose;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector3;

/**
 *
 * @author aluno
 */
public class Chegar  extends AlgoritmoMovimentacao {

    private static final char NOME = 'c';
    private float raio;
    private float timeToTarget = (float)0.25;
    private float maxAngular = 30f;


    public Chegar(float tangencial, float angular, float raio, float timeToTarget) {
        super(NOME);
        maxVelocidade = tangencial;
        maxAngular = angular;
        this.raio = raio;
        this.timeToTarget = timeToTarget;
        
    }
    

    @Override
    public Direcionamento guiar(Pose agente) {
        Direcionamento output = new Direcionamento();
        output.velocidade = new Vector3(alvo.getObjetivo()).sub(agente.posicao);
        
        if(output.velocidade.len() < this.raio){
            output.velocidade = Vector3.Zero;
            return output;
        }
        
        
        output.velocidade.scl(1/this.timeToTarget); 
        
        if(output.velocidade.len() > this.maxVelocidade){
            output.velocidade.nor();
            output.velocidade.scl(this.maxVelocidade);
        }
        agente.olharNaDirecaoDaVelocidade(output.velocidade);
        output.rotacao = 0;
     
        return output;
    }

    @Override
    public int getTeclaParaAtivacao() {
        return Keys.C;
    }
    
}
