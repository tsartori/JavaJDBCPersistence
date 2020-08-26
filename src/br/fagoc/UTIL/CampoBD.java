/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.fagoc.UTIL;

public class CampoBD {
    public final static int cTipoInteiro = 0;
    public final static int cTipoVarchar = 1;
    public final static int cTipoDate = 2;
    
    private String NomeTabela;
    private boolean Primaria;
    private String NomeCampo;
    private String TipoCampo;
    private boolean Estrangeria;
    
    public CampoBD(String nomeTabela){
        this.NomeTabela = nomeTabela;
    }
    
    public CampoBD(){
        
    }

    public String getNomeCampo() {
        return NomeCampo;
    }

    public void setNomeCampo(String NomeCampo) {
        this.NomeCampo = NomeCampo;
    }

    public boolean getChaveTabela() {
        return Primaria;
    }

    public void setPrimaria(String bChave) {
        if(bChave.equals("PRI"))
            this.Primaria=true;
        else
            this.Primaria=false;
        
    }

    public String getTipoCampo() {
        return TipoCampo;
    }

    public boolean isEstrangeria() {
        return Estrangeria;
    }

    public void setEstrangeria(String pChave) {
        if(pChave.equals("MUL"))
            this.Estrangeria = true;
        else
            this.Estrangeria=false;
    }
    

    public void setTipoCampo(String sTipoCampoBD) {
        this.TipoCampo= sTipoCampoBD.toLowerCase();
    }
    

}
