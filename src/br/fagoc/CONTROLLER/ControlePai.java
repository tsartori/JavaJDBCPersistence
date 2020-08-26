/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.fagoc.CONTROLLER;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thiago Sartori
 */
public abstract class ControlePai {
    public abstract ArrayList<String> RecuperaObjetoNavegacao(int iOpcao, int iCodAtual);
    public abstract ArrayList<String> RecuperaObjeto(int iCodigo);
    
    public abstract int Salvar(ArrayList<String> Dados);
    public abstract void Atualizar(ArrayList<String> Dados);
    public abstract void Excluir(int iCod);
    public abstract DefaultTableModel PesquisaObjeto (ArrayList<String> Parametros, DefaultTableModel ModeloTabela);
    
}
