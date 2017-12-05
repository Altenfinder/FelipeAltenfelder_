/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.costumer;

import br.com.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

public class Costumer {

    private int id_costumer;
    private String is_active;
    private String cpf_cnpj;
    private String nm_costumer;
    private float vl_total;

    private Connection conexao;
    private PreparedStatement p;
    private ResultSet rs;
    private String sql;
    private String sql1;
    private String sql2;

    public Costumer(int id_costumer, String is_active, String cpf_cnpj, String nm_costumer, float vl_total) {
        this.id_costumer = id_costumer;
        this.is_active = is_active;
        this.cpf_cnpj = cpf_cnpj;
        this.nm_costumer = nm_costumer;
        this.vl_total = vl_total;
    }

    public Costumer() {
        
    }

    public int getId_costumer() {
        return id_costumer;
    }

    public void setId_costumer(int id_costumer) {
        this.id_costumer = id_costumer;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getNm_costumer() {
        return nm_costumer;
    }

    public void setNm_costumer(String nm_costumer) {
        this.nm_costumer = nm_costumer;
    }

    public float getVl_total() {
        return vl_total;
    }

    public void setVl_total(float vl_total) {
        this.vl_total = vl_total;
    }

    //fiz a logica de um mini CRUD , para assim mostrar um pouco mais do que eu conheco.
    //geralmente eu faria uma classe DAO , mas já que o codigo é bem simples , vou colocar a logica na mesma classe
    /* public Costumer cadastrarC(int id_costumer, String is_active, String cpf_cnpj, String nm_costumer, float vl_total){
        
        Costumer c= new Costumer(id_costumer, is_active, cpf_cnpj, nm_costumer,vl_total);
        sql = "insert into tb_costumer_account values (?,?,?,?,?)";
        try {
            conexao = Conexao.getConnection();
            p = conexao.prepareStatement(sql);
            p.setInt(1, id_costumer);
            p.setString(2, cpf_cnpj);
            p.setString(3, nm_costumer);
            p.setString(4, is_active);
            p.setFloat(5, vl_total);
            p.execute();
            JOptionPane.showMessageDialog(null, "Cliente inserido com sucesso.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro no cadastro no banco de dados" + ex);
        }
        
        
        return c;
    }
    
    public boolean excluirC(Costumer c){
        boolean status = false;

        sql = "delete from tb_costumer_account where id_costumer = ?";
        try {
            conexao = Conexao.getConnection();
            p = conexao.prepareStatement(sql);    
            p.setInt(1, c.getId_costumer());
            p.execute();
            status = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir Cliente do BD\n"+ex);
        }
        
        return status;
    }
    //realizei a logica para editar costumer somente por boa pratica.
     /*public Costumer editarC(Costumer c){
   
        sql = "update tb_costumer_account set nm_costumer = ?, id_costumer = ?";
        try {
            conexao = Conexao.getConnection();
            p = conexao.prepareStatement(sql);
            p.setString(1, c.getNm_costumer());
            p.setInt(2, c.getId_costumer());
            p.execute();
            JOptionPane.showMessageDialog(null, "Clientes editados com sucesso.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro no alterar do banco de dados" + ex);
        }
        return c;
     }
     */
    public void PesquisarC() {
        boolean status = false;
        float totalCliente = 0;
        int contador = 0;
        float media = 0;
        sql = "select * from tb_costumer_account where vl_total>560 "
                + "and (id_costumer>=1500 and id_costumer<=2700) order by vl_total DESC;";
        
        try {
          
            conexao = Conexao.getConnection();
            p = conexao.prepareStatement("select * from tb_costumer_account where vl_total>560 and (id_costumer>=1500 and id_costumer<=2700) order by vl_total DESC;");
            rs = p.executeQuery();
            while (rs.next()) {
                int id_c = rs.getInt("id_costumer");
                String cpf_cnpjF = rs.getString("cpf_cnpj");
                String nomeC = rs.getString("nm_costumer");
                String is_actives = rs.getString("is_active");
                float total = rs.getFloat("vl_total");
                JOptionPane.showMessageDialog(null, "Nome do Cliente: " + nomeC + "\n Cpf_cnpj: " + cpf_cnpjF + "\n id_cliente: " + id_c + "\n Atividade:" + is_actives + "\n total de Saldo:" + total);

                totalCliente += total;
                contador++;
            }
            media = totalCliente / contador;
            JOptionPane.showMessageDialog(null, "media de todos os clientes : " + media);
        } catch (SQLException e) {
            showMessageDialog(null, "Erro ao pesquisar cliente no banco de dados\n" + e);
        }

  
    }
}
