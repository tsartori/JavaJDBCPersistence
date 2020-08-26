/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.fagoc.MODEL;

/**
 *
 * @author Thiago Sartori
 */
public abstract class ModelPai {

    public abstract void Salvar();
    public abstract void Atualizar();
    public abstract void Excluir();
    
    public abstract void setProximoCodigoInsercao();
    public abstract void RecuperaObjetoNavegacao(int iOpcao, int iCodAtual);
    public abstract void RecuperaObjeto(int iCodigo);
    
}
