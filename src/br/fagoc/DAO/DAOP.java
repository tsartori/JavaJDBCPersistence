/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.fagoc.DAO;

import br.fagoc.MODEL.ModelPai;
import br.fagoc.UTIL.CampoBD;
import br.fagoc.UTIL.Introspeccao;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Thiago Sartori
 */
public abstract class DAOP {
    
    public static final int cNavPrimeiro = 0;
    public static final int cNavAnterior = 1;
    public static final int cNavProximo = 2;
    public static final int cNavUltimo = 3;
    private String sNomeTabela = "";
    private String sChaveTabela = "";
    private ArrayList<CampoBD> CamposTabela;
    private CampoBD CamposTb;

    public DAOP(String Tabela, String Chave) {
        sNomeTabela = Tabela;
        sChaveTabela = Chave;

        CamposTabela = new ArrayList<CampoBD>();
    }

    public abstract ModelPai RecuperaObjCodigo(Integer Codigo);

    public final void PegaCamposTabela() {
        Connection cnx = ConnectionFactory.getInstance().getConexao();
        try {
            Statement objSTM = cnx.createStatement();
            //objSTM.executeQuery("show fields from " + sNomeTabela);//MySQL
            ResultSet objResultSet = objSTM.executeQuery("show fields from "+sNomeTabela);
            while (objResultSet.next()) {
                CamposTb = new CampoBD(sNomeTabela);

                String Field_Name = objResultSet.getString("Field");
                CamposTb.setNomeCampo(Field_Name);//Nome das colunas
                CamposTb.setTipoCampo(objResultSet.getString("Type"));
                if(objResultSet.getString("Key").equals("PRI")) {
                    CamposTb.setPrimaria(objResultSet.getString("Key"));
                }
                else if (objResultSet.getString("Key").equals("MUL")) {
                    CamposTb.setEstrangeria("Key");
                }

                CamposTabela.add(CamposTb);
            }
            objResultSet.close();
            objSTM.close();
            //cnx.close();
        } catch (Exception erro) {
            String errorMsg = "Erro ao Recuperar: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String GeraSQL(String sql, ModelPai obj) throws ClassNotFoundException {
        String vSQL = sql;

        if (vSQL.contains("insert into")) {
            //vSQL += sNomeTabela + " (";
            int j=0;
            for (int i = 0; i < CamposTabela.size(); i++) {
                CamposTb = CamposTabela.get(i);
                vSQL += CamposTb.getNomeCampo();
                j++;
                if (i + 1 < CamposTabela.size()) {
                    vSQL += ", ";
                } else {
                    vSQL += ") values (";
                }
            }
            for (int i = 0; i < j; i++) {
                if (i+1<j) {
                    vSQL +="?,";
                }else{
                    vSQL +="?)";
                }
            }
            vSQL += Introspeccao.PegaValorMetodo(sNomeTabela, obj, CamposTabela);
        }

        //UPDATE
        if (vSQL.contains("update")) {
            vSQL += sNomeTabela + " set";

            ModelPai vClasseInstaciada = null;
            vClasseInstaciada = obj;

            Class vClasseReflexao = Class.forName("br.casa.MODEL." + sNomeTabela);//A tabela deve possuir o mesmo nome da classe

            Method vMetodos[] = vClasseReflexao.getDeclaredMethods();

            for (int i = CamposTabela.size() - 1; i >= 0; i--) {
                CamposTb = CamposTabela.get(i);
                if (i > 0) {
                    vSQL += " " + CamposTb.getNomeCampo() + " = ";
                } else {
                    vSQL += " where " + sChaveTabela + "=";
                    CamposTb.setNomeCampo(sChaveTabela);//CamposTb.setNomeCampo(sChaveTabela);
                }
                for (int j = 0; j < vMetodos.length; j++) {
                    String vMetodo = vMetodos[j].getName();//toUpperCase()
                    if (vMetodo.contains(CamposTb.getNomeCampo()) && ((vMetodos[j].getName().charAt(0) == 'g') || (vMetodos[j].getName().charAt(0) == 'i'))) {
                        try {
                            Method MetodoInvocado = vClasseReflexao.getMethod(vMetodos[j].getName());
                            try {
                                String TipoRetorno = "" + vMetodos[j].getReturnType();
                                //System.out.println(TipoRetorno);
                                if (MetodoInvocado.invoke(vClasseInstaciada) != null) {

                                    if(TipoRetorno.contains("String")||TipoRetorno.contains("string")||TipoRetorno.contains("Date")||TipoRetorno.contains("date")) {
                                        vSQL+="'";
                                    }
                                    
                                    vSQL+=MetodoInvocado.invoke(vClasseInstaciada).toString();
                                    
                                    if(TipoRetorno.contains("String")||TipoRetorno.contains("string")||TipoRetorno.contains("Date")||TipoRetorno.contains("date")) {
                                        vSQL+="'";
                                    }
                       
                                } else {
                                    vSQL += "null";
                                }
                                j = vMetodos.length;

                            } catch (IllegalAccessException ex) {
                                Logger.getLogger(DAOPai.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IllegalArgumentException ex) {
                                Logger.getLogger(DAOPai.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (InvocationTargetException ex) {
                                Logger.getLogger(DAOPai.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } catch (NoSuchMethodException ex) {
                            Logger.getLogger(DAOPai.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SecurityException ex) {
                            Logger.getLogger(DAOPai.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }

                }

                if (i > 1) {
                    vSQL += ",";
                }
            }
        }
        return vSQL;
    }

    public void salvar(ModelPai Obj) throws SQLException, ClassNotFoundException {
        if (this.CamposTabela.isEmpty()) {
            this.PegaCamposTabela();
        }

        String sql, SQLExecutada;
        sql = "insert into "+this.sNomeTabela+" (";
        SQLExecutada = GeraSQL(sql, Obj);

        Connection cnx = ConnectionFactory.getInstance().getConexao();
        Statement insereSt = cnx.createStatement();

        try {
            cnx.setAutoCommit(false);

            insereSt.executeUpdate(SQLExecutada);

            cnx.commit();
        } catch (Exception e) {
            cnx.rollback();
            cnx.setAutoCommit(true);
            JOptionPane.showMessageDialog(null, "Erro ao incluir " + sNomeTabela + ": " + e.getMessage() + "\n");
        } finally {
            try {
                cnx.setAutoCommit(true);
                insereSt.close();
            } catch (Throwable e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão: " + e.getMessage());
            }
        }
    }

    public void Atualizar(ModelPai ObjModelo) {
        if (this.CamposTabela.isEmpty()) {
            this.PegaCamposTabela();
        }

        Statement insereSt = null;
        Connection cnx = ConnectionFactory.getInstance().getConexao();
        String sql = "update ";

        try {
            cnx.setAutoCommit(false);

            String SQLExecutada = GeraSQL(sql, ObjModelo);
            insereSt = cnx.createStatement();
            insereSt.executeUpdate(SQLExecutada);

            cnx.commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar dados do " + sNomeTabela + ":\n" + e.getMessage() + "\n");
            try {
                cnx.rollback();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao dar Rollback:\n" + ex.getMessage() + "\n");
            }
        } finally {
            try {
                cnx.setAutoCommit(true);
                insereSt.close();
                //cnx.close();
            } catch (Throwable e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão: " + e.getMessage());
            }
        }

    }

    public void Excluir(Integer Codigo) throws SQLException {
        Statement insereSt = null;

        String sql = "delete from " + sNomeTabela + " where " + sChaveTabela + " = " + Codigo;

        Connection cnx = ConnectionFactory.getInstance().getConexao();
        try {

            cnx.setAutoCommit(false);

            insereSt = cnx.createStatement();
            insereSt.executeUpdate(sql);

            cnx.commit();
        } catch (Exception e) {
            cnx.rollback();
            JOptionPane.showMessageDialog(null, "Erro ao excluir " + sNomeTabela + ":\n" + e.getMessage() + "\n");
        } finally {
            try {
                cnx.setAutoCommit(true);
                insereSt.close();
                //cnx.close();
            } catch (Throwable e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão: " + e.getMessage());
            }
        }
    }

    public final int PegaCodigoPelaNavegacao(int iOpcao, int iCodigoAtual) {
        Connection cnx = ConnectionFactory.getInstance().getConexao();
        
        Statement consulta = null;
        ResultSet resultado = null;
        int CodigoEncontrado = -1;
        
        try {
            consulta=(Statement)cnx.createStatement();
            resultado = consulta.executeQuery( SQLNavegacao(iOpcao, iCodigoAtual) );
            
            resultado.next();
            CodigoEncontrado=resultado.getInt("COD");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao executar sql de navegação:\n" + e.getMessage() + "\n");
        }try{
            consulta.close();
            //cnx.close();
        }catch(Throwable e){
            JOptionPane.showMessageDialog(null ,"Erro ao encerrar conexão na função de navegação: "+e.getMessage());
        } 
        
        return CodigoEncontrado;
    }

    public String SQLNavegacao(int iOpcao, int iCodigoAtual) {
        String sql = "";

        switch (iOpcao) {
            case cNavPrimeiro:
                sql = "select min(" + sChaveTabela + ") as COD from " + sNomeTabela;
                break;
            case cNavAnterior:
                sql = "select max(" + sChaveTabela + ") as COD from " + sNomeTabela + " where " + sChaveTabela + " < " + String.valueOf(iCodigoAtual);
                break;
            case cNavProximo:
                sql = "select min(" + sChaveTabela + ") as COD from " + sNomeTabela + " where " + sChaveTabela + " > " + String.valueOf(iCodigoAtual);
                break;
            case cNavUltimo:
                sql = "select max(" + sChaveTabela + ") as COD from " + sNomeTabela;
                break;
        }
        return sql;
    }
}
