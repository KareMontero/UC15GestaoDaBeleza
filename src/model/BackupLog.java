package model;

import java.sql.Timestamp;

public class BackupLog {

    private int id;
    private String nomeArquivo;
    private double tamanhoMb;
    private String status;
    private String mensagem;
    private Timestamp dataBackup;

    public BackupLog() {
    }

    public BackupLog(int id, String nomeArquivo, double tamanhoMb, String status, String mensagem, Timestamp dataBackup) {
        this.id = id;
        this.nomeArquivo = nomeArquivo;
        this.tamanhoMb = tamanhoMb;
        this.status = status;
        this.mensagem = mensagem;
        this.dataBackup = dataBackup;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public double getTamanhoMb() {
        return tamanhoMb;
    }

    public void setTamanhoMb(double tamanhoMb) {
        this.tamanhoMb = tamanhoMb;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Timestamp getDataBackup() {
        return dataBackup;
    }

    public void setDataBackup(Timestamp dataBackup) {
        this.dataBackup = dataBackup;
    }

}
