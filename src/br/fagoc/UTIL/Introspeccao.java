/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.fagoc.UTIL;

import br.fagoc.MODEL.ModelPai;
import java.lang.reflect.Method;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Thiago Sartori
 */
public class Introspeccao {
    
    public static String Maiscula(String pPalavra){//Primeira letra de uma Palavra em Maiusculo
        String vResultado = pPalavra.substring(0,1).toUpperCase()+pPalavra.substring(1);
        return vResultado;
    }
    
    public static ArrayList<Padrao> getMetodosDeclarados(String pClasse){
	ArrayList<Padrao> vRetorno = new ArrayList<Padrao>();
        try{
            Class classeReflexao = Class.forName("br.casa.MODEL."+pClasse);
            Method vMetodos[] = classeReflexao.getDeclaredMethods();
            
            for (int i = 0 ; i < vMetodos.length ; i++){
                if(vMetodos[i].getName().charAt(0) == 'g' || vMetodos[i].getName().charAt(0) == 'i'){//Pegar apenas os metodos get(s) e is
                    Padrao vAux = new Padrao();
                    vAux.setNomeMetodo(vMetodos[i].getName());
                    vAux.setTipoRetorno(vMetodos[i].getReturnType().toString());
                    
                    vRetorno.add(vAux);
                }
            }
            
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "Classe Inexistente\nDetalhe: "+e.toString(), "Mensagem", JOptionPane.ERROR_MESSAGE);
        }
        return vRetorno;
    }
    
    public static String PegaValorMetodo(String pClasse, ModelPai Obj, ArrayList<CampoBD> CamposTabela){  
        try {  
            ArrayList<Padrao>pMetodo = new ArrayList<Padrao>();
            pMetodo = getMetodosDeclarados(pClasse);
            
            ModelPai vClasseInstaciada = null;
            vClasseInstaciada = Obj;
            
            String vRetorno = "";
            
            Class ClasseGenerica = Class.forName("br.casa.MODEL."+pClasse);// "MODEL." refere-se ao nome do pacote.
            Padrao vAux = new Padrao();
            
            //Ordenando os metodos  ************INICIO****************
            ArrayList<Padrao> vMetodosOrganizados = new ArrayList<Padrao>();
            
            for(int j=0;j<CamposTabela.size();j++){
                CampoBD Campo = new CampoBD();
                Campo = CamposTabela.get(j);
                for(int n=0;n<pMetodo.size();n++){
                    vAux = pMetodo.get(n);
                    String NomeMetodo = pMetodo.get(n).getNomeMetodo();
                    //toUpperCase();
                    if(NomeMetodo.contains(Campo.getNomeCampo())){
                        vMetodosOrganizados.add(pMetodo.get(n));
                        n=pMetodo.size();//
                    }
                }
            }
            //Ordenando os metodos  ************FIM****************
            
            for(int i = 0; i< vMetodosOrganizados.size(); i++){
            vAux = vMetodosOrganizados.get(i);
            Method MetodoInvocado = ClasseGenerica.getMethod(vAux.getNomeMetodo());  //Nome do metodo que vai ser invocado
            
            try{
                if(MetodoInvocado.invoke(vClasseInstaciada)!=null){
                    
                    if(vAux.getTipoRetorno().contains("String")||vAux.getTipoRetorno().contains("string")||vAux.getTipoRetorno().contains("Date")||vAux.getTipoRetorno().contains("date"))
                        vRetorno+="'";
                    
                    vRetorno+=MetodoInvocado.invoke(vClasseInstaciada).toString();
                    
                    if(vAux.getTipoRetorno().contains("String")||vAux.getTipoRetorno().contains("string")||vAux.getTipoRetorno().contains("Date")||vAux.getTipoRetorno().contains("date"))
                        vRetorno+="'";
                }
                else{
                    vRetorno+="null";
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Erro Reflexão: "+e, "Gerenciador :: Thiago Sartori", JOptionPane.WARNING_MESSAGE);
            }
            
            if(i+1 < vMetodosOrganizados.size())
                vRetorno += ", ";
            else
                vRetorno += ")";
            }
            return vRetorno;
        }  
        catch (Throwable e) {  
            JOptionPane.showMessageDialog(null, "Ocorreu um erro em: Introspeccao.PegaValorMetodo()\nDetalhes:\n\n"+e);
            return "";
        }  
    }
    
}
